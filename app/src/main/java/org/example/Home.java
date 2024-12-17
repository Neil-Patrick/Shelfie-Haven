package org.example;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Home {

    public void HomeNativeKeyPressed(NativeKeyEvent e) {
        if ((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) && LayerManager.Login == 0) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.HomeOptions = Controls.SelectMenu(keys, 5, LayerManager.HomeOptions);
            Controls.clearScreen();
            PrintHomeUI();
        }
    }

    public static void PrintHomeUI() {
        AsciiUIDesign.HomePageUi();
        Controls.PrintSelectedOptions(AsciiUIDesign.Status(), LayerManager.HomeOptions, LayerManager.HomeOptions == 0);
        Controls.PrintSelectedOptions(AsciiUIDesign.BookCatalog(), LayerManager.HomeOptions, LayerManager.HomeOptions == 1);
        Controls.PrintSelectedOptions(AsciiUIDesign.SearchAndBorrow(), LayerManager.HomeOptions, LayerManager.HomeOptions == 2);
        Controls.PrintSelectedOptions(AsciiUIDesign.BorrowBookStatus(), LayerManager.HomeOptions, LayerManager.HomeOptions == 3);
        Controls.PrintSelectedOptions(AsciiUIDesign.Logout(), LayerManager.HomeOptions, LayerManager.HomeOptions == 4);
    }

    
}
