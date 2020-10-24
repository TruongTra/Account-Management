import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver implements IFileInfor{
    int money;
    String note;
    Date createdAt;

    public Receiver() {
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void input() {
        System.out.println("Nhap thong tin nap tien:");
        Scanner scan = new Scanner(System.in);

        System.out.println("Nhap so tien: ");
        money = Integer.parseInt(scan.nextLine());

        System.out.println("Nhap noi dung ghi chu: ");
        note = scan.nextLine();

        createdAt = new Date();
    }

    @Override
    public String toString() {
        return "Receiver{" + "money=" + money + ", note=" + note + ", createdAt=" + getCreatedAtString() + '}';
    }

    public String getCreatedAtString() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        String str = format.format(createdAt);

        return str;
    }

    public void display() {
        System.out.println(toString());
    }

    public static String getHeader() {
        return "Receiver:";
    }

    @Override
    public String getFileInfor() {
        return money+","+note+","+getCreatedAtString();
    }

    @Override
    public void putFileInfor(String data) throws ParseException {
        try {
            String[] param = data.split(",");
            money = Integer.parseInt(param[0]);

            note = param[1];

            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
            createdAt = format.parse(param[2]);
        } catch (ParseException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
