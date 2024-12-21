package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Home {

    public void HomeNativeKeyPressed(NativeKeyEvent e) 
    {
        if ((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) ) 
        {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.HomeOptions = Controls.SelectMenu(keys, 5, LayerManager.HomeOptions);
            Controls.clearScreen();
            PrintHomeUI();
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) 
        {
            switch (LayerManager.HomeOptions) 
            {
                case 0:
                    App.currentEventState = Controls.EventState.STATUS;
                    App.status.PrintStatusUI();
                    break;
                case 1:
                    App.currentEventState = Controls.EventState.CATALOG;
                    Catalog.ListBooks();
                    break;
                case 2:
                    App.currentEventState = Controls.EventState.BORROW;
                    Catalog.ListBooks();
                    break;
                case 3:
                    App.currentEventState = Controls.EventState.BORROWEDLIST;
                    BorrowedList.borrowers = Queries.GetBorrowers();
                    BorrowedList.BorrowedBookListUi();
                    break;
                case 4: Exit(); break;
                default: break;    
            }
        }
    }

    public static void PrintHomeUI() 
    {
        AsciiUIDesign.HomePageUi();
        Controls.PrintOptionInCenter(AsciiUIDesign.Status(), LayerManager.HomeOptions, LayerManager.HomeOptions == 0, 60);
        Controls.PrintOptionInCenter(AsciiUIDesign.BookCatalog(), LayerManager.HomeOptions, LayerManager.HomeOptions == 1, 60);
        Controls.PrintOptionInCenter(AsciiUIDesign.SearchAndBorrow(), LayerManager.HomeOptions, LayerManager.HomeOptions == 2,  60);
        Controls.PrintOptionInCenter(AsciiUIDesign.BorrowBookStatus(), LayerManager.HomeOptions, LayerManager.HomeOptions == 3, 60);
        Controls.PrintOptionInCenter(AsciiUIDesign.Logout(), LayerManager.HomeOptions, LayerManager.HomeOptions == 4, 60);
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        AsciiUIDesign.MenuGuideUI();
    }

    public void Exit() 
    {
        try 
        {
            GlobalScreen.unregisterNativeHook();
        } 
        catch (NativeHookException nativeHookException) 
        {
            nativeHookException.printStackTrace();
        }

        //TODO: Add logout confirmation muna dito
        System.exit(0); 
        
    }

    

    
}
