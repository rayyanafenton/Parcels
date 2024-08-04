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
import com.app.model.Parcel;

/**
 * Servlet implementation class StaffSearchParcel
 */
@WebServlet("/staff-search-parcel")
public class StaffSearchParcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffSearchParcelServlet() {
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
         request.setAttribute("email", email);
         
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/staffSearchParcel.jsp");
         dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parcelID = request.getParameter("parcelID");
		String email = request.getParameter("Email");
		
		List<Parcel>list = ParcelDao.searchParcel(parcelID);
    	request.setAttribute("list", list);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/staffSearchParcel.jsp");
    	dispatcher.forward(request, response);
				
	}

}
