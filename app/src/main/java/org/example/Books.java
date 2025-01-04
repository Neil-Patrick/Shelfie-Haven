package org.example;

import java.util.Date;

public class Books extends CatalogItem
{
    
    private String author;
    private String genre;
    private String location;
    private Date date;

    public Books(int id, String title, String author, String genre, String location, Date date, int quantity) 
    {
        super(id, title, quantity); 
        this.author = author;
        this.genre = genre;
        this.location = location;
        this.date = date;
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

    
    
}