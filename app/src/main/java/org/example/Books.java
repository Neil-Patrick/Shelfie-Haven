package org.example;

import java.util.Date;

public class Books 
{
    private int id;
    private String title;
    private String author;
    private String genre;
    private String location;
    private Date date;
    private int quantity;

    public Books(int id, String title, String author, String genre, String location, Date date, int quantity) 
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.location = location;
        this.date = date;
        this.quantity = quantity;
    }

    public int getId() 
    {
        return id;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getAuthor() 
    {
        return author;
    }

    public String getGenre() 
    {
        return genre;
    }

    public String getlocation() 
    {
        return location;
    }

    public Date getDate() 
    {
        return date;
    }

    public int getQuantity() 
    {
        return quantity;
    }
    
}