import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns="/deletion")
public class delemp extends HttpServlet
{
public void init() 
{
}
public void doPost(HttpServletRequest req,HttpServletResponse res) 
throws IOException,ServletException
{
res.setContentType("text/html");
PrintWriter out=res.getWriter();
String eid=req.getParameter("eid");
String ename=req.getParameter("ename");
//out.println(enroll+" "+pwd+" "+n_pwd);
try
{
String url="jdbc:mysql://localhost:3307/payroll";
Class.forName("com.mysql.cj.jdbc.Driver");//loading drive
Connection 
c=DriverManager.getConnection(url,"root","root");//building
String q2="delete from employee where empid=?";
PreparedStatement ps=c.prepareStatement(q2);
ps.setString(1,eid);
int n=ps.executeUpdate();
//out.println(n);
if(n==1)
{
out.println("<br>"+" Data of "+ename+" deleted Successfully !");
}
}
catch(Exception e)
{
e.getStackTrace();
}
}
public void destroy() {
}
}