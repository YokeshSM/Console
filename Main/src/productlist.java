import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.*;
public class productlist {
  public static int sum=0;
    public static Scanner sc=new Scanner(System.in);
    public int list(int v,String name) throws Exception{
       pvt p=new pvt();
       // System.out.println(p.geturl()+p.getpwd()+p.getuser());
        Class.forName("com.mysql.jdbc.Driver");
      Connection con =DriverManager.getConnection(p.geturl(),p.getuser(),p.getpwd());
      java.sql.Statement st=con.createStatement();
      String h="",b="";
   //   System.out.println("Enter the Product name you need");
      if(v==0)
      {
       h=sc.nextLine();
       b=sc.nextLine();
      }
      if(v==1)
      {
        sc.nextLine();
        h=sc.nextLine();
        b=sc.nextLine();
      }
      String query="select * from products where prname like '"+h+"' and companyname like '"+b+"' ";
      ResultSet rs=st.executeQuery(query);
      int price=0,c1=0;
      int tot=0;
      if(rs.next())
      {
       // System.out.print(rs.getInt(1)+" ");
        System.out.print("Product Name: "+rs.getString(2)+" ");
        System.out.print("Brand Name: "+rs.getString(3)+" ");
        System.out.print("Category: "+rs.getString(4)+" ");
       // System.out.print(rs.getInt(5)+" ");
        System.out.print("Price: "+rs.getInt(6)+" ");
        c1=rs.getInt(5);
        price=rs.getInt(6);
        b=rs.getString(3);
        System.out.println("Do You Want To Buy type Yes Or No");
        String f=sc.next();
        if(f.equals("Yes"))
        {
          System.out.println("How Much "+h+"you need");
          int ite=sc.nextInt();
          if(ite<=c1)
          {
           tot=tot+(price*ite);
           sum=sum+tot;
           int le=c1-ite;  
           int soldcost=0,soldcount=0; 
           String sv="select soldcost,soldcount from stocks where prd_name like '"+h+"' and brand like '"+b+"'";
           ResultSet rv=st.executeQuery(sv);
           if(rv.next())
           {
            soldcost=+rv.getInt(1)+ite;
            soldcount=+rv.getInt(2)+sum;
           }
           String paid="not paid";
           String upquery="insert into bills values(0,'"+name+"','"+h+"','"+ite+"','"+tot+"','"+paid+"')";
          String update="update products set count='"+le+"' where prname like '"+h+"' ";
          String update1="update stocks set soldcount='"+soldcount+"' where prd_name like '"+h+"' and brand like '"+b+"' ";
          String update2="update stocks set soldcost='"+soldcost+"' where prd_name like '"+h+"' and brand like '"+b+"' ";
          st.executeUpdate(update1);
          st.executeUpdate(update2);
          st.executeUpdate(upquery);
          st.executeUpdate(update);
          // System.out.println(tot);
          System.out.println("Do you want to buy more type Yes Or No");
          String bu=sc.next();
          if(bu.equals("Yes"))
          {
            System.out.println("Enter the Product name and Brand Name you need");
            list(1,name);
            
          }
          else{
           return tot;
          }
        }
        else
        {
          System.out.println("We have not enough stock ,come after few days");
          return 0;
        }
        }
      }
      else
      {
        System.out.print("No Match Found");

      }
      return tot;
    }

    public  void showlist(String name)throws Exception{
        pvt p=new pvt();
        System.out.println(p.geturl()+p.getpwd()+p.getuser());
        Class.forName("com.mysql.jdbc.Driver");
       Connection con =DriverManager.getConnection(p.geturl(),p.getuser(),p.getpwd());
         java.sql.Statement st=con.createStatement();
         String d="select * from products";
        ResultSet rs=st.executeQuery(d);
        while(rs.next())
        {
             System.out.println("Id "+rs.getInt(1)+" ");
        System.out.println("Product Name "+rs.getString(2)+" ");
        System.out.println("Brand Name "+rs.getString(3)+" ");
        System.out.println("Category "+rs.getString(4)+" ");
        // System.out.print(rs.getInt(5)+" ");
        System.out.println("Price "+rs.getInt(6)+" ");
        System.out.println();
        }
        System.out.println("Enter the Product name and Brand Name you need");
        list(0,name);
         int amt=0;
        if(sum==0)
        System.out.print("Thank you for shopping");
        else{
        String v="select * from bills where name like '"+name+"' and paid='not paid'";
        ResultSet rd= st.executeQuery(v);
        while(rd.next())
        {
          System.out.println("Bill ID: "+rd.getInt(1));
          System.out.println("UserName: "+rd.getString(2));
          System.out.println("Product: "+rd.getString(3));
          System.out.println("Count: "+rd.getInt(4));
          System.out.println("Amount: "+rd.getInt(5));
          //System.out.println(rd.getString(6));
        }
        System.out.println("Enter "+sum+"rs to complete payment");
        amt=sc.nextInt();
         System.out.println("Total Amount= "+sum);
         if(amt==sum)
         {
          String update="update bills set paid='paid' where name like '"+name+"'";
          st.executeUpdate(update) ;
          System.out.print("Thank you for Shopping");
          sum=0;
         }
        }


}
}

