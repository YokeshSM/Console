import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class verify {

    //  String query="select * from user";
    public  boolean vfy (String name,String Password) throws Exception
    {
      String query="select username,userpwd from user where userpwd like "+Password+" ";
      pvt p=new pvt();
      // System.out.println(p.geturl()+p.getpwd()+p.getuser());
      Class.forName("com.mysql.jdbc.Driver");
      Connection con =DriverManager.getConnection(p.geturl(),p.getuser(),p.getpwd());
      java.sql.Statement st= con.createStatement();
      ResultSet rs=st.executeQuery(query);
      if(rs.next())
      {
      if(rs.getString(1).equals(name)&&rs.getString(2).equals(Password))
      {
      return true;
      }
      else{
        return false;
      }
    }
     else
    return false;

}
}
