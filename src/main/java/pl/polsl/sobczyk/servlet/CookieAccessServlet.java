/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.sobczyk.servlet;

import java.io.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.net.URLDecoder;

/**
 * Servlet for accessing and displaying the last generated graph result stored
 * in cookies.
 *
 * @author Agata Sobczyk
 * @version 1.0
 */
@WebServlet("/CookieAccess")
public class CookieAccessServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CookiesAccess</title>");
            out.println("</head>");
            out.println("<body>");

            Cookie[] cookies = request.getCookies();
            String lastGraphResult = "Nie generowałeś jeszcze grafu końcowego.";

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("lastGraphResult")) {
                        String encodedValue = cookie.getValue();
                        lastGraphResult = URLDecoder.decode(encodedValue, "UTF-8");
                        break;
                    }
                }
            }

            out.println("<h2>Twój ostatni wynik:</h2>");
            out.println("<p>" + lastGraphResult + "</p>");
            out.println("<hr>");
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
        return "Servlet for accessing and displaying the last generated graph result stored in cookies.";
    }// </editor-fold>
}
