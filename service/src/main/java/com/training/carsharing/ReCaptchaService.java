package com.training.carsharing;

public interface ReCaptchaService {

    String ERROR_STRING = "Captcha is invalid!";

    boolean verify(String gRecaptchaResponse);
}
