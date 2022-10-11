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
@WebServlet("/updateServlet")

public class update extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String nm=req.getParameter("name");
		String e=req.getParameter("email");
		
		System.out.println(nm+" "+e);
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/interview","root","root");
			System.out.println("Connection Successful");
			
			PreparedStatement pstm=con.prepareStatement("update student set name=? where email=?");
			pstm.setString(1, nm);
			pstm.setString(2, e);
			
			int i=pstm.executeUpdate();
			if(i!=0)
			{
				System.out.println("Updated");
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
