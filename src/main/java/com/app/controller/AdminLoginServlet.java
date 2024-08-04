package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.app.dao.AdminDao;
import com.app.dao.OwnerDao;
import com.app.dao.ParcelDao;
import com.app.model.Admin;
import com.app.model.Collection;
import com.app.model.Owner;
import com.app.model.Parcel;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminLogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
	      String password = request.getParameter("password");
	        
	        Admin admin = new Admin();
	        admin.setEmail(email);
	        admin.setPassword(password);
	        
	        try {
	            int loggedIntAdmin = AdminDao.loginAdmin(admin);
	            
	            if (loggedIntAdmin == 1) {
	   
	                request.setAttribute("email", email);
	                
	                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-parcel-summary");
	                dispatcher.forward(request, response);
	            } else {
	                request.setAttribute("errorMessage", "Invalid email or password");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminLogin.jsp");
	                dispatcher.forward(request, response);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "An error occurred while trying to log you in. Please try again later.");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminLogin.jsp");
	            dispatcher.forward(request, response);
	        }
	}

}
