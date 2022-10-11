package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/InsertServlet")

public class insert extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String nm=req.getParameter("name");
		String ct=req.getParameter("city");
		String email=req.getParameter("email");
		String pass=req.getParameter("pass");
		
		System.out.println(nm+" "+ct+" "+email+" "+pass);
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/interview","root","root");
			System.out.println("Connection Successful");
			
			PreparedStatement pstm=con.prepareStatement("insert into student(name,city,email,password) value(?,?,?,?)");
			pstm.setString(1, nm);
			pstm.setString(2, ct);
			pstm.setString(3, email);
			pstm.setString(4, pass);
			
			int i=pstm.executeUpdate();
			if(i!=0)
			{
				System.out.println("Record Inserted");
			}
			else
			{
				System.out.println("Error");
			}
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
}
