package org.example;
import java.io.Console;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ResourceBundle.Control;

import org.fusesource.jansi.AnsiConsole;

public class Login {

    public static String Username;
    public static String Password;
    public static Scanner scanner = new Scanner(System.in);
    private LoginHandler loginHandler;

    public Login() {
        AnsiConsole.systemInstall();
        char verifyCred;


        selectLoginType();

        do {
            loginHandler.displayLoginScreen();

            // Get the credentials
            Username = scanner.next();
            Controls.PrintInCenter("");
            Controls.PrintInCenter("═══════════════════════════Password════════════════════════════");
            InputLayout("Enter Your Password: ", 43);
            Password = scanner.next();

            Controls.clearScreen();

            Controls.PrintInCenter("═══════════════════════════Confirm Login════════════════════════════");
            Controls.PrintInCenter("Are your credentials correct? (y/n)");
            verifyCred = scanner.next().charAt(0);
            if (verifyCred == 'n') {
                Username = "";
                Password = "";
            }

        } while (verifyCred == 'n');

        if (verifyCred != 'y') {
            Controls.clearScreen();
            AsciiUIDesign.InvalidInputUI();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Controls.clearScreen();

            new Login();
        } else {
            CheckLogin();
        }
    }

    public static void InputLayout(String input, int leftPad) {
        String printResult = " ".repeat(Math.max(0, leftPad)) + input;
        System.out.print(printResult);
    }

    public void CheckLogin() {
        if (loginHandler.verifyCredentials(Username, Password)) {
            loginHandler.handleSuccessfulLogin();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Controls.clearScreen();
        } else {
            loginHandler.handleFailedLogin();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Controls.clearScreen();

            new Login();
        }
    }

    private void selectLoginType() {
    // Let the user select the login type
    while (true) {
        Controls.clearScreen();
        Controls.PrintInCenter("Select login type: ");
        Controls.PrintInCenter("[1] Admin");
        Controls.PrintInCenter("[2] Basic");

        try {
            int choice = scanner.nextInt(); 
    
            if (choice == 1) {
                loginHandler = new AdminLoginHandler(); 
                Controls.admin = 1;
                break;
            } else if (choice == 2) {
                loginHandler = new RegularUserLoginHandler(); 
                break; 
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
                scanner.nextLine(); 

            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number (1 or 2).");
            scanner.nextLine(); 
        }
    }
}

    
}
