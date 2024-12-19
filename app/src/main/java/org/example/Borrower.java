package org.example;
import java.util.Date;

public class Borrower {
    private int id;
    private String fName;
    private String mName;
    private String lName;
    private int bookId;
    private Date dateBorrowed;
    private boolean isReturned;

    public Borrower(int id, String fName, String mName, String lName, int bookId, Date dateBorrowed, boolean isReturned) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.bookId = bookId;
        this.dateBorrowed = dateBorrowed;
        this.isReturned = isReturned;
    }

    public int getId() {
        return id;
    }

    public String getFName() {
        return fName;
    }

    public String getMName() {
        return mName;
    }

    public String getLName() {
        return lName;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public boolean getIsReturned() {
        return isReturned;
    }

    
}
