import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
public class Var8 {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Сколько людей хотите записать в файл");
        int count=sc.nextInt();
        sc.nextLine();
        RandomAccessFile rf=null;
        RandomAccessFile rfOut = null;
        try {
            File f=new File("London");
            if (f.exists()) f.delete();
            f.createNewFile();
            rf=new RandomAccessFile(f, "rw");
            String name, lastname;
            int year, month;
            for (int i=0; i<count; i++) {
                System.out.println("Введите фамиию");
                lastname=sc.nextLine();
                rf.writeUTF(lastname);
                for (int j=0; j<20 -lastname.length(); j++) rf.writeByte(1);

                System.out.println("Введите имя");
                name=sc.nextLine();
                rf.writeUTF(name);
                for (int j=0; j<20 -name.length(); j++) rf.writeByte(1);

                System.out.println("Введите год рождения");
                year=sc.nextInt();
                rf.writeInt(year);

                System.out.println("Ведите месяц рождения");
                month=sc.nextInt();
                rf.writeInt(month);
                    sc.nextLine();
            }

            File fOut = new File("Paris");
            if (fOut.exists()) fOut.delete();
            fOut.createNewFile();
            rfOut = new RandomAccessFile(fOut, "rw");
            rfOut.seek(0);

            String namer, lastnamer;
            int years, months = 0;
            int worker1=0;
            rf.seek(0);
            for (int i=0; i<count; i++) {
                namer = rf.readUTF();
                for (int j = 0; j < 20 - namer.length(); j++) rf.readByte();

                lastnamer = rf.readUTF();
                for (int j = 0; j < 20 - lastnamer.length(); j++) rf.readByte();

                years = rf.readInt();
                months=rf.readInt();
                if (months == 1) {
                    rfOut.writeUTF(namer);
                    for (int j = 0; j < 20 - namer.length(); ++j) rfOut.writeByte(1);
                    rfOut.writeUTF(lastnamer);
                    for (int j = 0; j < 20 - lastnamer.length(); ++j) rfOut.writeByte(1);
                    rfOut.writeInt(years);
                    rfOut.writeInt(months);
                    worker1++;

                }
            }
            if (worker1!=0){
                rfOut.seek(0);
                for(int i = 0; i < worker1; ++i) {
                    namer = rfOut.readUTF();
                    for (int j = 0; j < 20 - namer.length(); ++j) rfOut.readByte();

                    lastnamer = rfOut.readUTF();
                    for (int j = 0; j < 20 - namer.length(); ++j) rfOut.readByte();

                    years = rfOut.readInt();
                    months = rfOut.readInt();
                    System.out.println("name " + namer + " lastname " + lastnamer + " year " + years + " month " + months);
                }
                }
            else {
                System.out.println("Нет людей, родивших в январе");
            }

        }catch (IOException ie) {
            ie.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            rf.close();
            rfOut.close();
        }
    }

}
