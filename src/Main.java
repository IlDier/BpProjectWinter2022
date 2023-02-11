import javax.sound.sampled.Line;
import java.io.*;
import java.nio.file.Files;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static File orders=new File("C:/Users/iran/IdeaProjects/BpProjectWinter2022/Orders.txt");
    public static File Out=new File("C:/Users/iran/IdeaProjects/BpProjectWinter2022/LogOuts.txt");

    public static File Log=new File("C:/Users/iran/IdeaProjects/BpProjectWinter2022/Log.txt");
    public static Information info=new Information();;

    public static Cafe SharifPlusCafe=new Cafe();
   public static Restaurant SharifPlusRestaurant=new Restaurant();
   public static Storage storg = new Storage();
   public static Scanner input=new Scanner(System.in);
   public static UserLists userr=new UserLists();
   public static ArrayList<Order> orderList=new ArrayList<>();


    public static void Check(String input) {
        if (input.equals("Exit")) {
            System.exit(0);
            userr.users.clear();
        }
    }


    public static void Logging(){
        String in;

        System.out.println("Do you have an account?(Yes/No)");
        in = input.nextLine();
        Check(in);
        if(in.equals("Info")){
            info.LoginInformation();
            Logging();
        }
       else if (in.equals("Yes")) {
            LogYes();
        }
        else if(in.equals("No")){
            LogNo();
        }
        else Logging();

    }
    public static void LogYes(){
        String in;
        String in1;
        System.out.println("Logging in:");
        System.out.println("Enter your username");
        in=input.nextLine();
        Check(in);
        if(in.equals("logout")) Logging();
       else if(in.equals("Info")){
            info.LoginInformation();
            LogYes();
        }
        else if(in.equals("back")) Logging();
        System.out.println("Enter your password");
        in1=input.nextLine();
        Check(in1);
        if(in.equals("logout")) Logging();
        else if(in1.equals("back")) Logging();
        if(Checklog(in,in1)!=null){

            System.out.println("Hello  "+in+"  Welcome to Sharif Plus, You Entered as "+ Objects.requireNonNull(Checklog(in, in1)).Type);
             if(Objects.requireNonNull(Checklog(in, in1)).Type.equals("Costumer")) {
    User user=Checklog(in,in1);
                 try {
                     FileWriter fw=new FileWriter(Log,true);
                     BufferedWriter bw=new BufferedWriter(fw);
                     bw.write("Username : "+user.name+" Logged in at : "+LocalDateTime.now());
                     bw.newLine();
                     bw.close();
                 }catch (IOException e){
                     e.printStackTrace();
                 }

                 System.out.println("Type Info to get some information about your service");
                 CostumerEnter(Checklog(in,in1));
             }
             else{
                 System.out.println("Type Info to get information");
                 EmployeeEnter(Checklog(in,in1));
             }
        }
        else{
            System.out.println("Username or Password was wrong");
            Logging();
        }
    }
    public static void LogNo(){
        String in;
        String in1;
        System.out.println("Sign Up: ");
        System.out.println("Enter any username you want");
        in=input.nextLine();
        in=in.replaceAll("\\s+"," ");
        Check(in);
        if(in.equals("logout")) Logging();
        else if(in.equals("Info")){
            info.LoginInformation();
            LogNo();
        }
       else if(in.equals("back")) Logging();
        else if(in.equals(" ")||in.equals("")) LogNo();
        else if(in.length()<4) {
            System.out.println("Your username should have at least 4 characters");
            LogNo();
        }
        if(CheckLog(in)){
            System.out.println("Enter any Password you want");
            in1=input.nextLine();
            Check(in1);
            in1=in1.replace("\\s+","");
            if(in.equals("logout")) Logging();
           else if(in1.equals("back")) LogNo();
            else if(in1.equals("")) {
                System.out.println("Please Enter something");
                LogNo();
            }
            else if(in1.length()<4){
                System.out.println("Your Password should have at least 4 characters");
                LogNo();
            }
            else {
                User user = new User(in1, in);
                LogType(user);
            }
        }
        else {
            System.out.println("Sorry,This username has been used");
            LogNo();
        }
    }
    public static void LogType(User user){
        String in;

        System.out.println("Choose your role(Costumer/Employee)");
        in=input.nextLine();
        Check(in);
        if(in.equals("logout")) Logging();
       else if(in.equals("back")) LogNo();
        else if(in.equals("Costumer")){
            user.Use1("Costumer");
            userr.users.add(user);
        }
        else if(in.equals("Employee")){
            user.Use1("Employee");
            userr.users.add(user);
        }
        else LogType(user);
        System.out.println("Your account has been created!");
        try {
            FileWriter fw=new FileWriter(Log,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("Username :"+user.name+" Sign uped as "+user.Type+" at time: "+LocalDateTime.now());
            bw.newLine();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            FileWriter fw=new FileWriter(userr.Users,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("Username: "+user.name+" Password: "+user.Password+" Type: "+user.Type);
            bw.newLine();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        Logging();
    }

    public  static boolean CheckLog(String in ){

        boolean result=true;
       for(User value:userr.users){
           if(value.name.equals(in)) {
               result=false;
               break;
           }
       }
return result;
    }
    public static User Checklog(String in,String password){
        User val=null;
        boolean result=false;
        for(User value:userr.users){
            if(value.name.equals(in)&&value.Password.equals(password)) {
                result=true;
                val=value;
break;
            }
        }
        if(result) return val;
        else return null;
    }
    public static void EmployeeEnter(User user){
        System.out.println("Employee// Main Menu");
        String in=input.nextLine();
        Check(in);
        if(in.equals("logout")) Logging();
       else if(in.equals("Info")) {
            info.EmployeeInformation();
            EmployeeEnter(user);
        }
        else if(in.equals("back")) {
            try {
                FileWriter fw=new FileWriter(Out,true);
                BufferedWriter bw=new BufferedWriter(fw);
                bw.write("Username: "+user.name+" Logged Out at time: "+LocalDateTime.now());
                bw.newLine();
                bw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            LogYes();
        }
        else if(in.equals("Orders Report")) {
            if(orderList.size()!=0) {
                for(Order order:orderList) {
                    if(order!=null) order.OrderDefine1();
                }
            }
            else System.out.println("No Order has been Ordered");
            EmployeeEnter(user);
        }
        else if(in.equals("Storage Report")){
            storg.report();
            EmployeeEnter(user);
        }
        else if(in.contains("Cancel Order")){
          storg.CancelOrder(in,orderList);
            EmployeeEnter(user);
        }

        else if(in.contains("Modify Storage")){
                 ModifiyStorage(user);
        }
        else EmployeeEnter(user);
    }
    public static void ModifiyStorage(User user){
        System.out.println("Modifier Main Menu:");
        String in=input.nextLine();
        Check(in);
        if(in.equals("logout")) Logging();
        else if(in.equals("back")||in.equals("cancel")) EmployeeEnter(user);
         else if(in.contains("Update")) {
             in=in.replaceAll("\\s+"," ");
             String[] p=in.split(" ");
             if(p.length>1){
                 int x=Integer.parseInt(p[1]);
                 storg.consume(x);
                 ModifiyStorage(user);
             }
             else {
                 System.out.println("Please Enter Something");
                 ModifiyStorage(user);
             }
         }
         else if(in.contains("Consumes")){
             in=in.replaceAll("\\s+"," ");
             ArrayList<String> y=new ArrayList<>();
             ArrayList<Integer> x=new ArrayList<>();
             String[] p=in.split(" ");
             if(p.length<2){
                 System.out.println("Please Enter Something");
                 ModifiyStorage(user);
             }
             else if(p.length%2==0){
                 System.out.println("Please Enter your request correctly");
                 ModifiyStorage(user);
             }
             else{
                 boolean anothercheck=true;
                 for(int i=1;i<p.length;i+=2){
                    try {
                        x.add(Integer.parseInt(p[i]));
                    } catch (NumberFormatException nfe){
                        anothercheck=false;
                        break;
                    }
              y.add(p[i+1]);

                 }
                 if(!storg.consmue(y,x)||!anothercheck) System.out.println("Sorry your request cannot be done");
                 else System.out.println("Done");
                 ModifiyStorage(user);
             }
         }
         else if(in.equals("Info")){
             info.ModifiyStorage();
             ModifiyStorage(user);
         }
         else ModifiyStorage(user);
    }

    public static void CostumerEnter(User user){
        System.out.println("You Entered as Costumer to SharifPlus");
       String in=input.nextLine();
        Check(in);
        if(in.equals("logout")) Logging();
      else if(in.equals("Info")) {
           info.SharifPlus();
           CostumerEnter(user);
       }
       else if(in.equals("back")) {
           try {
               FileWriter fw=new FileWriter(Out,true);
               BufferedWriter bw=new BufferedWriter(fw);
               bw.write("Username: "+user.name+" Logged Out at time: "+LocalDateTime.now());
               bw.newLine();
               bw.close();
           }catch (IOException e){
               e.printStackTrace();
           }
           LogYes();
       }
       else if(in.equals("Cafe")){
           System.out.println("Welcome to Sharif Plus Cafe :)");
           System.out.println("Type Info to get some information about ShairfPlus Cafe");
           EnterCafe(user);
       }
       else if(in.equals("Restaurant")){
           System.out.println("Welcome to Sharif Plus Restaurant :)");
           System.out.println("Type Info to get some information about ShairfPlus Restaurant");
           EnterRestaurant(user);
       }
     else CostumerEnter(user);
    }

public static void EnterCafe(User user){
        System.out.println("SharifPlus Cafe Main Menu:");
        String in=input.nextLine();
        Check(in);
    if(in.equals("logout")) Logging();
        else if(in.equals("Info")){
            info.CafeInformation();
            EnterCafe(user);
        }
        else if(in.contains("back")||in.contains("cancel"))  CostumerEnter(user);
        else if(in.equals("getMenu")) {
            SharifPlusCafe.getMenu();
            EnterCafe(user);
        }
        else if(in.contains("Add Order")) {
            Order pqr=SharifPlusCafe.AddOrder(in,user,storg,orders);
            if(pqr!=null){
                SharifPlusCafe.orderList.add(pqr);
                orderList.add(pqr);
                System.out.println("Done");

            }

            EnterCafe(user);

        }
        else if(in.equals("History of recent orders")){
            SharifPlusCafe.RecentOrders(user);
            EnterCafe(user);
        }
        else EnterCafe(user);
}
public static void EnterRestaurant(User user){
    System.out.println("SharifPlus Restaurant Main Menu");
        String in=input.nextLine();
        Check(in);
    if(in.equals("logout")) Logging();
      else  if(in.equals("Info")) {
            info.RestuarantInformation();
            EnterRestaurant(user);
        }
        else if(in.equals("back")) CostumerEnter(user);
        else if(in.equals("getMenu")) {
            SharifPlusRestaurant.getMenu();
            EnterRestaurant(user);
        }
   else if(in.contains("Add Order")){
            Order pqr=SharifPlusRestaurant.AddOrder(in,user,storg,orders);
            if(pqr!=null){
                SharifPlusRestaurant.orderList.add(pqr);
                orderList.add(pqr);
                System.out.println("Done");
            }
            EnterRestaurant(user);
        }
   else if(in.equals("History of recent orders")){
       SharifPlusRestaurant.RecentOrders(user);
       EnterRestaurant(user);
        }
   else EnterRestaurant(user);

}
    public static void main(String[] args) {
         System.out.println("\033[0;33m" + "Welcome" + System.lineSeparator() + "To Sharif  " + "\033[0;31m" + "**Plus**  " + "\033[0;34m" + "Coffee Shop");
    SharifPlusRestaurant.getStorage(storg);
    SharifPlusCafe.getStorage(storg);
   Logging();

    }
}