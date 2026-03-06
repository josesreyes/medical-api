package com.jsrdev.medapi.usecase.user;

public interface AuthenticateUser {

    String execute(String login, String password);
}
