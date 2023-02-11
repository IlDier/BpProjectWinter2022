import java.util.ArrayList;

public class Order {
    String Id;
    User user;
    ArrayList<Products> Product=new ArrayList<>();
    String Status="On Serving";
    void Order(String Id,User user,String Status){
        this.Id=Id;
        this.user=user;
        this.Status=Status;
    }

    public void OrderDefine(){
        System.out.println("Your Order order at time "+Id);
        for(Products value:Product) System.out.println("You Ordered: "+value.get());
        System.out.println(Status);
    }
   public void OrderDefine1(){
        System.out.println("Order with Id: "+Id+" has been ordered by user: "+user.name);
        for(Products value:Product) System.out.println(user.name+" Ordered: "+ value.get());
        System.out.println(Status);
   }
}
