import java.util.Scanner; 
public class App1 {

    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
         System.out.println("Enter 1 for User Login");
         System.out.println("Enter 2 for New User Login");
         System.out.println("Enter 3 for Admin Login");
         nameclass n=new nameclass();
         newuser us=new newuser();
         verify m=new verify();
         productlist pl=new productlist();
         Adm ad=new Adm();
         int t=sc.nextInt();
         if(t==1) // Graphite Pencil
         {
           System.out.println("Enter User Name");
           sc.nextLine();
           String s=sc.nextLine();
            n.setname(s);
           System.out.println("Enter Password");
           String p1=sc.nextLine();
            n.setPassword(p1);
            if(m.vfy(s,p1))
            {
                System.out.println("Do you want to shop anything Type Yes or No");
                String op=sc.nextLine();
                if(op.equals("Yes"))
                {
                   pl.showlist(s);
                }
                else
                System.out.println("Thank you for visiting");

            }
            else
            System.out.println("False");
         }
         if(t==2){
            System.out.println("Welcome to New User Login");
            System.out.println("Kindly Enter the Credentials Correctly");
              us.user();
        //   String upquery="insert into user"
         }
         if(t==3)
         {
             System.out.println("Welcome to Admin Login");
             if(!ad.adlogin())
             System.out.println("Please Go Back");
             else{
                System.out.println("Login Successful");
                  ad.adminops();
             }


         }
    }
}
