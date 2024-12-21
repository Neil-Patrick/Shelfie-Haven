package org.example;

import java.io.Console;
import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class BorrowedList 
{
    public static List<Borrower> borrowers = Queries.GetBorrowers();


    public void BorrowedBookListNativeKeyPressed(NativeKeyEvent e) 
    {
        switch (e.getKeyCode()) 
        {
            case NativeKeyEvent.VC_UP: Select(e); break;    
            case NativeKeyEvent.VC_DOWN: Select(e); break;  
            case NativeKeyEvent.VC_LEFT: SelectPage(e); break;    
            case NativeKeyEvent.VC_RIGHT: SelectPage(e); break;   
            case NativeKeyEvent.VC_ENTER:
                switch (LayerManager.BorrowedListLayer) 
                {
                    case 0: ReturnBookConfirmation(); break;    
                    case 1: ReturnBook(); break;  
                    default: break;
                        
                }
                break;
            case NativeKeyEvent.VC_ESCAPE:
                switch (LayerManager.BorrowedListLayer) 
                {
                    case 0: GotoHome(); break;    
                    case 1: BorrowedBookListUi(); break;
                    default: break;   
                }
                break;
            default:
                break;
        }
    }

    private void Select(NativeKeyEvent e) 
    {
        Controls.clearScreen();
        String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";

        int totalPages = (borrowers.size() > 0) ? (int) Math.ceil((double) borrowers.size() / 10) : 0;
        int numberOfOptions = (totalPages == LayerManager.currentPage + 1) ? borrowers.size() % 10 : 10;
        LayerManager.currentSelection = Controls.SelectMenu(keys, numberOfOptions, LayerManager.currentSelection);
        BorrowedBookListUi();
    }

    private void SelectPage(NativeKeyEvent e) 
    {
        Controls.clearScreen();
        String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
        int totalPages = (borrowers.size() > 0) ? (int) Math.ceil((double) borrowers.size() / 10.0) : 1;
        LayerManager.currentPage = Controls.SelectMenu(keys, totalPages, LayerManager.currentPage);
        LayerManager.currentSelection = 0;
        BorrowedBookListUi();
    }

    public static void BorrowedBookListUi() 
    {
        LayerManager.BorrowedListLayer = 0;
        Controls.clearScreen();
        AsciiUIDesign.BorrowBookUi();
        displayPage();
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        AsciiUIDesign.BorrowedBookGuideUI();

    }

    public void ReturnBookConfirmation() 
    {
        
        if (!borrowers.isEmpty())
            {
                LayerManager.BorrowedListLayer = 1;
                Borrower borrower = borrowers.get(LayerManager.currentPage * 10 + LayerManager.currentSelection);
                Controls.clearScreen();
                Controls.PrintInCenter("Are you sure you want to return this book?");
                Controls.PrintInCenter("");
                Controls.PrintOptionInCenter("Title: " + borrower.getTitle(), 150, false, 40);
                Controls.PrintOptionInCenter("Borrower: " + borrower.getName(), 150, false, 40);
                Controls.PrintOptionInCenter("Date Borrowed: " + borrower.getDateBorrowed().toString(), 150, false, 40);
                Controls.PrintInCenter("");
                Controls.PrintInCenter("");
                Controls.PrintInCenter("");
                Controls.PrintInCenter("");
                AsciiUIDesign.ConfirmReturnGuideUI();
            }
            else
            {
                Controls.clearScreen();
                Controls.PrintInCenter("");
                Controls.PrintInCenter("");
                AsciiUIDesign.NoBookUI();
                Controls.PrintInCenter("");
                Controls.PrintInCenter("");
                Controls.PrintInCenter("");
                Controls.PrintInCenter("");
                AsciiUIDesign.ConfirmReturnGuideUI();
            }
    }

    public void GotoHome() 
    {
        Controls.clearScreen();
        LayerManager.BorrowedListLayer = 0;
        LayerManager.currentPage = 0;
        LayerManager.currentSelection = 0;
        App.currentEventState = Controls.EventState.HOME;
        Home.PrintHomeUI();
    }

    public void ReturnBook() 
    {
        Borrower borrower = borrowers.get(LayerManager.currentPage * 10 + LayerManager.currentSelection);
        LayerManager.BorrowedListLayer = 0;
        LayerManager.currentSelection = 0;
        LayerManager.currentPage = 0;
        Queries.ReturnBook(borrower);
        borrowers = Queries.GetBorrowers();
        BorrowedBookListUi();
    }

    private static void displayPage() 
    {
        int pageSize = 10;
        int totalPages = (borrowers.size() > 0) ? (int) Math.ceil((double) borrowers.size() / 10.0) : 1;

        int start = LayerManager.currentPage * pageSize;
        int end = Math.min(start + pageSize, borrowers.size());

        
        Controls.PrintInCenter("< Page " + (LayerManager.currentPage + 1) + " of " + totalPages + " >\n");
        Controls.PrintInCenter("---------------------------------------------------------------------------");
        Controls.PrintInCenter("ID  Name                 Title                Date Borrowed");
        Controls.PrintInCenter("---------------------------------------------------------------------------");

        for (int i = start; i < end; i++) 
        {
            String id = String.format("%-3s", borrowers.get(i).getId()); // Ensure ID is 3 characters wide, left-aligned
            String name = borrowers.get(i).getName();
            String title = borrowers.get(i).getTitle();
            String date = borrowers.get(i).getDateBorrowed().toString(); // Format date accordingly if needed

            // Truncate name, title, and date if they exceed 20 characters, adding "..." at the end
            if (name.length() > 20) name = name.substring(0, 17) + "...";
            if (title.length() > 20) title = title.substring(0, 17) + "...";
            if (date.length() > 20) date = date.substring(0, 17) + "...";

            // Print the output with the specified width formatting
            if (i - start == LayerManager.currentSelection) 
            {
                Controls.PrintOptionInCenter(String.format("-> %-3s %-20s %-20s %-20s", id, name, title, date), 150, true, 42);
            } 
            else 
            {
                Controls.PrintOptionInCenter(String.format("   %-3s %-20s %-20s %-20s", id, name, title, date), 150, false, 42);
            }
        }

        
    }
}
