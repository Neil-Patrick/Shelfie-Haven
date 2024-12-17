package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.lang.Thread;
public class Login implements NativeKeyListener {

    String username = "";
    String password = "";

    

    public void LoginNativeKeyPressed(NativeKeyEvent e) {
		// System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            Controls.clearScreen();
            LayerManager.Login = 1;
            System.out.println("Press Enter to quit");
            System.out.println("Press Any key to cancel");

        } else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.Login == 1) {
            try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException nativeHookException) {
                    nativeHookException.printStackTrace();
                }
                System.exit(0); 
        } else if (e.getKeyCode() != NativeKeyEvent.VC_ENTER && LayerManager.Login == 1) {
            Controls.clearScreen();
            LayerManager.Login = 0;
            AsciiUIDesign.LoginUi(); 

        } else if ((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) && LayerManager.Login == 0) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.LoginInput = Controls.SelectMenu(keys, 2, LayerManager.LoginInput);
            Controls.clearScreen();
            PrintLoginUI();
        } else if ((e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE || e.getKeyCode() == NativeKeyEvent.VC_DELETE) && LayerManager.Login == 0) {
            if (LayerManager.LoginInput == 0) {
                username = username.substring(0, username.length() - 1);
            } else {
                password = password.substring(0, password.length() - 1);
            } 
            Controls.clearScreen();
            PrintLoginUI();
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.Login == 0) {
            ProceedLogin(username, password);
            username = "";
            password = "";
        }
    }

    public void LoginnativeKeyTyped(NativeKeyEvent e) {
		if (Character.isLetter(e.getKeyChar())) {
            char keyChar = e.getKeyChar();
            if (NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Shift")) {
                keyChar = Character.toUpperCase(keyChar);
            } else {
                keyChar = Character.toLowerCase(keyChar);
            }
    
            if (LayerManager.LoginInput == 0) {
                username += keyChar;
            } else {
                password += keyChar;
            }
            Controls.clearScreen();
            PrintLoginUI();
        }
	}


    public void PrintLoginUI() {
        AsciiUIDesign.LoginUi();
        Controls.PrintSelectedOptions("Username: " + username, LayerManager.Login, LayerManager.LoginInput == 0);
        Controls.PrintSelectedOptions("Password: " + password, LayerManager.Login, LayerManager.LoginInput == 1);
    }

    public void ProceedLogin(String username, String password) {
        Controls.clearScreen();
        if (username.equals("admin") && password.equals("admin")) {
            //TODO: Pagandahin tong Login Successful at igitna
            System.out.println("Login Successful");
            try {
                Thread.sleep(1000); // 1-second delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Controls.clearScreen();
            AsciiUIDesign.HomePageUi();
            App.currentEventState = Controls.EventState.HOME;
        } else {
            //TODO: Pagandahin tong Login Successful at igitna

            System.out.println("Login Failed");
        }
    }
}
