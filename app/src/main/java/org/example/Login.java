package org.example;
import java.io.Console;
import java.util.Scanner;
import java.util.ResourceBundle.Control;

import org.fusesource.jansi.AnsiConsole;

public class Login 
{
    
    public static String Username;
    public static String Password;
    public static Scanner scanner = new Scanner(System.in);

    public Login() 
    {
        AnsiConsole.systemInstall();
        char verifyCred;
        
        do
        {
            Controls.clearScreen();
            System.out.flush();
            Controls.PrintInCenter("═══════════════════════════Username════════════════════════════");
            //Controls.PrintInCenter("Enter Your Username: ");
            InputLayout("Enter Your Username: ", 43);
            Username = scanner.next();

            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");

            Controls.PrintInCenter("═══════════════════════════Password════════════════════════════");
            InputLayout("Enter Your Password: ", 43);
            Password = scanner.next();

            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");

            Controls.clearScreen();

            Controls.PrintInCenter("═══════════════════════════Confirm Login════════════════════════════");
            Controls.PrintInCenter("Are your credentials correct? (y/n)");
            Controls.PrintInCenter("");
            InputLayout("Enter Input: ", 43);
            verifyCred = scanner.next().charAt(0);
            if (verifyCred == 'n')
            {
                Username = "";
                Password = "";
            }
            
        }
        while (verifyCred == 'n');
        
        if (verifyCred != 'y')
        {
            Controls.clearScreen();
            AsciiUIDesign.InvalidInputUI();
            try
            {
                Thread.sleep(1500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Controls.clearScreen();

            new Login();
        }
        else
        {
            CheckLogin();
        }
        
    }

    public static void InputLayout(String input, int leftPad)
    {
        String printResult = " ".repeat(Math.max(0, leftPad)) + input;
        System.out.print(printResult);
    }

    public static void CheckLogin()
    {
        Controls.clearScreen();
        if (Username.equals("Admin") && Password.equals("Admin"))
        {
            System.out.flush();
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            AsciiUIDesign.LoginSuccessful();
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Controls.clearScreen();
        }
        else
        {
            AsciiUIDesign.LoginFailed();
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Controls.clearScreen();

            new Login();
        }
        
    }
}
