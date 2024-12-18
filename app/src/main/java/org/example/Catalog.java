package org.example;

import java.io.Console;
import java.util.List;

import org.checkerframework.checker.units.qual.g;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Catalog {

    
    private static List<Books> booksList;
    private String title;
    private String author;
    private String genre;
    private String location;
    private String date;
    private int quantity;

    public void CatalogNativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            Controls.isCtrlPressed = true;
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && LayerManager.CatalogLayer == 0) {
            Controls.clearScreen();
            App.currentEventState = Controls.EventState.HOME;
            LayerManager.HomeOptions = 0;
            Home.PrintHomeUI();
            
        }else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && LayerManager.CatalogLayer == 1) {
            LayerManager.CatalogLayer = 0;
            ListBooks();
        }
         else if (Controls.isCtrlPressed && e.getKeyCode() == NativeKeyEvent.VC_EQUALS) {
            Controls.clearScreen();
            LayerManager.CatalogLayer = 1;
            AddBook();
            
        } else if ((e.getKeyCode() == NativeKeyEvent.VC_LEFT || e.getKeyCode() == NativeKeyEvent.VC_RIGHT) && LayerManager.CatalogLayer == 0) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
            LayerManager.BookIndex = Controls.SelectMenu(keys, booksList.size(), LayerManager.BookIndex);
            ListBooks();
        } else if ((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN && LayerManager.CatalogLayer == 1) ) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.BookInput = Controls.SelectMenu(keys, 6, LayerManager.BookInput);
            AddBook();
        } 
        
    }

    public void CatalogNativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            Controls.isCtrlPressed = false;
        }
    }

    public static void ListBooks() {
        Controls.clearScreen();
        AsciiUIDesign.BookCatalogUi();
        booksList = Queries.GetBooks();
        
        Controls.PrintInCenter("");
        Controls.PrintOptionInCenter("ID:           " + booksList.get(LayerManager.BookIndex).getId(), 150, false, 60);
        Controls.PrintOptionInCenter("Title:        " + booksList.get(LayerManager.BookIndex).getTitle(), 150, false, 60);
        Controls.PrintOptionInCenter("Author:       " + booksList.get(LayerManager.BookIndex).getAuthor(), 150, false, 60);
        Controls.PrintOptionInCenter("Genre:        " + booksList.get(LayerManager.BookIndex).getGenre(), 150, false, 60);
        Controls.PrintOptionInCenter("Location:     " + booksList.get(LayerManager.BookIndex).getlocation(), 150, false, 60);
        Controls.PrintOptionInCenter("Date:         " + booksList.get(LayerManager.BookIndex).getDate(), 150, false, 60);
        Controls.PrintOptionInCenter("Quantity:     " + booksList.get(LayerManager.BookIndex).getQuantity(), 150, false, 60);
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    
        String greenColor = "\033[32m";
        String resetColor = "\033[0m";
        Controls.PrintInCenter(greenColor + "<<< " + (LayerManager.BookIndex + 1) + "/" + (booksList.size()) + " >>>" + resetColor);
    }

    public void AddBook() {
        Controls.clearScreen();
        AsciiUIDesign.AddBookUi();
        Controls.PrintOptionInCenter("Title:                " + title, 150, LayerManager.BookInput == 0, 60);
        Controls.PrintOptionInCenter("Author:               " + author, 150, LayerManager.BookInput == 1, 60);
        Controls.PrintOptionInCenter("Genre:                " + genre, 150, LayerManager.BookInput == 2, 60);
        Controls.PrintOptionInCenter("Location:             " + location, 150, LayerManager.BookInput == 3, 60);
        Controls.PrintOptionInCenter("Date(YYYY-MM-DD):     " + date, 150, LayerManager.BookInput == 4, 60);
        Controls.PrintOptionInCenter("Quantity:             " + quantity, 150, LayerManager.BookInput == 5, 60);

        
    }
}
