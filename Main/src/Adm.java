import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
public class Adm {
    public static Scanner sc=new Scanner(System.in);
    public boolean adlogin() throws Exception{
    pvt p=new pvt();
    Class.forName("com.mysql.jdbc.Driver");
      Connection con =DriverManager.getConnection(p.geturl(),p.getuser(),p.getpwd());
      java.sql.Statement st=con.createStatement();
      System.out.println("Welcome Admin");
      System.out.println("Enter Your Name");
      String Name=sc.next();
      System.out.println("Enter Your Password");
      String pass=sc.next();
      String check="select name ,password from admin where name like '"+Name+"' ";
      ResultSet rs=st.executeQuery(check);
      if(rs.next())
      {
        String a=rs.getString(1);
        String b=rs.getString(2);
        if(Name.equals(a)&&pass.equals(b))
        {
            System.out.println("Welcome admin, "+Name);
           return true;
        }
        else
        {
        System.out.println("Give the proper details");
        return false;
        }
      }
      else
      {
      System.out.println("No Match Found");
      return false;
      }
    }
    public  void adminops()throws Exception{
        pvt p=new pvt();
        Class.forName("com.mysql.jdbc.Driver");
      Connection con =DriverManager.getConnection(p.geturl(),p.getuser(),p.getpwd());
      java.sql.Statement st=con.createStatement();
      System.out.println("Do you want to See the Stocks? enter Yes or no");
      String op=sc.next();
      if(op.equals("Yes"))
      {
        String sel="select * from stocks";
        ResultSet rs=st.executeQuery(sel);
        while(rs.next())
        {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getInt(6));
            System.out.println(rs.getString(7));
        }
      }
      System.out.println("Do you want to update the stocks");
      String opt=sc.next();
      if(opt.equals("Yes"))
      {
        System.out.println("Enter the Product Name");
        String name=sc.next();
        System.out.println("Enter the Product Brand");
        String brand=sc.next();
        System.out.println("Enter the Agent Name");
        sc.nextLine();
        String agent=sc.nextLine();
        System.out.println("Enter the Agency Name");
        String agency=sc.nextLine();
        System.out.println("Enter the count");
        int c=sc.nextInt();
        System.out.println("Enter the Date you bought");
        String date=sc.next();
        String ins="insert into stocks values(0,'"+name+"','"+brand+"','"+agent+"','"+agency+"','"+c+"','"+date+"',0,0)";
        int d=st.executeUpdate(ins);
        if(d>=1)
        System.out.print("Added to the stocks");
        else
        System.out.print("Please give the details properly");
      }
      else{
        System.out.println("Thank You Admin");
      }
      System.out.println("do you want to update the shop's products? type Yes Or No");
      String t=sc.next();
      if(t.equals("Yes"))
      {
        System.out.println("Enter Product Name:");
        sc.nextLine();
        String name=sc.nextLine();

        System.out.println("Enter Company Name");
        String comp=sc.nextLine();
        System.out.println("Enter Category");
        String cate=sc.nextLine();
        System.out.println("Enter Count");
        int count =sc.nextInt();
        System.out.println("Enter Price");
        int price=sc.nextInt();
        String q1="select name,count from stocks where name like '"+name+"'";
        ResultSet rt=st.executeQuery(q1);
        int m=0;
        if(rt.next())
        m=rt.getInt(2);
        if(count>m)
        System.out.print("Check the count");
        else {
            String s="insert into products values(0,'"+name+"','"+comp+"','"+cate+"','"+count+"','"+price+"')";
            int rv=st.executeUpdate(s);
            if(rv>=1)
            System.out.println("Updation Sucessfull");
            else
            System.out.println("Updation Unsucessfull");
        }
    }
    }
}
