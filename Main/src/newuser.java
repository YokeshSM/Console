import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class newuser {
    public static Scanner sc=new Scanner(System.in);
    public boolean user() throws Exception{
         pvt p=new pvt();
       // System.out.println(p.geturl()+p.getpwd()+p.getuser());
        Class.forName("com.mysql.jdbc.Driver");
      Connection con =DriverManager.getConnection(p.geturl(),p.getuser(),p.getpwd());
      java.sql.Statement st=con.createStatement();
        System.out.println("Welcome for New User Login");
        System.out.println("Enter your name");
        String name=sc.nextLine();
        System.out.println("Enter your Password");
        String pass=sc.nextLine();
        System.out.println("Enter your Password Again");
        String pass1=sc.nextLine();
        System.out.println("Enter your Email");
        String mail=sc.nextLine();
        System.out.println("Enter your phone number");
        String ph=sc.next();
        if(!pass1.equals(pass))
        {
        System.out.print("Incorrect Password");
        return false;
        }
        else if(pass1.length()>=8){
         System.out.print("Password should contain atleast 8 characters Session Timed Out Login Again");
         return false;
        }
        else if(name.equals("")&&name.length()>=8)
        {
        System.out.print("Name is must");
        return false;
        }
        else if(mail.equals(""))
        {
        System.out.print("Mail is must");
        return false;
        }
        else if(ph.equals("")){
        System.out.print("Phone number is must");
        return false;
        }
        else{
         String v="insert into user values(0,'"+name+"','"+pass1+"','"+mail+"','"+ph+"')";
         st.executeUpdate(v);
         System.out.println("Welcome "+name);
         return true;
        }

    }
    
    
}
