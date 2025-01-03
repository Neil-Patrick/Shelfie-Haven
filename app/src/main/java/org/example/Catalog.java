package org.example;

import java.io.Console;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.management.QueryEval;
import javax.swing.text.LayeredHighlighter;
import javax.swing.text.LayeredHighlighter.LayerPainter;

import org.checkerframework.checker.units.qual.g;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.google.common.base.Ascii;

public class Catalog {

    
    private static List<Books> booksList;
        private String title = "";
        private String author = "";
        private String genre = "";
        private String location = "";
        private String date = "";
        private String quantity = "";

        private String search = "";
    public void CatalogNativeKeyPressed(NativeKeyEvent e) 
    {

        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) 
        {
            Controls.isCtrlPressed = true;
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && LayerManager.CatalogLayer == 0) 
        {
            Controls.clearScreen();
            App.currentEventState = Controls.EventState.HOME;
            LayerManager.HomeOptions = 0;
            Home.PrintHomeUI();
            
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && (LayerManager.CatalogLayer == 1 || LayerManager.CatalogLayer == 3 || LayerManager.CatalogLayer == 4)) 
        {
            LayerManager.CatalogLayer = 0;
            LayerManager.BookInput = 0;
            clearFields();
            ListBooks();
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE && (LayerManager.CatalogLayer == 1 || LayerManager.CatalogLayer == 4)) 
        {
            try 
            {
                if (LayerManager.BookInput == 0) 
                {
                    if (title.length() > 0) 
                    {
                        title = title.substring(0, title.length() - 1);
                    }
                } 
                else if (LayerManager.BookInput == 1) 
                {
                    if (author.length() > 0) 
                    {
                        author = author.substring(0, author.length() - 1);
                    }
                } 
                else if (LayerManager.BookInput == 2) 
                {
                    if (genre.length() > 0) 
                    {
                        genre = genre.substring(0, genre.length() - 1);
                    }
                } 
                else if (LayerManager.BookInput == 3) 
                {
                    if (location.length() > 0) 
                    {
                        location = location.substring(0, location.length() - 1);
                    }
                } 
                else if (LayerManager.BookInput == 4) 
                {
                    if (date.length() > 0) 
                    {
                        date = date.substring(0, date.length() - 1);
                    }
                } 
                else if (LayerManager.BookInput == 5) 
                {
                    if (quantity.length() > 0) 
                    {
                        quantity = quantity.substring(0, quantity.length() - 1);
                    }
                }
            } 
            catch (StringIndexOutOfBoundsException f) 
            {
                System.out.println("You can't delete while it's already empty.");
                try 
                {
                    // Add a 2-second delay
                    Thread.sleep(1000);
                } 
                catch (InterruptedException g) 
                {
                    g.printStackTrace();
                }
            }
            Controls.clearScreen();
            if (LayerManager.CatalogLayer == 1) 
            {
                AddBook();
            } 
            else if (LayerManager.CatalogLayer == 4) 
            {
                UpdateBook();
            }
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.CatalogLayer == 1) 
        {
            Queries.AddBook(title, author, genre, location, date, quantity);
            clearFields();
            Controls.clearScreen();
            LayerManager.CatalogLayer = 0;
            LayerManager.BookIndex = 0;
            LayerManager.BookInput = 0;
            ListBooks();  
        } 
        // Update book
        else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.CatalogLayer == 4) 
        {
            Controls.clearScreen();
            LayerManager.CatalogLayer = 0;
            boolean success = Queries.UpdateBook(CreateUpdatedBook());
            if (success) 
            {
                System.out.println("Book updated successfully.");
            } 
            else 
            {
                System.out.println("Book update failed.");
                
            }
            try 
            {
                // Add a 2-second delay
                Thread.sleep(1000);
            } 
            catch (InterruptedException g) 
            {
                g.printStackTrace();
            }
            ListBooks();

        } 
        else if (Controls.isCtrlPressed && e.getKeyCode() == NativeKeyEvent.VC_N && LayerManager.CatalogLayer == 0) 
        {
            Controls.clearScreen();
            LayerManager.CatalogLayer = 1;
            AddBook();
            

        } 
        // Enter Update
        else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.CatalogLayer == 0) 
        {
            if (!booksList.isEmpty())
            {
                Controls.clearScreen();
                LayerManager.CatalogLayer = 4;
                UpdateBookFields(booksList.get(LayerManager.BookIndex));
                UpdateBook();
            }
            else
            {
                Controls.clearScreen();
                LayerManager.CatalogLayer = 0;
                ListBooks();
            }
            
            
        } 
        else if (((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) && LayerManager.CatalogLayer == 4)) 
        {
            Controls.clearScreen();
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.BookInput = Controls.SelectMenu(keys, 6, LayerManager.BookInput);
            UpdateBook();
        }
        else if (((e.getKeyCode() == NativeKeyEvent.VC_LEFT || e.getKeyCode() == NativeKeyEvent.VC_RIGHT)) && LayerManager.CatalogLayer == 0) 
        {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
            LayerManager.BookIndex = Controls.SelectMenu(keys, booksList.size(), LayerManager.BookIndex);
            ListBooks();
        } 
        else if (((e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_DOWN) && LayerManager.CatalogLayer == 1) ) 
        {
            String keys = (e.getKeyCode() == NativeKeyEvent.VC_UP) ? "up" : "down";
            LayerManager.BookInput = Controls.SelectMenu(keys, 6, LayerManager.BookInput);
            AddBook();
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_DELETE && LayerManager.CatalogLayer == 0) 
        {
            Controls.clearScreen();
            LayerManager.CatalogLayer = 2;
            // System.out.println("Are you sure you want to delete this book? ");
            // System.out.println("Press Enter to continue");
            // System.out.println("Press Esc to cancel");
            AsciiUIDesign.DeleteUI();
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            Controls.PrintInCenter("");
            AsciiUIDesign.DeleteGuideUI();
        
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.CatalogLayer == 2) 
        {
            Queries.DeleteBook(booksList.get(LayerManager.BookIndex).getId());
            LayerManager.CatalogLayer = 0;
            LayerManager.BookIndex = 0;
            ListBooks();
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && LayerManager.CatalogLayer == 2) 
        {
            Controls.clearScreen();
            LayerManager.CatalogLayer = 0;
            LayerManager.BookIndex = 0;
            ListBooks();
        }
        else if (Controls.isCtrlPressed && e.getKeyCode() == NativeKeyEvent.VC_B && LayerManager.CatalogLayer == 0) 
        {
            Controls.clearScreen();
            LayerManager.CatalogLayer = 3;
            ShowSearchUI();
            
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_ENTER && LayerManager.CatalogLayer == 3) 
        {
            Controls.clearScreen();
            booksList = Queries.GetSearchedBooks(search);
            LayerManager.CatalogLayer = 0;
            LayerManager.BookIndex = 0;
            search = "";
            ListBooks(booksList);
            
        } 
        else if (e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE && LayerManager.CatalogLayer == 3) 
        {
            try 
            {
                
                if (search.length() > 0) 
                {
                    search = search.substring(0, search.length() - 1);
                }
                
            } 
            catch (StringIndexOutOfBoundsException f) 
            {
                System.out.println("You can't delete while it's already empty.");
                try 
                {
                    // Add a 2-second delay
                    Thread.sleep(1000);
                } 
                catch (InterruptedException g) 
                {
                    g.printStackTrace();
                }
            }
            Controls.clearScreen();
            ShowSearchUI();
        }
        
    }

    public void CatalogNativeKeyTyped(NativeKeyEvent e) 
    {
		if ((Character.isLetter(e.getKeyChar()) || Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '-' || e.getKeyChar() == ' ') && (LayerManager.CatalogLayer == 1 || LayerManager.CatalogLayer == 4) && !Controls.isCtrlPressed) 
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

            switch (LayerManager.BookInput) 
            {
                case 0: title += keyChar; break;  
                case 1: author += keyChar; break;   
                case 2: genre += keyChar; break;  
                case 3: location += keyChar; break;  
                case 4: date += keyChar; break;   
                case 5: quantity += keyChar; break;
                default: break;
                    
            }

            Controls.clearScreen();

            if (LayerManager.CatalogLayer == 1) 
            {
                AddBook();
            } 
            else if (LayerManager.CatalogLayer == 4) 
            {
                UpdateBook();
            }
        }

        if ((Character.isLetter(e.getKeyChar()) || Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '-' || e.getKeyChar() == ' ') && LayerManager.CatalogLayer == 3 && !Controls.isCtrlPressed) 
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
    
            if (LayerManager.BookInput == 0) 
            {
                search += keyChar;
            }
            Controls.clearScreen();
            ShowSearchUI();
        }
	}

    public void CatalogNativeKeyReleased(NativeKeyEvent e) 
    {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) 
        {
            Controls.isCtrlPressed = false;
        }
    }

    public static void ListBooks(List<Books> booksfound) 
    {
        Controls.clearScreen();
        AsciiUIDesign.BookCatalogUi();
    
        booksList = booksfound;
    
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
            
        }
    }

    public static void ListBooks() 
    {
        Controls.clearScreen();

        if (App.currentEventState == Controls.EventState.CATALOG) 
        {
            AsciiUIDesign.BookCatalogUi();
        } 
        else if (App.currentEventState == Controls.EventState.BORROW) 
        {
            AsciiUIDesign.AvailableBooksUI();
        }
    
        booksList = Queries.GetBooks();
    
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
            if (App.currentEventState == Controls.EventState.CATALOG) 
            {
                AsciiUIDesign.BookCatalogGuideUI();
            } 
            else if (App.currentEventState == Controls.EventState.BORROW) 
            {
                AsciiUIDesign.BorrowGuideUI();
            }
        }
    }
    

    public void AddBook() 
    {
        Controls.clearScreen();
        AsciiUIDesign.AddBookUi();
        Controls.PrintOptionInCenter("Title:                " + title, 150, LayerManager.BookInput == 0, 40);
        Controls.PrintOptionInCenter("Author:               " + author, 150, LayerManager.BookInput == 1, 40);
        Controls.PrintOptionInCenter("Genre:                " + genre, 150, LayerManager.BookInput == 2, 40);
        Controls.PrintOptionInCenter("Location:             " + location, 150, LayerManager.BookInput == 3, 40);
        Controls.PrintOptionInCenter("Date(YYYY-MM-DD):     " + date, 150, LayerManager.BookInput == 4, 40);
        Controls.PrintOptionInCenter("Quantity:             " + quantity, 150, LayerManager.BookInput == 5, 40);
        AsciiUIDesign.AddBookGuideUI();

    }

    public void clearFields() 
    {
        title = "";
        author = "";
        genre = "";
        location = "";
        date = "";
        quantity = "";
    }

    public void ShowSearchUI() 
    {
        Controls.clearScreen();
        AsciiUIDesign.SearchUI();
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintOptionInCenter("Search:  " + search, 150, true, 52);
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        AsciiUIDesign.SearchGuideUI();
    }

    public void UpdateBook() 
    {
        Controls.clearScreen();
        
        AsciiUIDesign.UpdateBookUi();
        Controls.PrintOptionInCenter("Title:                " + title, 150, LayerManager.BookInput == 0, 40);
        Controls.PrintOptionInCenter("Author:               " + author, 150, LayerManager.BookInput == 1, 40);
        Controls.PrintOptionInCenter("Genre:                " + genre, 150, LayerManager.BookInput == 2, 40);
        Controls.PrintOptionInCenter("Location:             " + location, 150, LayerManager.BookInput == 3, 40);
        Controls.PrintOptionInCenter("Date(YYYY-MM-DD):     " + date, 150, LayerManager.BookInput == 4, 40);
        Controls.PrintOptionInCenter("Quantity:             " + quantity, 150, LayerManager.BookInput == 5, 40);

    }

    public void UpdateBookFields(Books book) 
    {
        title = booksList.get(LayerManager.BookIndex).getTitle();
        author =  booksList.get(LayerManager.BookIndex).getAuthor();
        genre = booksList.get(LayerManager.BookIndex).getGenre();
        location = booksList.get(LayerManager.BookIndex).getlocation();
        date = booksList.get(LayerManager.BookIndex).getDate().toString();
        quantity = String.valueOf(booksList.get(LayerManager.BookIndex).getQuantity());
    } 
    
    public Books CreateUpdatedBook() 
    {
        String dateString = date; // Assuming 'date' is a String
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the pattern as needed
        Date date = null;
        try 
        {
            date = (Date) formatter.parse(dateString);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        Books updatedBook = new Books(booksList.get(LayerManager.BookIndex).getId(), title, author, genre, location, date, Integer.parseInt(quantity));
        return updatedBook;
    }

    
}
