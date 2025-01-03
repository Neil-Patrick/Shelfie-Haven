package org.example;
import java.util.Date;

public class Borrower 
{
    private int id;
    private String fName;
    private String mName;
    private String lName;
    private int bookId;
    private Date dateBorrowed;
    private String title;

    public Borrower(int id, String fName, String mName, String lName, int bookId, Date dateBorrowed, String title) 
    {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.bookId = bookId;
        this.dateBorrowed = dateBorrowed;
        this.title = title;
    }

    public Borrower(int id, String fName, String mName, String lName, int bookId, Date dateBorrowed)
    {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.bookId = bookId;
        this.dateBorrowed = dateBorrowed;
    }

    public int getId() 
    {
        return id;
    }

    public String getFName() 
    {
        return fName;
    }

    public String getMName() 
    {
        return mName;
    }

    public String getLName() 
    {
        return lName;
    }

    public int getBookId() 
    {
        return bookId;
    }

    public Date getDateBorrowed() 
    {
        return dateBorrowed;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getName() 
    {
        return fName + " " + mName + " " + lName;
    }


}
