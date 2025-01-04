package org.example;

interface LoginHandler {
    void displayLoginScreen();
    boolean verifyCredentials(String username, String password);
    void handleSuccessfulLogin();
    void handleFailedLogin();
}
