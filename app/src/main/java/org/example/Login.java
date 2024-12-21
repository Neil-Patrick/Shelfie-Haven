package org.example;
import java.io.Console;
import java.util.Scanner;
import java.util.ResourceBundle.Control;

import org.fusesource.jansi.AnsiConsole;
// import com.github.kwhat.jnativehook.GlobalScreen;
// import com.github.kwhat.jnativehook.NativeHookException;
// import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
// import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
//import java.lang.Thread;
// public class Login implements NativeKeyListener {
//import java.util.ResourceBundle.Control;

//     //TODO: Alising to after magawa ang project.
//     String username = "admin";
//     String password = "admin";

    

//     public void LoginNativeKeyPressed(NativeKeyEvent e) {
// 		// System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
// 		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
//             Controls.clearScreen();
//             LayerManager.Login = 1;
//             System.out.println("Press Enter to quit");
//             System.out.println("Press Any key to cancel");

//         } else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.Login == 1) {
//             try {
//                     GlobalScreen.unregisterNativeHook();
//                 } catch (NativeHookException nativeHookException) {
//                     nativeHookException.printStackTrace();
//                 }
//                 System.exit(0); 
//         } else if (e.getKeyCode() != NativeKeyEvent.VC_ENTER && LayerManager.Login == 1) {
//             Controls.clearScreen();
//             LayerManager.Login = 0;
            
//             AsciiUIDesign.LoginUi(); 

//         } else if ((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) && LayerManager.Login == 0) {
//             String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
//             LayerManager.LoginInput = Controls.SelectMenu(keys, 2, LayerManager.LoginInput);
//             Controls.clearScreen();
//             PrintLoginUI();
//         } else if ((e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE || e.getKeyCode() == NativeKeyEvent.VC_DELETE) && LayerManager.Login == 0) {
//             if (LayerManager.LoginInput == 0) {
//                 username = username.substring(0, username.length() - 1);
//             } else {
//                 password = password.substring(0, password.length() - 1);
//             } 
//             Controls.clearScreen();
//             PrintLoginUI();
//         } else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.Login == 0) {
//             ProceedLogin(username, password);
//             username = "";
//             password = "";
//         }
//     }

//     public void LoginnativeKeyTyped(NativeKeyEvent e) {
// 		if (Character.isLetter(e.getKeyChar())) {
//             char keyChar = e.getKeyChar();
//             if (NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Shift")) {
//                 keyChar = Character.toUpperCase(keyChar);
//             } else {
//                 keyChar = Character.toLowerCase(keyChar);
//             }
    
//             if (LayerManager.LoginInput == 0) {
//                 username += keyChar;
//             } else {
//                 password += keyChar;
//             }
//             Controls.clearScreen();
//             PrintLoginUI();
//         }
// 	}


//     public void PrintLoginUI() {
//         AsciiUIDesign.LoginUi();
//         Controls.PrintOptionInCenter(username, LayerManager.Login, LayerManager.LoginInput == 0, 45);
//         Controls.PrintOptionInCenter("═══════════════════════════Username════════════════════════════", LayerManager.Login, LayerManager.LoginInput == 0, 45);
//         Controls.PrintInCenter("");
//         Controls.PrintInCenter("");
//         Controls.PrintInCenter("");
//         Controls.PrintOptionInCenter(password, LayerManager.Login, LayerManager.LoginInput == 1, 45);
//         Controls.PrintOptionInCenter("═══════════════════════════Password════════════════════════════", LayerManager.Login, LayerManager.LoginInput == 1, 45);


//     }

//     public void ProceedLogin(String username, String password) {
//         Controls.clearScreen();
//         if (username.equals("admin") && password.equals("admin")) {
//             //TODO: Pagandahin tong Login Successful at igitna
//             System.out.println("Login Successful");
//             try {
//                 Thread.sleep(1000); // 1-second delay
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//             Controls.clearScreen();
//             Home.PrintHomeUI();
//             App.currentEventState = Controls.EventState.HOME;
//         } else {
//             //TODO: Pagandahin tong Login failed at igitna

//             System.out.println("Login Failed");
//         }
//     }
// }

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
