package Books_Management;

import java.util.Random;
import java.util.Scanner;

public class Book {
    public static int id = 100000;
    private int bookID;
    private String bookName;
    private String author;
    private String specialization;
    private int publishYear;
    private int quantity;

    public Book( int bookID,String bookName, String author, String specialization, int publishYear, int quantity) {
        if (bookID == 0) {
            setBookID();
        }else{
            setBookID(bookID);
        }
        this.bookName = bookName;
        this.author = author;
        this.specialization = specialization;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    public Book() {}
    public Book(int bookID) {this.bookID = bookID;}

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public void setBookID() {
        this.bookID = id;
        id = id+1;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getPublishYear() {
        return publishYear;
    }
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", specialization='" + specialization + '\'' +
                ", publishYear=" + publishYear +
                ", quantity=" + quantity +
                '}';
    }
}
