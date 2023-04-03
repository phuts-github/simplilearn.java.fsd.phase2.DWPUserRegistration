package com.simplilearn.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("userName");
		String pass=request.getParameter("userPass");
		String email=request.getParameter("userEmail");
		String country=request.getParameter("userCountry");
		
        try {
            // 1. Register Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("2");

            // 2. Get Connection
            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://127.0.0.1:3306/employee?serverTimezone=GMT",
                    "jdbc:mysql://localhost:3306/employee?serverTimezone=GMT",
                    "root",
                    "97257"
            );
            System.out.println("3");

            // 3. Create Statement
            PreparedStatement preparedStmnt = connection.prepareCall("{INSERT INTO EMPLOYEE_REGISTRATION VALUES(?,?,?,?)}");
            preparedStmnt.setString(1, name);
            preparedStmnt.setString(2, pass);
            preparedStmnt.setString(3, email);
            preparedStmnt.setString(4, country);
            
            int i = preparedStmnt.executeUpdate();
            if (i == 0 ) {
            	System.out.println("Record Inserted");
            }
            
            System.out.println("name - " + name + " Pass - " + pass + ", Email - " + email + ", Country - " + country);
        } catch (Exception e){
            System.out.println(e);

        };	
		
	};

}
