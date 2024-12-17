package com.example.taskmanagementsystems.api.validation.constant;

public class ValidationRegex {

  private ValidationRegex() {
  }

  public static final String REGEXP_EMAIL =
      "^(?=.{1,30}@.{2,15}\\..{2,3}$)(?=[^_@\\s-])(?=[^\\.]|\\.)"
          + "(?!.*\\.\\.)[A-Za-z0-9._-]{1,30}@[A-Za-z0-9-]{2,15}\\.[A-Za-z]{2,3}$";

  public static final String REGEXP_PASSWORD =
      "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[~!?@#$%^&*\\_\\-\\+\\(\\)\\[\\]\\{\\}>/<|\"\\\\'.:,;])"
          + "[A-Za-z\\d~!?@#$%^&*\\_\\-\\+\\(\\)\\[\\]\\{\\}>/<|\"\\\\'.:,;]{8,50}$";

}
