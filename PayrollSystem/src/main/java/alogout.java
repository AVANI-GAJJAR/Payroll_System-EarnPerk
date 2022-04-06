import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns="/alogout")
public class alogout extends HttpServlet
{
	
	public void init()  throws ServletException {}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		HttpSession s=req.getSession();
		int count=(int)s.getAttribute("counter");
		String username=(String)s.getAttribute("username");
		//out.print(username);
		String pass=(String)s.getAttribute("pass");
		count++;
		out.println("Total number of times admin loggedin: <br>");
		out.println(count+"<br>");
		//count=1
		try
		{
			String url="jdbc:mysql://localhost:3307/payroll";
			Class.forName("com.mysql.cj.jdbc.Driver");//loading drive
			Connection c=DriverManager.getConnection(url,"root","root");//building
			//out.println("hello"+count);
			String q2="update adminlogin set counter=? where uname=?";
			PreparedStatement ps=c.prepareStatement(q2);
			ps.setInt(1,count);
			ps.setString(2,username);
			ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		s.invalidate();
		out.print("Successfully Logged Out!!"+"<br>"+"<button><a href=\"adminlogin.html\">Login Again</a></button>");
		out.print("<br>"+"<button><a href=\"MAIN-PAGE.html\">Home</a></button>");
		
	}
	
	public void destroy()
	{
		
	}
}

