package com.publiclibrary;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email= request.getParameter("uemail");
		String password= request.getParameter("epassword");
		HttpSession session= request.getSession();
		RequestDispatcher dispatcher= null;
		Connection con = null;
	
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","Siva@143247");
			PreparedStatement prep= con.prepareStatement("select * from user_info where Email=? AND Password=?");
			prep.setString(1, email);
			prep.setString(2, password);
			
			ResultSet rs=prep.executeQuery();
			 if(rs.next()) {
				 session.setAttribute("Name", rs.getString("name"));
				 dispatcher = request.getRequestDispatcher("index.html");
				 dispatcher.forward(request, response);
			 }
			 else {
				 request.setAttribute("status", "failed");
				 dispatcher = request.getRequestDispatcher("login.html");
				 dispatcher.forward(request, response);
			 }
			 dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
