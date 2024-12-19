package org.example;

import java.util.List;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Borrow {

    private static List<Books> booksList = Queries.GetBooks();

    public String searchString = "";
    public void BorrowNativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_CONTROL:
                Controls.isCtrlPressed = true;
                break;
            case NativeKeyEvent.VC_ENTER:
                switch (LayerManager.BorrowLayer) {
                    case 1:
                        EnterSearchedKeyword();
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            case NativeKeyEvent.VC_ESCAPE:
                switch (LayerManager.BorrowLayer) {
                    case 0:
                        GotoHome();
                        break;
                    case 1:
                        GotoBorrow();
                    default:
                        break;
                }
                break;

            case NativeKeyEvent.VC_LEFT:
                switch (LayerManager.BorrowLayer) {
                    case 0:
                        Select(e);
                        break;
                
                    default:
                        break;
                }
                break;
            case NativeKeyEvent.VC_RIGHT:
                switch (LayerManager.BorrowLayer) {
                    case 0:
                        Select(e);
                        break;
                
                    default:
                        break;
                }
                break;
            
            case NativeKeyEvent.VC_F:
                if (Controls.isCtrlPressed) {
                    GotoSearch();

                }
                break;
            default:
                break;
        }



        
    }

    public void BorrowNativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            Controls.isCtrlPressed = false;
        }
    }

    public void BorrowNativeKeyTyped(NativeKeyEvent e) {
        switch (LayerManager.BorrowLayer) {
            case 1:// nasa search part
                TypeSearch(e);
                break;
            case 2:// nasa enter name part

                break;
            default:
                break;
        }
    }

    public static void Select(NativeKeyEvent e) {
        Controls.clearScreen();
        String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
        LayerManager.BookIndex = Controls.SelectMenu(keys, booksList.size(), LayerManager.BookIndex);
        Borrow.ListBooks();

    }

    

    public void TypeSearch(NativeKeyEvent e) {
        if ((Character.isLetter(e.getKeyChar()) || Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '-' || e.getKeyChar() == ' ') && !Controls.isCtrlPressed) { 
            char keyChar = e.getKeyChar();
            if (NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Shift")) {
                keyChar = Character.toUpperCase(keyChar);
            } else {
                keyChar = Character.toLowerCase(keyChar);
            }
    
            searchString += keyChar;
            GotoSearch();
        }
    }

    public void GotoBorrow() {
        LayerManager.BorrowLayer = 0;
        booksList = Queries.GetBooks();
        Borrow.ListBooks();
    }
    public void GotoHome() {
        Controls.clearScreen();
        LayerManager.BookIndex = 0;
        LayerManager.HomeOptions = 0;
        App.currentEventState = Controls.EventState.HOME;
        booksList = Queries.GetBooks();
        Home.PrintHomeUI();
    }

    public void GotoSearch() {
        LayerManager.BorrowLayer = 1;
        Controls.clearScreen();
        Controls.PrintOptionInCenter("Search:  " + searchString, 150, true, 40);
        
    }

    public void EnterSearchedKeyword() {
        LayerManager.BorrowLayer = 0;
        Controls.clearScreen();
        booksList = Queries.GetSearchedBooks(searchString);
        ListBooks();
    }

    public static void ListBooks() {
        Controls.clearScreen();
        AsciiUIDesign.BookCatalogUi();
    
        if (booksList.isEmpty()) {
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
        } else {
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
        }
    }

}
