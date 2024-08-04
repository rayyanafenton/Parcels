package com.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.app.dao.ScheduleDao;
import com.app.model.Owner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class OwnerGetAvailableSlot
 */
@WebServlet("/owner-available-slot")
public class OwnerGetAvailableSlot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerGetAvailableSlot() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(request.getReader());
	        String scheduledDate = jsonNode.get("scheduledDate").asText();

	        List<String> bookedSlots = ScheduleDao.getAvailableSlots(scheduledDate);

	        String jsonResponse = objectMapper.writeValueAsString(bookedSlots);

	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print(jsonResponse);
	        out.flush();
	    }
}
