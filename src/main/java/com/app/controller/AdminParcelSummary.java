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
import com.app.model.Collection;
import com.app.model.Parcel;

/**
 * Servlet implementation class AdminParcelSummary
 */
@WebServlet("/admin-parcel-summary")
public class AdminParcelSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminParcelSummary() {
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
        
		Collection summary = ParcelDao.viewParcelSummary();
    	List<Parcel>list = ParcelDao.adminParcelSummary();
        request.setAttribute("summary", summary);
        request.setAttribute("list", list);
        request.setAttribute("email", email);
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminParcelSummary.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
