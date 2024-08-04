package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.dao.ParcelDao;
import com.app.dao.ScheduleDao;
import com.app.model.Schedule;

/**
 * Servlet implementation class OwnerDeleteScheduleServlet
 */
@WebServlet("/owner-delete-schedule")
public class OwnerDeleteScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerDeleteScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
			String parcels = request.getParameter("parcels");
			String scheduleID = request.getParameter("scheduleID");
	        String email = request.getParameter("email");  

	        try {
	            int status = ScheduleDao.deleteSchedule(scheduleID);
	            if (status != 0) {
	            	ParcelDao.updateParcelStatusToNotCollected(parcels);
	            	 request.setAttribute("email", email);
	                 response.sendRedirect(request.getContextPath() + "/owner-parcel-schedule?email=" + email);
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
