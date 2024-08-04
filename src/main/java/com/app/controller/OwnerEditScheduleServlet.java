package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.dao.ScheduleDao;
import com.app.model.Schedule;

/**
 * Servlet implementation class OwnerEditSchedule
 */
@WebServlet("/owner-edit-schedule")
public class OwnerEditScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OwnerEditScheduleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scheduleID = request.getParameter("scheduleID");
        String email = request.getParameter("email");  

        try {
            Schedule schedule = ScheduleDao.viewScheduleCustomerEdit(Integer.parseInt(scheduleID));
            request.setAttribute("schedule", schedule);
       	 	request.setAttribute("email", email);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ownerEditSchedule.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scheduleID = request.getParameter("scheduleID");
        String scheduledDate = request.getParameter("scheduledDate");
        String scheduledTime = request.getParameter("scheduledTime");
        String email = request.getParameter("email");  

        Schedule schedule = new Schedule();
        schedule.setScheduleID(Integer.parseInt(scheduleID));
        schedule.setScheduledDate(scheduledDate);
        schedule.setScheduledTime(scheduledTime);
       
		try {
			  int status = ScheduleDao.updateSchedule(schedule);
	            if (status != 0) {
	            	 request.setAttribute("email", email);
	                 response.sendRedirect(request.getContextPath() + "/owner-parcel-schedule?email=" + email);
	            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
