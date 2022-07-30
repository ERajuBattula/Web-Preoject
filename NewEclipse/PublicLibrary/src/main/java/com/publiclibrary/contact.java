package com.publiclibrary;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class contact
 */
@WebServlet("/contact")
public class contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contact() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name= request.getParameter("uname");
		String email= request.getParameter("uemail");
        String message= request.getParameter("umessage");
        Connection con=null;
        RequestDispatcher dispatcher=null;
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","Siva@143247");
			PreparedStatement prep = con.prepareStatement("insert into contact(Name,Email,Message)values(?,?,?)");
			prep.setString(1, name);
			prep.setString(2, email);
			prep.setString(3, message);
			int row =prep.executeUpdate();
            dispatcher= request.getRequestDispatcher("Contact.html");
            if(row>0) {
                request.setAttribute("status", "sucess");

            }
            else {
                request.setAttribute("status", "failed");

            }
            dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
