package Books_Management.xuly_menu;
import Books_Management.Book;
import Books_Management.BookReaderManagerment;
import Books_Management.Reader;

import java.util.ArrayList;
import java.util.Scanner;
public class ControllerUtility {
    public void chucNang(){
        Book book = null;
        DataController controller = new DataController();
        var listBook = new ArrayList<Book>();
        var listReader = new ArrayList<Reader>();
        var listBRMS = new ArrayList<BookReaderManagerment>();
        DataController dataController = new DataController();
        var isBookChecked = false;
        var isReaderChecked = false;
        Scanner sc = new Scanner(System.in);
        String fileBook = "D:/.vscode/LAP_TRINH_JAVA/quanLy_ThuVienSach/src/BOOK.DAT";
        String fileReader = "D:/.vscode/LAP_TRINH_JAVA/quanLy_ThuVienSach/src/READER.DAT";
        String fileBRM = "D:/.vscode/LAP_TRINH_JAVA/quanLy_ThuVienSach/src/BRM.DAT";
        int luaChon = 0;
        do {
            System.out.println("\n1. add Book to file Book.DAT");
            System.out.println("2. print list of books in file");
            System.out.println("3. add Reader to file READER.DAT");
            System.out.println("4. print list of Reader in file");
            System.out.println("5. create information BookReaderManagement, save to BRM.DAT");
            System.out.println("6. sort list BookReaderManagement saved in file BRM.DAT");
            System.out.println("7. search and display information in BookReaderManagement by name Reader");
            System.out.println("0. exit");
            System.out.print("\nxin mời bạn nhập: ");
            luaChon = sc.nextInt();
            sc.nextLine();
            switch (luaChon){
                case 1:
                    String[] listSpecialization= {"Science", "Art", "Economic", "IT"};
                    String bookName, author, specialization;
                    int sp, year, quan;
                    System.out.print("Nhập số lượng sách cần thêm: ");
                    int soLuongBook = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < soLuongBook; i++){
                        System.out.print("bookName: ");
                        bookName = sc.nextLine();
                        System.out.print("author: ");
                        author = sc.nextLine();

                        do {
                            System.out.println("specialization: ");
                            System.out.println("\t1. Science");
                            System.out.println("\t2. SRT");
                            System.out.println("\t3. Economic");
                            System.out.println("\t4. IT");
                            sp = sc.nextInt();
                        }while  (sp < 1 && sp >4);
                        specialization = listSpecialization[sp-1];
                        System.out.print("publishYear: ");
                        year = sc.nextInt();
                        System.out.print("quantity: ");
                        quan = sc.nextInt();
                        book = new Book(0,bookName, author, specialization , year, quan);
                        if (!isBookChecked){
                            controller.checkBookID(book, fileBook);
                            isBookChecked = true;
                        }
                        controller.writeBookToFile(book, fileBook);
                    }
                    break;
                case 2:
                    listBook = controller.readBookToFile(fileBook);
                    for (var a : listBook){
                        System.out.println(a);
                    }

                    break;
                case 3:
//                    int readerID, String fullName, String address, String phoneNumber
                    String fullName, address, phoneNumber;
                    System.out.print("Nhap so luong reader: ");
                    int soLuongReader = sc.nextInt();
                    sc.nextLine();
                    for (int i =0; i< soLuongReader; i++){
                        System.out.print("\nfullName: ");
                        fullName = sc.nextLine();
                        System.out.print("address: ");
                        address = sc.nextLine();
                        System.out.print("phoneNumber: ");
                        phoneNumber = sc.nextLine();
                        if(phoneNumber.length() != 10){
                            do{
                                System.out.println("Please enter a phone number of length 10!");
                                System.out.print("phoneNumber: ");
                                phoneNumber = sc.nextLine();
                            }while(phoneNumber.length() != 10);
                        }
                        Reader reader = new Reader(0, fullName,address, phoneNumber);
                        if (!isReaderChecked){
                            controller.checkReaderID(reader, fileReader);
                            isReaderChecked = true;
                        }
                        controller.writeReaderToFile(reader, fileReader);
                    }
                    break;
                case 4:
                    listReader = controller.readReaderToFile(fileReader);
                    for(var a : listReader){
                        System.out.println(a);
                    }
                    break;
                case 5:
                    int readerID, bookID, sachMuon, total = 0;
                    String note = "";
                    boolean checkFull;

                    listReader = controller.readReaderToFile(fileReader);
                    for(var a : listReader){
                        System.out.println(a);
                    }
                    System.out.print("readerID can tim: ");
                    readerID = sc.nextInt();
                    System.out.println();
                    listBook = controller.readBookToFile(fileBook);
                    for (var a : listBook){
                        System.out.println(a);
                    }
                    System.out.print("BookID can muon: ");
                    bookID = sc.nextInt();
                    listBRMS = controller.readBRMSToFile(fileBRM);
                    total = controller.NumOfBorrowed(listBRMS,readerID, bookID);

                    System.out.print("nhập số sách cần mượn <=3 ( đã mượn "+total+"): ");
                    sachMuon = sc.nextInt();
                    sc.nextLine();
                    checkFull = controller.checkFull(listBRMS, readerID, bookID);
                    if(checkFull == true){
                        System.out.println("so sach muon da dat toi da!");
                    }else {
                        if (( sachMuon + total) >=1 && ( sachMuon + total) <=3){
                            total += sachMuon;
                            System.out.print("note: ");
                            note = sc.nextLine();
                            Book bookBrm = controller.book(listBook, bookID);
                            Reader readerBrm = controller.reader(listReader, readerID);

                            BookReaderManagerment brm = new BookReaderManagerment( readerBrm, bookBrm, total, note, 0);
                            listBRMS = controller.updataBRMS(listBRMS, brm);
                            controller.updateFile(listBRMS, fileBRM);
                            listBRMS = controller.readBRMSToFile(fileBRM);
                        }else{
                            System.out.println("số sách mượn vượt quá yêu cầu (tổng sách mượn  <=3) !!");
                        }
                    }

                    for (var a:listBRMS){
                        System.out.println(a);
                    }
                    break;
                case 6:
                    listReader = controller.readReaderToFile(fileReader);
                    listBRMS = controller.readBRMSToFile(fileBRM);
                    ArrayList<BookReaderManagerment> list = controller.updateTotalBRB(listBRMS);
                    int c;
                    do{
                        System.out.println("\t1. sắp xếp theo tên: ");
                        System.out.println("\t2. sắp xếp theo tổng sách: ");
                        System.out.println("\t0. thoát");
                        System.out.print("xin mời bạn chọn: ");
                        c = sc.nextInt();
                        if(c==1){
                            ArrayList<BookReaderManagerment> list1 = controller.sortTen(list, listReader);
                            for(var a: list1){
                                System.out.println(a);
                            }
                        }else if(c == 2){
                            ArrayList<BookReaderManagerment> list2 = controller.sortBorrowed(list);
                            for(var a: list2){
                                System.out.println(a);
                            }
                        }
                    }while (c!=0);
                    break;
                case 7:
                    listBRMS = controller.readBRMSToFile(fileBRM);
                    ArrayList<BookReaderManagerment> listCase = controller.updateTotalBRB(listBRMS);
                    System.out.print("fullName can tim kiem: ");
                    String search1 = sc.nextLine();
                    ArrayList<BookReaderManagerment> listSearch = controller.search(listCase, search1);
                    for (var a : listSearch){
                        System.out.println(a);
                    }
                    break;
                case 0:
                    System.out.println("________________cảm ơn bạn đã sử dụng dịch vụ_____________");
                    break;
                default:
                    break;
            }
        }while (luaChon != 0);

    }
}

