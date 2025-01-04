package org.example;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Status 
{
    
    public void StatusNativeKeyPressed(NativeKeyEvent e) 
    {
        switch (e.getKeyCode()) 
        {
            case NativeKeyEvent.VC_ESCAPE: GotoHome(); break;
            default: break;  
        }
    }

    public void PrintStatusUI() 
    {
        Controls.clearScreen(); 
        AsciiUIDesign.BookStatusUi();
        Controls.PrintInCenter("Total Books:           " + Queries.CountBooks());
        Controls.PrintInCenter("Total Borrowed Books:  " + Queries.CountBorrowedBooks());
        AsciiUIDesign.StatusGuideUI();
    }
    
    public void GotoHome() 
    {
        Controls.clearScreen();
        App.currentEventState = Controls.EventState.HOME;
        Home.PrintHomeUI();
    }
}
