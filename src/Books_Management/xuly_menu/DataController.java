
package Books_Management.xuly_menu;
import Books_Management.Book;
import Books_Management.BookReaderManagerment;
import Books_Management.Reader;
import java.io.*;
import java.util.ArrayList;

// ghi file, xuât file
public class DataController {
    private FileWriter fileWriter;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private FileInputStream fileInputStream;


    public void openFileToWrite(String fileName){
        try{
            fileInputStream = new FileInputStream(fileName);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeBookToFile(Book book , String fileName){
        try {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.write(book.getBookID()+"|"+ book.getBookName()+"|"+book.getAuthor()+"|"+book.getSpecialization()
            +"|"+ book.getPublishYear()+"|"+book.getQuantity()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeReaderToFile(Reader reader , String fileName){
        try {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.write(reader.getReaderID()+"|"+reader.getFullName()+"|"+reader.getAddress()+"|"+reader.getPhoneNumber()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeBRMToFile(BookReaderManagerment brm , String fileName){
        try {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.write(brm.getReader().getReaderID()+"|"+brm.getReader().getFullName()+"|"+brm.getBook().getBookID()+
                    "|"+brm.getNumOfBorrowed()+"|"+ brm.getNote()+"|"+
                    brm.getTotalBorrowedBooks()+"\n");


            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Book> readBookToFile(String fileName) {
        ArrayList<Book> books = new ArrayList<>();
        openFileToWrite(fileName);
        try{
            String line = bufferedReader.readLine();
            while (line != null){
                Book book = creatFromBookLine(line);
                books.add(book);
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        closeFile(fileName);
        return books;
    }

    private Book creatFromBookLine(String line) {
        String[] datas = line.split("\\|");
        Book book = new Book(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], Integer.parseInt(datas[4]), Integer.parseInt(datas[5]));
        return book;
    }

    public ArrayList<Reader> readReaderToFile(String fileName) {
        ArrayList<Reader> readers = new ArrayList<>();
        openFileToWrite(fileName);
        try{
            String line = bufferedReader.readLine();
            while (line != null){
                Reader reader = creatFromReaderLine(line);
                readers.add(reader);
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        closeFile(fileName);
        return readers;
    }
    private Reader creatFromReaderLine(String line) {
        String[] datas = line.split("\\|");
        Reader reader = new Reader(Integer.parseInt(datas[0]),datas[1], datas[2], datas[3]);
        return reader;
    }


    public ArrayList<BookReaderManagerment> readBRMSToFile(String fileName) {
        ArrayList<BookReaderManagerment> BRMS = new ArrayList<>();
        openFileToWrite(fileName);
        try{
            String line = bufferedReader.readLine();
            while (line != null){
                BookReaderManagerment brm = creatFromBRMLine(line);
                BRMS.add(brm);
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        closeFile(fileName);
        return BRMS;
    }
    private BookReaderManagerment creatFromBRMLine(String line) {
        String[] datas = line.split("\\|");
        Reader rea = new Reader(Integer.parseInt(datas[0]), datas[1]);
        BookReaderManagerment brm = new BookReaderManagerment(rea, new Book(Integer.parseInt(datas[2])), Integer.parseInt(datas[3]), datas[4], Integer.parseInt(datas[5]));
        return brm;
    }

//  chức năng khôg cho 2 BookID - ReaderID trùng nhau
    public void checkBookID(Book book, String fileName) {
        var listBooks = readBookToFile(fileName);
        if (listBooks.size() != 0){
            int a = listBooks.get(listBooks.size()-1).getBookID()+1;
            book.setBookID(a);
        }
//        closeFile(fileName);
    }
    public void checkReaderID(Reader reader, String fileName) {
        var listReaders = readReaderToFile(fileName);
        if (listReaders.size() != 0){
            int a = listReaders.get(listReaders.size()-1).getReaderID()+1;
            reader.setReaderID(a);
        }
        closeFile(fileName);
    }


    public boolean checkFull(ArrayList <BookReaderManagerment> bookReaderManagerment , int readerID, int bookID){
        for (var r : bookReaderManagerment){
            if (r.getReader().getReaderID() == readerID
                    &&r.getBook().getBookID() == bookID && r.getNumOfBorrowed() == 3){
                return true; //đã đầy không đc thêm
            }
        }
        return false; // có thể mượn
    }

    public int NumOfBorrowed(ArrayList <BookReaderManagerment> bookReaderManagerment,int readerID, int bookID){
        for (var r : bookReaderManagerment){
            if( r.getReader().getReaderID() == readerID
                    && r.getBook().getBookID() == bookID ){
                return r.getNumOfBorrowed();
            }
        }
        return 0;
    }

    public Book book(ArrayList<Book> books,int bookID){
        for (int i = 0; i< books.size(); i++){
            if (books.get(i).getBookID() == bookID){
                return books.get(i);
            }
        }
        return null;
    }

    public Reader reader(ArrayList<Reader> readers,int readerID){
        for (int i = 0; i< readers.size(); i++){
            if (readers.get(i).getReaderID() == readerID){
                return readers.get(i);
            }
        }
        return null;
    }

    public ArrayList<BookReaderManagerment> updataBRMS(ArrayList<BookReaderManagerment> list, BookReaderManagerment arm){
        boolean check = false;
        for (int i = 0; i< list.size(); i++){
            if (list.get(i).getReader().getReaderID() == arm.getReader().getReaderID() &&
                    list.get(i).getBook().getBookID() == arm.getBook().getBookID()){
                list.set(i, arm);
                check = true;
            }
        }
        if (!check){
            list.add(arm);
        }
        return list;
    }

    public void updateFile(ArrayList<BookReaderManagerment> list, String fileName){
        File file = new File(fileName);
        if (file.exists()){
            file.delete();
        }
        try {
            fileWriter = new FileWriter(fileName, true);
            for (var brm : list){
                fileWriter.write(brm.getReader().getReaderID()+"|"+brm.getReader().getFullName()+"|"+brm.getBook().getBookID()+
                        "|"+brm.getNumOfBorrowed()+"|"+ brm.getNote()+"|"+
                        brm.getTotalBorrowedBooks()+"\n");
            }
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BookReaderManagerment> updateTotalBRB(ArrayList<BookReaderManagerment> list){
        for (int i =0; i<list.size(); i++){
            int sum = 0;
            BookReaderManagerment b = list.get(i);
            for (int j=0; j < list.size(); j++){
                if (list.get(j).getReader().getReaderID() == b.getReader().getReaderID()){
                    sum = sum + list.get(j).getNumOfBorrowed();
                }
            }
            b.setTotalBorrowedBooks(sum);
            list.set(i, b);

        }
        return list;
    }

    public ArrayList<BookReaderManagerment> sortBorrowed(ArrayList<BookReaderManagerment> list){
        for (int i = 0; i< list.size()-1; i++){
            for (int j= i+1; j< list.size(); j++){
                if (list.get(i).getTotalBorrowedBooks() < list.get(j).getTotalBorrowedBooks()){
                    BookReaderManagerment tamp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tamp);
                }
            }
        }
        return list;
    }

    public ArrayList<BookReaderManagerment> sortTen(ArrayList<BookReaderManagerment> list, ArrayList<Reader> listReader){
        for (int i = 0; i< list.size()-1; i++){
            Reader readerTamp = reader(listReader, list.get(i).getReader().getReaderID());
            for (int j= i+1; j< list.size(); j++){
                Reader readerTamp1 = reader(listReader, list.get(j).getReader().getReaderID());
                if ( readerTamp.getFullName().compareTo(readerTamp1.getFullName()) > 0){
                    BookReaderManagerment tamp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tamp);
                }
            }
        }
        return list;
    }

    public  ArrayList<BookReaderManagerment> search(ArrayList<BookReaderManagerment> list, String fullName){
        ArrayList<BookReaderManagerment> listTamp = new ArrayList<>();
        for (var a : list){
            if (a.getReader().getFullName().compareTo(fullName) ==0){
                listTamp.add(a);
            }
        }
        return listTamp;
    }

//   đóng file
    public void closeFile(String fileName){
        try {
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
