package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.dao.AdminDao;
import com.app.dao.OwnerDao;
import com.app.model.Admin;
import com.app.model.Owner;

/**
 * Servlet implementation class StaffEditProfileServlet
 */
@WebServlet("/staff-edit-profile")
public class StaffEditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffEditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String email = request.getParameter("email");
		Admin staff = AdminDao.viewStaff(email);
		
        request.setAttribute("email", email);
		request.setAttribute("staff", staff);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/staffEditProfile.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String staffID = request.getParameter("staffID");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String role = request.getParameter("role");
		
		Admin staff = new Admin();
	    staff.setAdminID(Integer.parseInt(staffID)); 
		staff.setFirstName(firstName);
		staff.setLastName(lastName);
		staff.setEmail(email);
		staff.setPassword(password);
		staff.setPhoneNumber(phoneNumber);
		staff.setRole(role);
		
		try {
			AdminDao.updateStaffProfile(staff);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        request.setAttribute("email", email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff-view-profile");
		dispatcher.forward(request, response);
	}

}
