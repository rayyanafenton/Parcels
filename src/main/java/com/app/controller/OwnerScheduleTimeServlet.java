package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.app.dao.OwnerDao;
import com.app.dao.ParcelDao;
import com.app.dao.ScheduleDao;
import com.app.model.Owner;
import com.app.model.Parcel;
import com.app.model.Schedule;

/**
 * Servlet implementation class OwnerScheduleTimeServlet
 */
@WebServlet("/owner-schedule-time")
public class OwnerScheduleTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScheduleDao scheduleDao = new ScheduleDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerScheduleTimeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String[] parcelIds = request.getParameterValues("parcelIds");   

        String parcelIdsString = "";

        if (parcelIds != null) {
            for (String parcelId : parcelIds) {
            	parcelIdsString = String.join(",", parcelIds);
            }
        } else {
            System.out.println("No parcels selected.");
        }
        
        Owner owner = OwnerDao.viewProfile(email);
        request.setAttribute("email", email);
        request.setAttribute("owner", owner);
        request.setAttribute("parcels", parcelIdsString);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ownerScheduleTime.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ownerID = request.getParameter("ownerID");
		String parcels = request.getParameter("parcels");
		String scheduledDate = request.getParameter("scheduledDate");
		String scheduledTime = request.getParameter("scheduledTime");
		String email = request.getParameter("email");

		Schedule schedule = new Schedule();
		schedule.setOwnerID(ownerID);
		schedule.setParcels(parcels);
		schedule.setScheduledDate(scheduledDate);
		schedule.setScheduledTime(scheduledTime);
		
		try {
            int i = ScheduleDao.addSchedule(schedule);
            if (i != 0) {
                ParcelDao.updateParcelStatusToScheduled(parcels);

                request.setAttribute("email", email);
                response.sendRedirect(request.getContextPath() + "/owner-parcel-schedule?email=" + email);
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
;