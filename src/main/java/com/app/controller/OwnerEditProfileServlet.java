package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.dao.OwnerDao;
import com.app.model.Owner;

/**
 * Servlet implementation class OwnerEditProfileServlet
 */
@WebServlet("/owner-edit-profile")
public class OwnerEditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OwnerDao ownerDao = new OwnerDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerEditProfileServlet() {
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
		Owner owner = OwnerDao.viewProfile(email);
		
        request.setAttribute("email", email);
		request.setAttribute("owner", owner);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ownerEditProfile.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ownerID = request.getParameter("ownerID");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		
		Owner owner = new Owner();
	    owner.setOwnerID(Integer.parseInt(ownerID)); 
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setEmail(email);
		owner.setPassword(password);
		owner.setPhoneNumber(phoneNumber);
		
		try {
			ownerDao.updateProfile(owner);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("owner-view-profile");
		dispatcher.forward(request, response);
	}

}
