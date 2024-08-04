package com.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.dao.ParcelDao;
import com.app.dao.ScheduleDao;

/**
 * Servlet implementation class StaffDeleteParcelServlet
 */
@WebServlet("/staff-delete-parcel")
public class StaffDeleteParcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffDeleteParcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 String parcelID = request.getParameter("parcelID");
	        String email = request.getParameter("email");  

	        try {
	            int status = ParcelDao.deleteParcel(parcelID);
	            if (status != 0) {
	            	request.setAttribute("email", email);
	                 response.sendRedirect(request.getContextPath() + "/staff-parcel-list?email=" + email);
	            }  
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
