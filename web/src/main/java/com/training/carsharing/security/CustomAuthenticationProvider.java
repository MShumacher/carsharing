package com.training.carsharing.security;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.model.impl.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger("loginLogger");

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String email = authentication.getPrincipal().toString();
        final String password = authentication.getCredentials().toString();
        LOGGER.info("user {} try to log in.", email);
        UserAccount user = userAccountService.findByEmail(email);
        if (user == null) {
            LOGGER.info("user {} doesn't exist.");
            throw new BadCredentialsException("1000");

        } else {
            // если гость не подтвердил e-mail доступа ему нет
            if ((user != null) && (!user.isVerified())) {
                throw new DisabledException("1001");
            }
            if (!userAccountService.isPasswordCorrect(user, password)) {
                LOGGER.info("incorrect password for user {}", email);
                throw new BadCredentialsException("1000");
            }

            final List<SimpleGrantedAuthority> roles = Arrays
                    .asList(new SimpleGrantedAuthority(user.getRole().toString()));
            LOGGER.info("user {} with role {} has been logged.", user.getEmail(), user.getRole());
            return new UsernamePasswordAuthenticationToken(email, password, roles);
        }

        /*
         * if (user.getLocked()) { // locked user. если юзер забанен throw new
         * DisabledException("1001"); }
         */
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
