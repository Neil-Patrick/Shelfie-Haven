package org.example;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Home {

    public void HomeNativeKeyPressed(NativeKeyEvent e) {
        if ((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) ) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.HomeOptions = Controls.SelectMenu(keys, 5, LayerManager.HomeOptions);
            Controls.clearScreen();
            PrintHomeUI();
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
            switch (LayerManager.HomeOptions) {
                case 1:
                    App.currentEventState = Controls.EventState.CATALOG;
                    Catalog.ListBooks();
                    break;
                case 4:
                    //Logout
                    App.currentEventState = Controls.EventState.LOGIN;
                    Logout();
                    break;
            
                default:
                    break;
            }
        }
    }

    public static void PrintHomeUI() {
        AsciiUIDesign.HomePageUi();
        Controls.PrintOptionInCenter(AsciiUIDesign.Status(), LayerManager.HomeOptions, LayerManager.HomeOptions == 0, 60);
        Controls.PrintOptionInCenter(AsciiUIDesign.BookCatalog(), LayerManager.HomeOptions, LayerManager.HomeOptions == 1, 60);
        Controls.PrintOptionInCenter(AsciiUIDesign.SearchAndBorrow(), LayerManager.HomeOptions, LayerManager.HomeOptions == 2,  60);
        Controls.PrintOptionInCenter(AsciiUIDesign.BorrowBookStatus(), LayerManager.HomeOptions, LayerManager.HomeOptions == 3, 60);
        Controls.PrintOptionInCenter(AsciiUIDesign.Logout(), LayerManager.HomeOptions, LayerManager.HomeOptions == 4, 60);
    }

    public void Logout() {
        Controls.clearScreen();
        LayerManager.Login = 0;
        //TODO: Add Threaded delay for logging out message
        App.login.PrintLoginUI();
    }

    

    
}
