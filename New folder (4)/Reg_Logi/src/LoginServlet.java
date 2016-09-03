

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	public void init() throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			stmt=conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String firstname=request.getParameter("firstname");
		String password=request.getParameter("password");
		
		try {
			rs=stmt.executeQuery("select password from registration where firstname='"+firstname+"'");
			while(rs.next())
			{
			String password1=rs.getString("password");
			if(password1.equals(password))
			{
				out.print("Hi..you are succeessfully logged in");
		}
			else
			{
				out.print("Sorry....you are not succeessfully logged");
			}
			}
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
	}


	
	


