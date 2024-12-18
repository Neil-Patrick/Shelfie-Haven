package org.example;

import java.util.List;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Catalog {

    private static List<Books> booksList;

    public void CatalogNativeKeyPressed(NativeKeyEvent e) {
        Catalog.ListBooks();
    }

    public static void ListBooks() {
        Controls.clearScreen();
        booksList = Queries.GetBooks();

        for (Books books : booksList) {
            System.out.println("ID: " + books.getId());
            System.out.println("Title: " + books.getTitle());
            System.out.println("Author: " + books.getAuthor());
            System.out.println("Genre: " + books.getGenre());
            System.out.println("Location: " + books.getlocation());
            System.out.println("Date: " + books.getDate());
            System.out.println("Quantity: " + books.getQuantity());
        }
        

    }
}

