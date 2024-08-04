package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.app.dao.OwnerDao;
import com.app.dao.ParcelDao;
import com.app.model.Owner;
import com.app.model.Parcel;

/**
 * Servlet implementation class OwnerTrackParcel
 */
@WebServlet("/owner-track-parcel")
public class OwnerTrackParcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerTrackParcelServlet() {
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
        
        try {
        	List<Parcel>list = ParcelDao.trackParcelOwner(email);
        	request.setAttribute("email", email);
        	request.setAttribute("list", list);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ownerTrackParcel.jsp");
        	dispatcher.forward(request, response);
           
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while trying to log you in. Please try again later.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ownerLogin.jsp");
            dispatcher.forward(request, response);
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
