package org.example;

import java.io.Console;
import java.util.List;

import org.checkerframework.checker.units.qual.g;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Catalog {

    
    private static List<Books> booksList;
        private String title = "";
        private String author = "";
        private String genre = "";
        private String location = "";
        private String date = "";
        private String quantity = "";

    public void CatalogNativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            Controls.isCtrlPressed = true;
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && LayerManager.CatalogLayer == 0) {
            Controls.clearScreen();
            App.currentEventState = Controls.EventState.HOME;
            LayerManager.HomeOptions = 0;
            Home.PrintHomeUI();
            
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && LayerManager.CatalogLayer == 1) {
            LayerManager.CatalogLayer = 0;
            ListBooks();
        } else if (e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE && LayerManager.CatalogLayer == 1) {
            try {
                if (LayerManager.BookInput == 0) {
                    if (title.length() > 0) {
                        title = title.substring(0, title.length() - 1);
                    }
                } else if (LayerManager.BookInput == 1) {
                    if (author.length() > 0) {
                        author = author.substring(0, author.length() - 1);
                    }
                } else if (LayerManager.BookInput == 2) {
                    if (genre.length() > 0) {
                        genre = genre.substring(0, genre.length() - 1);
                    }
                } else if (LayerManager.BookInput == 3) {
                    if (location.length() > 0) {
                        location = location.substring(0, location.length() - 1);
                    }
                } else if (LayerManager.BookInput == 4) {
                    if (date.length() > 0) {
                        date = date.substring(0, date.length() - 1);
                    }
                } else if (LayerManager.BookInput == 5) {
                    if (quantity.length() > 0) {
                        quantity = quantity.substring(0, quantity.length() - 1);
                    }
                }
            } catch (StringIndexOutOfBoundsException f) {
                System.out.println("You can't delete while it's already empty.");
                try {
                    // Add a 2-second delay
                    Thread.sleep(1000);
                } catch (InterruptedException g) {
                    g.printStackTrace();
                }
            }
            Controls.clearScreen();
            AddBook();
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.CatalogLayer == 1) {
            // TODO: Add book to database
            Queries.AddBook(title, author, genre, location, date, quantity);
            clearFields();
            Controls.clearScreen();
            LayerManager.CatalogLayer = 0;
            LayerManager.BookIndex = 0;
            LayerManager.BookInput = 0;
            ListBooks();
            
            
        } else if (Controls.isCtrlPressed && e.getKeyCode() == NativeKeyEvent.VC_N && LayerManager.CatalogLayer == 0) {
            Controls.clearScreen();
            LayerManager.CatalogLayer = 1;
            AddBook();
            
        } else if (((e.getKeyCode() == NativeKeyEvent.VC_LEFT || e.getKeyCode() == NativeKeyEvent.VC_RIGHT)) && LayerManager.CatalogLayer == 0) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
            LayerManager.BookIndex = Controls.SelectMenu(keys, booksList.size(), LayerManager.BookIndex);
            ListBooks();
        } else if (((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) && LayerManager.CatalogLayer == 1) ) {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.BookInput = Controls.SelectMenu(keys, 6, LayerManager.BookInput);
            AddBook();
        } 
        
    }

    public void CatalogNativeKeyTyped(NativeKeyEvent e) {
		if ((Character.isLetter(e.getKeyChar()) || Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '-' || e.getKeyChar() == ' ') && LayerManager.CatalogLayer == 1) { 
            char keyChar = e.getKeyChar();
            if (NativeKeyEvent.getModifiersText(e.getModifiers()).contains("Shift")) {
                keyChar = Character.toUpperCase(keyChar);
            } else {
                keyChar = Character.toLowerCase(keyChar);
            }
    
            if (LayerManager.BookInput == 0) {
                title += keyChar;
            } if (LayerManager.BookInput == 1) {
                author += keyChar;
            } if (LayerManager.BookInput == 2) {
                genre += keyChar;
            } if (LayerManager.BookInput == 3) {
                location += keyChar;
            } if (LayerManager.BookInput == 4) {
                date += keyChar;
            } if (LayerManager.BookInput == 5) {
                quantity += keyChar;
            }
            Controls.clearScreen();
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

    public void AddBook() {
        Controls.clearScreen();
        AsciiUIDesign.AddBookUi();
        Controls.PrintOptionInCenter("Title:                " + title, 150, LayerManager.BookInput == 0, 40);
        Controls.PrintOptionInCenter("Author:               " + author, 150, LayerManager.BookInput == 1, 40);
        Controls.PrintOptionInCenter("Genre:                " + genre, 150, LayerManager.BookInput == 2, 40);
        Controls.PrintOptionInCenter("Location:             " + location, 150, LayerManager.BookInput == 3, 40);
        Controls.PrintOptionInCenter("Date(YYYY-MM-DD):     " + date, 150, LayerManager.BookInput == 4, 40);
        Controls.PrintOptionInCenter("Quantity:             " + quantity, 150, LayerManager.BookInput == 5, 40);

    }

    public void clearFields() {
        title = "";
        author = "";
        genre = "";
        location = "";
        date = "";
        quantity = "";
    }
    

    
}
