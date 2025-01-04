package org.example;

class AdminLoginHandler implements LoginHandler {

    @Override
    public void displayLoginScreen() {
        Controls.clearScreen();
        Controls.PrintInCenter("═══════════════════════════Admin Login════════════════════════════");
        InputLayout("Enter Your Username: ", 43);
    }

    @Override
    public boolean verifyCredentials(String username, String password) {
        return username.equals("Admin") && password.equals("Admin");
    }

    @Override
    public void handleSuccessfulLogin() {
        Controls.clearScreen();
        AsciiUIDesign.LoginSuccessful();
    }

    @Override
    public void handleFailedLogin() {
        AsciiUIDesign.LoginFailed();
    }

    private static void InputLayout(String input, int leftPad) {
        String printResult = " ".repeat(Math.max(0, leftPad)) + input;
        System.out.print(printResult);
    }
}

class RegularUserLoginHandler implements LoginHandler {

    @Override
    public void displayLoginScreen() {
        Controls.clearScreen();
        Controls.PrintInCenter("═══════════════════════════User Login════════════════════════════");
        InputLayout("Enter Your Username: ", 43);
    }

    @Override
    public boolean verifyCredentials(String username, String password) {
        // Example logic: check against a list/database of regular users
        return username.equals("User") && password.equals("User123");
    }

    @Override
    public void handleSuccessfulLogin() {
        Controls.clearScreen();
        AsciiUIDesign.LoginSuccessful();
    }

    @Override
    public void handleFailedLogin() {
        AsciiUIDesign.LoginFailed();
    }

    private static void InputLayout(String input, int leftPad) {
        String printResult = " ".repeat(Math.max(0, leftPad)) + input;
        System.out.print(printResult);
    }
}





