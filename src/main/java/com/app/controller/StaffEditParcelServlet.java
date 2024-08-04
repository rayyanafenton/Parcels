package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.app.dao.ParcelDao;
import com.app.dao.ScheduleDao;
import com.app.model.Parcel;
import com.app.model.Schedule;

/**
 * Servlet implementation class StaffEditParcelServlet
 */
@WebServlet("/staff-edit-parcel")
public class StaffEditParcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffEditParcelServlet() {
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
	            Parcel parcel = ParcelDao.trackParcel(parcelID);
	            request.setAttribute("parcel", parcel);
	       	 	request.setAttribute("email", email);

	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/staffEditParcel.jsp");
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

		 String parcelID = request.getParameter("parcelID");
	        String ownerID = request.getParameter("ownerID");
	        String parcelName = request.getParameter("parcelName");
	        String dateReceived = request.getParameter("dateReceived");
	        String status = request.getParameter("status");
	        String dateCollected = request.getParameter("dateCollected");
	        String email = request.getParameter("email");  

	        Parcel parcel = new Parcel();
	        parcel.setParcelID(parcelID);
	        parcel.setOwnerID(ownerID);
	        parcel.setParcelName(parcelName);
	        parcel.setDateReceived(dateReceived);
	        parcel.setDateCollected(dateCollected);
	        parcel.setStatus(status);
	       
			try {
				  int i = ParcelDao.updateParcel(parcel);
		            if (i != 0) {
		            	 request.setAttribute("email", email);
		                 response.sendRedirect(request.getContextPath() + "/staff-parcel-list?email=" + email);
		            }  
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
;