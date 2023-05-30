package Books_Management;

public class BookReaderManagerment {
    private Book book;
    private Reader reader;
    private int numOfBorrowed;
    private String note;
    private int totalBorrowedBooks;

    public BookReaderManagerment() {
    }

    public BookReaderManagerment( Reader reader, Book book, int numOfBorrowed, String note, int totalBorrowedBooks) {
        this.book = book;
        this.reader = reader;
        this.numOfBorrowed = numOfBorrowed;
        this.note = note;
        this.totalBorrowedBooks = totalBorrowedBooks;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public int getNumOfBorrowed() {
        return numOfBorrowed;
    }

    public void setNumOfBorrowed(int numOfBorrowed) {
        this.numOfBorrowed = numOfBorrowed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTotalBorrowedBooks() {
        return totalBorrowedBooks;
    }

    public void setTotalBorrowedBooks(int totalBorrowedBooks) {
        this.totalBorrowedBooks = totalBorrowedBooks;
    }

    @Override
    public String toString() {
        return "BookReaderManagerment{" +
                "readerID=" + reader.getReaderID() +
                ", fullName=" +reader.getFullName()+
                ", bookID=" + book.getBookID() +
                ", numOfBorrowed=" + numOfBorrowed +
                ", note='" + note + '\'' +
                ", totalBorrowedBooks=" + totalBorrowedBooks +
                '}';
    }
}
