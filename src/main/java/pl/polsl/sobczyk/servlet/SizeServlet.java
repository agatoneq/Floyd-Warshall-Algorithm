/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.sobczyk.servlet;

import java.io.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

/**
 * Servlet responsible for handling requests related to providing the initial
 * size of the graph.
 *
 * @author Agata Sobczyk
 * @version 1.0
 */
@WebServlet("/Size")
public class SizeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String size = request.getParameter("size");
        try (PrintWriter out = response.getWriter()) {
            int rows = Integer.parseInt(size);
            int cols = Integer.parseInt(size);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Podawanie wartosci</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Podaj wartości grafu początkowego:</h1>");
            out.println("<form action=\"GraphData\" method=\"GET\">");
            out.println("<p>Podaj wagi krawedzi grafu, jesli nie ma polaczenia to wpisz x.</p>");
            out.println("<input type=\"hidden\" name=\"size\" value=\"" + size + "\" />");

            out.println("<table>");
            for (int i = 0; i < rows; i++) {
                out.println("<tr>");
                for (int j = 0; j < cols; j++) {
                    if (i == j) {
                        out.println("<td><input type=\"text\" name=\"pole" + (i + 1) + "_" + (j + 1) + "\" style=\"width: 30px;\" readonly value=\"0\" /></td>");
                    } else {
                        out.println("<td><input type=\"text\" name=\"pole" + (i + 1) + "_" + (j + 1) + "\" style=\"width: 30px;\" /></td>");
                    }
                }
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("<input type=\"submit\" value=\"Zatwierdz\" />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Retrieves the size of the graph and creates text fields for entering initial graph values based on it.";
    }// </editor-fold>
}