import com.sun.tools.javac.Main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyTaiKhoan  {
    static Scanner scan = new Scanner(System.in);
    static Account account = null;

    public static void main(String[] args) {
        login();
        System.out.println("Next Step!!!");
        int choose;

        do {
            showMenu();
            choose = Integer.parseInt(scan.nextLine());

            switch(choose) {
                case 1:
                    account = new Account();
                    account.input();
                    break;
                case 2:
                    if(account != null) {
                        account.addReceiver();

                        account.display();
                    } else {
                        System.err.println("TK khong ton tai");
                    }
                    break;
                case 3:
                    if(account != null) {
                        account.transfer();
                        account.display();
                    } else {
                        System.err.println("TK khong ton tai");
                    }
                    break;
                case 4:
                    if(account != null) {
                        account.displayReceiverHistory();
                    } else {
                        System.err.println("TK khong ton tai");
                    }
                    break;
                case 5:

                    if(account != null) {
                        account.displayTransferHistory();
                    } else {
                        System.err.println("TK khong ton tai");
                    }
                    break;
                case 6:
                    saveFile();
                    break;
                case 7:
                    readFile();
                    break;
                case 8:
                    System.out.println("Thoat!!!");
                    break;
                default:
                    System.out.println("Nhap sai!!!");
                    break;
            }
        } while(choose != 8);
    }

    static void saveFile() {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("account.txt");

            //save thong tin tk
            //header
            String line = Account.getHeader() + "\n";
            fos.write(line.getBytes());
            //than
            line = account.getFileInfor() + "\n";
            fos.write(line.getBytes());

            //Luu lich su nhan tien
            line = Receiver.getHeader() + "\n";
            fos.write(line.getBytes());
            for (Receiver receiver : account.getReceiverList()) {
                line = receiver.getFileInfor()+ "\n";
                fos.write(line.getBytes());
            }

            //Luu lich su chuyen tien
            line = Transfer.getHeader() + "\n";
            fos.write(line.getBytes());
            for (Transfer transfer : account.getTransferList()) {
                line = transfer.getFileInfor()+ "\n";
                fos.write(line.getBytes());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    static void readFile() {
        

    }

    static void showMenu() {
        System.out.println("1. Khoi tao TK");
        System.out.println("2. Nap tien");
        System.out.println("3. Chuyen tien");
        System.out.println("4. Xem lich su nap tien");
        System.out.println("5. Xem lich su chuyen tien");
        System.out.println("6. Luu");
        System.out.println("7. Doc du lieu");
        System.out.println("8. Thoat");
        System.out.println("Chon: ");
    }

    static void login() {
        System.out.println("===== WELCOME ======");
        System.out.println("User Name: ");
        String username = scan.nextLine();

        System.out.println("Password: ");
        String password = scan.nextLine();

        if(username.equalsIgnoreCase("nhom13") && password.equals("Nhom13@123")) {
            System.out.println("Login Success!!!");
        } else {
            System.err.println("Login Failed!!!");
            //de quy.
            login();
        }
    }
}
