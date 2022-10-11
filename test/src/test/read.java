package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/readServlet")

public class read extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String e=req.getParameter("email");
		String p=req.getParameter("pass");
		
		System.out.println(e+" "+p);
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/interview","root","root");
			System.out.println("Connection Successful");
			
			PreparedStatement pstm=con.prepareStatement("select * from student where email=?");
			pstm.setString(1, e);
			
			ResultSet rs=pstm.executeQuery();
			String name=null;
			String city=null;
			String email=null;
			String password=null;
			
			while(rs.next())
			{
				name=rs.getString("name");
				city=rs.getString("city");
				email=rs.getString("email");
				password=rs.getString("password");
			}
			if(e.equals(email)&& p.equals(password))
			{
				System.out.println("Record Read");
			}
			else
			{
				System.out.println("Error");
			}
			con.close();
		}
		catch (ClassNotFoundException | SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
}
