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
 * Servlet implementation class OwnerScheduleParcel
 */
@WebServlet("/owner-parcel-schedule")
public class OwnerScheduleParcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerScheduleParcelServlet() {
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
		String date = "2024-08-17";

		
	        try {
	        	List<Schedule>list = ScheduleDao.viewScheduleCustomer(email);
	        	request.setAttribute("email", email);
	        	request.setAttribute("list", list);
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ownerParcelSchedule.jsp");
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
		doGet(request, response);
	}

}
