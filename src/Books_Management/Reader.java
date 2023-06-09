package Books_Management;

import java.util.Scanner;

public class Reader {
    Scanner sc = new Scanner(System.in);
    public static int rdID = 10000000;
    private int readerID ;
    private String fullName;
    private String address;
    private String phoneNumber;

    public Reader(int readerID, String fullName, String address, String phoneNumber) {
        if (readerID == 0){
            setReaderID();
        }else{
            setReaderID(readerID);
        }
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Reader() {
    }
    public Reader(int readerID, String fullName) {
        this.readerID = readerID;
        this.fullName = fullName;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }
    public void setReaderID(){
        this.readerID = rdID;
        rdID = rdID + 1;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Reader{" +
                ", readerID=" + readerID +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
