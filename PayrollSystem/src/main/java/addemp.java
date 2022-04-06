import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
@WebServlet(urlPatterns="/register")
public class addemp extends HttpServlet
{
	public void init() {
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws 
	IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String empid=req.getParameter("empid");
		String title=req.getParameter("title");
		String dob=req.getParameter("dob");
		String addr=req.getParameter("addr");
		String state=req.getParameter("state");
		int phone=Integer.valueOf(req.getParameter("phone"));
		String panno=req.getParameter("panno");
		String jid=req.getParameter("jid");
		String ename=req.getParameter("ename");
		String doj=req.getParameter("doj");
		String city=req.getParameter("city");
		int pin=Integer.valueOf(req.getParameter("pin"));
		String mail=req.getParameter("mail");
		String gender=req.getParameter("gender");
		//out.print(enroll+"<br>"+user+"<br>"+pwd+"<br>"+gen+"<br>"+email+"<br>"+ph+"<br>"+addr+"<br>");
		out.print(title+"<br>"+dob+"<br>"+ename);
		try
		{
			String url="jdbc:mysql://localhost:3307/payroll";
			//out.print("hello1");
			Class.forName("com.mysql.cj.jdbc.Driver");//loading drive
			//out.print("hello2");
			Connection c=DriverManager.getConnection(url,"root","root");//building
			//out.print("hello3");
			String q2="insert into employee() values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps=c.prepareStatement(q2);
			//out.print("hello4");
			ps.setString(1,empid);
			ps.setString(2,title);
			ps.setString(3,jid);
			ps.setString(4,ename);
			ps.setString(5,dob);
			ps.setInt(6,phone);
			ps.setString(7,mail);
			ps.setString(8,gender);
			ps.setString(9,panno);
			ps.setString(10,doj);
			ps.setString(11,state);
			ps.setString(12,city);
			ps.setString(13,addr);
			ps.setInt(14,pin);
			//out.print("hello5");
			ps.executeUpdate();
			out.println("<br>"+" Data Inserted Successfully !");
		}
		catch(Exception e)
		{
			out.print(e.getStackTrace());
		}
	}
	public void destroy() {
	}
}