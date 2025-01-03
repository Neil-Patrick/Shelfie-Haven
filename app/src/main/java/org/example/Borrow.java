package org.example;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.A;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Borrow implements NativeKeyListener 
{

    private static List<Books> booksList = Queries.GetBooks();

    public String searchString = "";
    public static String Fname = "";
    public static String Mname = "";
    public static String Lname = "";
    public static Date dateBorrowed = new Date(System.currentTimeMillis());

    public void BorrowNativeKeyPressed(NativeKeyEvent e) 
    {
        switch (e.getKeyCode()) 
        {
            case NativeKeyEvent.VC_CONTROL:
                Controls.isCtrlPressed = true;
                break;
            case NativeKeyEvent.VC_BACKSPACE:
                switch (LayerManager.BorrowLayer) 
                {
                    case 1: BackspaceSearch(); break;   
                    case 2: BackspaceName(); break;
                    default: break;
                }
                break;
            case NativeKeyEvent.VC_ENTER:
                switch (LayerManager.BorrowLayer) 
                {
                    case 0: GetFullName(); break;  
                    case 1: EnterSearchedKeyword(); break;    
                    case 2: ShowConfirmation(); break;    
                    case 3: UploadToDatabase(); 
                    default: break;
                        
                }
                break;
            case NativeKeyEvent.VC_ESCAPE:
                switch (LayerManager.BorrowLayer) 
                {
                    case 0: GotoHome(); break;   
                    case 1: GotoBorrow();  
                    case 2: GotoBorrow(); break;    
                    case 3: GetFullName(); break;
                    default: break;  
                }
                break;

            case NativeKeyEvent.VC_LEFT:
                switch (LayerManager.BorrowLayer) 
                {
                    case 0: Select(e); break;
                    default: break;  
                }
                break;
            case NativeKeyEvent.VC_RIGHT:
                switch (LayerManager.BorrowLayer) 
                {
                    case 0: Select(e); break;
                    default: break;      
                }
                break;
            case NativeKeyEvent.VC_UP:
                switch (LayerManager.BorrowLayer) 
                {
                    case 2: SelectNameInput(e); break;
                    default: break;    
                }
                break;

            case NativeKeyEvent.VC_DOWN:
                switch (LayerManager.BorrowLayer) 
                {
                    case 2: SelectNameInput(e); break;
                    default: break;
                }
                break;
            case NativeKeyEvent.VC_B:
                switch (LayerManager.BorrowLayer) 
                {
                    case 0:
                        if (Controls.isCtrlPressed) 
                        {
                            GotoSearch();
                        }
                        break;
                
                    default:
                        break;
                }
                break;
            default:
                break;
        }



        
    }

    public void BorrowNativeKeyReleased(NativeKeyEvent e) 
    {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) 
        {
            Controls.isCtrlPressed = false;
        }
    }

    public void BorrowNativeKeyTyped(NativeKeyEvent e) 
    {
        switch (LayerManager.BorrowLayer) 
        {
            // nasa search part
            case 1: TypeSearch(e); break; 
             // nasa enter name part   
            case 2: TypeName(e); break;   
            default: break;
        }
    }

    public void BackspaceSearch() 
    {
        if (searchString.length() > 0) 
        {
            searchString = searchString.substring(0, searchString.length() - 1);
            GotoSearch();
        }
    }
    public void BackspaceName() 
    {
        if (LayerManager.nameInput == 0) 
        {
            if (Fname.length() > 0) 
            {
                Fname = Fname.substring(0, Fname.length() - 1);
            }
        } 
        else if (LayerManager.nameInput == 1) 
        {
            if (Mname.length() > 0) 
            {
                Mname = Mname.substring(0, Mname.length() - 1);
            }
        } 
        else if (LayerManager.nameInput == 2) 
        {
            if (Lname.length() > 0) 
            {
                Lname = Lname.substring(0, Lname.length() - 1);
            }
        }
        GetFullName();
    }

    public static void Select(NativeKeyEvent e) 
    {
        Controls.clearScreen();
        String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
        LayerManager.BookIndex = Controls.SelectMenu(keys, booksList.size(), LayerManager.BookIndex);
        Borrow.ListBooks();

    }

    public static void SelectNameInput(NativeKeyEvent e) 
    {
        Controls.clearScreen();
        String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
        LayerManager.nameInput = Controls.SelectMenu(keys, 3, LayerManager.nameInput);
        Borrow.GetFullName();

    }

    

    public void TypeSearch(NativeKeyEvent e) 
    {
        if ((Character.isLetter(e.getKeyChar()) || Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '-' || e.getKeyChar() == ' ') && !Controls.isCtrlPressed) 
        { 
            char keyChar = e.getKeyChar();
            if (NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Shift")) 
            {
                keyChar = Character.toUpperCase(keyChar);
            } 
            else 
            {
                keyChar = Character.toLowerCase(keyChar);
            }
    
            searchString += keyChar;
            GotoSearch();
        }
    }

    public void TypeName(NativeKeyEvent e) 
    {
        if ((Character.isLetter(e.getKeyChar()) || Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '-' || e.getKeyChar() == ' ') && !Controls.isCtrlPressed) 
        { 
            char keyChar = e.getKeyChar();
            if (NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Shift")) 
            {
                keyChar = Character.toUpperCase(keyChar);
            } 
            else 
            {
                keyChar = Character.toLowerCase(keyChar);
            }

            switch (LayerManager.nameInput) 
            {
                case 0: Fname += keyChar; break;
                case 1: Mname += keyChar; break;
                case 2: Lname += keyChar; break;
                default: break;
            }
            GetFullName();
        }
    }

    public void GotoBorrow() 
    {
        LayerManager.BorrowLayer = 0;
        clearFields();
        booksList = Queries.GetBooks();
        Borrow.ListBooks();
    }
    public void GotoHome() 
    {
        Controls.clearScreen();
        LayerManager.BookIndex = 0;
        LayerManager.HomeOptions = 0;
        App.currentEventState = Controls.EventState.HOME;
        booksList = Queries.GetBooks();
        Home.PrintHomeUI();
    }

    public void GotoSearch() 
    {
        LayerManager.BorrowLayer = 1;
        Controls.clearScreen();
        Controls.PrintOptionInCenter("Search:  " + searchString, 150, true, 40);
        
    }

    public void EnterSearchedKeyword() 
    {
        LayerManager.BorrowLayer = 0;
        LayerManager.BookIndex = 0;
        Controls.clearScreen();
        booksList = Queries.GetSearchedBooks(searchString);
        ListBooks();
    }

    public void UploadToDatabase() 
    {
       
        boolean success = Queries.AddBorrower(Fname, Mname, Lname, booksList.get(LayerManager.BookIndex).getId(), dateBorrowed);
        
        if (success) 
        {
            AsciiUIDesign.SuccessfulProcess();
            try 
            {
                Thread.sleep(1000); // 1-second delay
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        } 
        else 
        {
            AsciiUIDesign.FailedProcess();
            try 
            {
                Thread.sleep(1000); // 1-second delay
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
        LayerManager.BorrowLayer = 0;
        LayerManager.BookIndex = 0;
        booksList = Queries.GetBooks();
        Borrow.ListBooks();
    }
    

    public void ShowConfirmation() 
    {
        LayerManager.BorrowLayer = 3;
        Controls.clearScreen();
        AsciiUIDesign.BorrowBookUi();
        Controls.PrintOptionInCenter("Title :  " + booksList.get(LayerManager.BookIndex).getTitle(), 150, false, 40);
        Controls.PrintOptionInCenter("Author :  " + booksList.get(LayerManager.BookIndex).getAuthor(), 150, false, 40);
        Controls.PrintOptionInCenter("Date Borrowed :  " + dateBorrowed.toString(), 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("First Name:  " + Fname, 150, false, 40);
        Controls.PrintOptionInCenter("Middle Name:  " + Mname, 150, false, 40);
        Controls.PrintOptionInCenter("Last Name:  " + Lname, 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("Review your inputs.", 150, false, 40);
        AsciiUIDesign.SearchGuideUI();
    }
    public void clearFields() 
    {
        searchString = "";
        Fname = "";
        Mname = "";
        Lname = "";
    }

    public static void GetFullName() 
    {
        LayerManager.BorrowLayer = 2;
        Controls.clearScreen();
        AsciiUIDesign.AvailableBooksUI();;
        
        Controls.PrintOptionInCenter("Title :  " + booksList.get(LayerManager.BookIndex).getTitle(), 150, false, 40);
        Controls.PrintOptionInCenter("Author :  " + booksList.get(LayerManager.BookIndex).getAuthor(), 150, false, 40);
        Controls.PrintOptionInCenter("Date Borrowed :  " + dateBorrowed.toString(), 150, false, 40);
        Controls.PrintOptionInCenter("Quantity :  " +  booksList.get(LayerManager.BookIndex).getQuantity(), 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("", 150, false, 40);
        Controls.PrintOptionInCenter("First Name:  " + Fname, 150, LayerManager.nameInput == 0, 40);
        Controls.PrintOptionInCenter("Middle Name:  " + Mname, 150, LayerManager.nameInput == 1, 40);
        Controls.PrintOptionInCenter("Last Name:  " + Lname, 150, LayerManager.nameInput == 2, 40);
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        AsciiUIDesign.ConfirmBorrowGuideUI();
    }
    public static void ListBooks() 
    {
        Controls.clearScreen();
        AsciiUIDesign.AvailableBooksUI();
        if (booksList.isEmpty()) 
        {
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("No books found in the catalog.");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
        } 
        else 
        {
            Controls.PrintInCenter("");
            Controls.PrintOptionInCenter("ID:           " + booksList.get(LayerManager.BookIndex).getId(), 150, false, 40);
            Controls.PrintOptionInCenter("Title:        " + booksList.get(LayerManager.BookIndex).getTitle(), 150, false, 40);
            Controls.PrintOptionInCenter("Author:       " + booksList.get(LayerManager.BookIndex).getAuthor(), 150, false, 40);
            Controls.PrintOptionInCenter("Genre:        " + booksList.get(LayerManager.BookIndex).getGenre(), 150, false, 40);
            Controls.PrintOptionInCenter("Location:     " + booksList.get(LayerManager.BookIndex).getlocation(), 150, false, 40);
            Controls.PrintOptionInCenter("Date:         " + booksList.get(LayerManager.BookIndex).getDate(), 150, false, 40);
            Controls.PrintOptionInCenter("Quantity:     " + booksList.get(LayerManager.BookIndex).getQuantity(), 150, false, 40);
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
    
            String greenColor = "\033[32m";
            String resetColor = "\033[0m";
            Controls.PrintInCenter(greenColor + "<<< " + (LayerManager.BookIndex + 1) + "/" + (booksList.size()) + " >>>" + resetColor);
            AsciiUIDesign.BorrowGuideUI();
        }
    }



}
