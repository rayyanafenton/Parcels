package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.dao.AdminDao;
import com.app.dao.ParcelDao;
import com.app.model.Admin;
import com.app.model.Parcel;

/**
 * Servlet implementation class AdminEditStaffServlet
 */
@WebServlet("/admin-edit-staff")
public class AdminEditStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditStaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String adminID = request.getParameter("adminID");

        try {
            Admin admin = AdminDao.staffListEdit(adminID);
            request.setAttribute("admin", admin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminEditStaff.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminID = request.getParameter("adminID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");  

        Admin admin = new Admin();
        admin.setAdminID(Integer.parseInt(adminID));
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);
        admin.setPhoneNumber(phoneNumber);
        admin.setPassword(password);

		try {
			  int i = AdminDao.updateStaff(admin);
	            if (i != 0) {
	                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-staff-list");
	        		dispatcher.forward(request, response);
	            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
