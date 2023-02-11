import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserLists {
    ArrayList<User> users=new ArrayList<>();

    File Users=new File("C:/Users/iran/IdeaProjects/BpProjectWinter2022/Users.txt");

    UserLists(){
        try {
            Scanner input= new Scanner(Users);
            while(input.hasNextLine()){
                String usge= input.nextLine();
                if(usge.length()>0) {
                    usge = usge.replaceAll("\\s+", " ");
                    String[] us = usge.split(" ");
                    User user = new User(us[3], us[1]);
                    user.Use1(us[5]);
                    if (!users.contains(user)) users.add(user);
                }
            }
input.close();
        }catch (
                IOException e){
            e.printStackTrace();
        }
    }

}
