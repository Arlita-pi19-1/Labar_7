import javax.swing.*;
import java.io.*;
import java.io.Serializable;
import java.util.Scanner;
class Worker implements Serializable{
    String name;
    String lastname;
    String year;
    int month;
    @Override
    public String toString(){
    return "Worker{"+
        "name='"+name+'\''+
            ", lastname='" + lastname + '\''+
            ", year='"+year+ '\''+
            ", month="+month+
            '}';

        }
public static class Var8pr1 {
        public static void main(String[] args) throws IOException, ClassNotFoundException{
            FileOutputStream fos=null;
            ObjectOutputStream oos=null;
            FileInputStream fin=null;
            ObjectInputStream oin=null;

            FileOutputStream fos2=null;
            ObjectOutputStream oos2=null;
            FileInputStream fin2=null;
            ObjectInputStream oin2=null;
            try {
                File f=new File("London");
                if (f.exists()) f.delete();
                f.createNewFile();
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
                System.out.println("Сколько людей хотите записать в файл");
                Scanner sc=new Scanner(System.in);
                int count=sc.nextInt();
                sc.nextLine();
                Worker worker;
                for (int i=0; i<count; i++){
                    worker=new Worker();
                    System.out.println("Введите фамилию");
                    worker.lastname=sc.nextLine();
                    System.out.println("Введите имя");
                    worker.name=sc.nextLine();
                    System.out.println("Введите год рождения");
                    worker.year=sc.nextLine();
                    System.out.println("Введите месяц рождения");
                    worker.month=sc.nextInt();
                    sc.nextLine();
                    oos.writeObject(worker);
                }
                System.out.println("Информация о людях записанных в файл");

                fin=new FileInputStream(f);
                oin=new ObjectInputStream(fin);

                File fOut=new File("Paris");
                if (fOut.exists()) fOut.delete();
                fOut.createNewFile();
                fos2=new FileOutputStream(fOut);
                oos2=new ObjectOutputStream(fos2);

                fin2=new FileInputStream(fOut);
                oin2=new ObjectInputStream(fin2);

                int workerOver1=0;
                for (int i=0; i<count; i++){
                    worker=(Worker) oin.readObject();
                    if (worker.month==1) {
                        oos2.writeObject(worker);
                        workerOver1++;
                    }
                }

                if (workerOver1!=0){
                    for(int i=0; i<workerOver1; i++){

                    }
                    System.out.println("Люди рожденные в январе");
                    System.out.println((Worker)oin2.readObject());
                }else {
                    System.out.println("Нет людей рожденных в январе");
                }
            }catch (IOException ie){
                ie.printStackTrace();
            }catch (ClassNotFoundException one){
                one.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                oos2.flush();
                oos2.close();
                fos2.flush();
                fos2.close();
                oin2.close();
                fin2.close();
                oos.flush();
                oos.close();
                fos.flush();
                fos.close();
                oin.close();
                fin.close();

            }


        }
}

}
