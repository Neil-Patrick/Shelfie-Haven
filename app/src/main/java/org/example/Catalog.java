package org.example;

import java.io.Console;
import java.util.List;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Catalog {

    
    private static List<Books> booksList;

    public void CatalogNativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            Controls.isCtrlPressed = true;
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            Controls.clearScreen();
            App.currentEventState = Controls.EventState.HOME;
            LayerManager.HomeOptions = 0;
            Home.PrintHomeUI();
            
        }
         else if (Controls.isCtrlPressed && e.getKeyCode() == NativeKeyEvent.VC_EQUALS) {
            Controls.clearScreen();
            System.out.println("Add Book");
            
        } else if ((e.getKeyCode() == NativeKeyEvent.VC_LEFT || e.getKeyCode() == NativeKeyEvent.VC_RIGHT) ) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
            LayerManager.BookIndex = Controls.SelectMenu(keys, booksList.size(), LayerManager.BookIndex);
            ListBooks();
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
}
