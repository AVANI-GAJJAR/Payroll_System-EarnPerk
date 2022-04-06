import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
@WebServlet(urlPatterns="/alogin")

public class alogin extends HttpServlet
{
	int count;
	public void init() 
	{
		count=0;	
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String user=req.getParameter("username");
		String pwd=req.getParameter("password");
		//out.println(user);
		//out.println(pwd);
		String username="",pass="";
		
		try
		{
			String url="jdbc:mysql://localhost:3307/payroll";
			Class.forName("com.mysql.cj.jdbc.Driver");//loading drive
			Connection c=DriverManager.getConnection(url,"root","root");//building
			String q2="select uname, pwd,counter from adminlogin where uname=? and pwd=?";
			PreparedStatement ps=c.prepareStatement(q2);
			ps.setString(1,user);
			ps.setString(2,pwd);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) 
			{
				username=rs.getString("uname");
				pass=rs.getString("pwd");
				count=rs.getInt("counter");
				//out.print("hello"+username+" "+pass);
			}	
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		if (user.equals(username)&&pwd.equals(pass))
		{
			HttpSession s=req.getSession();
			s.setAttribute("counter",count);
			s.setAttribute("username",username);
			s.setAttribute("pass", pass);
			out.print("<font color=blue><h1>"+"Successfully Logged in."+"</h1></font>");
			//out.print(username);
			//out.print("<br>"+"<a href=\"alogout\">Logout</a>");
			RequestDispatcher rd=req.getRequestDispatcher("/a_dashboard.html");  
	        rd.forward(req, res);
		}
		else
		{
			
			out.print("<font color=red>"+"Wrong Password or email.");
			RequestDispatcher rd=req.getRequestDispatcher("/alogin.html");  
	        rd.include(req, res);
		}
	}
}
