package com.training.carsharing.controller;

import com.training.carsharing.EmailSenderService;
import com.training.carsharing.ReCaptchaService;
import com.training.carsharing.UserAccountService;
import com.training.carsharing.converter.fromDto.UserAccountFromDtoConverter;
import com.training.carsharing.dto.UserAccountDto;
import com.training.carsharing.model.impl.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger("registrationLogger");
    private static final Object MESSAGING_ERROR = "Something went wrong. Verifying key was not sended to you email. Your key: ";
    private static final String ALPHABET = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
    private static final String G_RECAPTCHA_RESPONSE = "g-recaptcha-response";
    private static final String ERROR = "error";
    private static final String FORM_MODEL_ATTRIBUTE_NAME = "formModel";
    private static final String VERIFYING_COMPLETE = "verifying complete for user {}";
    private static final String VERIFIED = "verified";
    private static final String PATH_VARIABLE_VERIFY_KEY = "verifyKey";

    private static final String VERIFY_WITH_LINK_MAPPING = "/verifying/{verifyKey}";
    private static final String VERIFY_WITH_KEY_MAPPING = "/verifying";

    private static final String REDIRECT = "redirect:registration/verifying";
    private static final String REGISTRATION_VIEW_NAME = "registration.edit";
    private static final String VERIFYING_VIEW_NAME = "registration.verifying";


    @Autowired
    private UserAccountFromDtoConverter fromDtoConverter;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ReCaptchaService reCaptchaService;

    @GetMapping
    public ModelAndView index() {
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, new UserAccountDto());
        return new ModelAndView(REGISTRATION_VIEW_NAME, hashMap);
    }

    @PostMapping
    public Object save(final HttpServletRequest req, @Valid @ModelAttribute(FORM_MODEL_ATTRIBUTE_NAME) final UserAccountDto formModel,
                       final BindingResult result) {
        final Map<String, Object> hashMap = new HashMap<>();
        if (result.hasErrors()) {
            hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, formModel);
            return new ModelAndView(REGISTRATION_VIEW_NAME, hashMap);
        } else {
            String gRecaptchaResponse = req.getParameter(G_RECAPTCHA_RESPONSE);
            // Verify CAPTCHA.
            boolean valid = reCaptchaService.verify(gRecaptchaResponse);
            if (!valid) {
                // req.setAttribute("errorString", "Captcha invalid!");
                hashMap.put(ERROR, reCaptchaService.ERROR_STRING);
                return new ModelAndView(REGISTRATION_VIEW_NAME, hashMap);
            }
            final UserAccount entity = fromDtoConverter.apply(formModel);
            final String verifyKey = generateString(20);
            entity.setVerifyKey(verifyKey);
            try {
                userAccountService.save(entity);
                emailSenderService.sendVerifyKey(entity, verifyKey);
            } catch (DataIntegrityViolationException e) {
                hashMap.put(ERROR, e.getClass().getSimpleName());
                return new ModelAndView(REGISTRATION_VIEW_NAME, hashMap);
            } catch (MessagingException e) {
                hashMap.put(ERROR, MESSAGING_ERROR + verifyKey);
                return new ModelAndView(VERIFYING_VIEW_NAME, hashMap);
            }
            return REDIRECT;
        }
    }

    @GetMapping(VERIFY_WITH_LINK_MAPPING)
    public Object verifyWithLink(@PathVariable(name = "", required = false) final String verifyKey) {
        final boolean verified = isVerifyingComplete(verifyKey);
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(VERIFIED, verified);
        return new ModelAndView(PATH_VARIABLE_VERIFY_KEY, hashMap);
    }

    @RequestMapping(value = VERIFY_WITH_KEY_MAPPING, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView verifyWithKey(@ModelAttribute(FORM_MODEL_ATTRIBUTE_NAME) final UserAccountDto userAccountDto) {
        String verifyKey = userAccountDto.getVerifyKey();
        final boolean verified = isVerifyingComplete(verifyKey);
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(VERIFIED, verified);
        return new ModelAndView(VERIFYING_VIEW_NAME, hashMap);
    }

    private boolean isVerifyingComplete(final String verifyKey) {
        final UserAccount userAccount = userAccountService.findByVerifyKey(verifyKey);
        if (userAccount == null) {
            return false;
        } else {
            userAccount.setVerified(true);
            userAccountService.save(userAccount);
            LOGGER.info(VERIFYING_COMPLETE, userAccount.getEmail());
            return true;
        }
    }

    private String generateString(final int length) {
        String characters = ALPHABET;
        Random rnd = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);
    }
}