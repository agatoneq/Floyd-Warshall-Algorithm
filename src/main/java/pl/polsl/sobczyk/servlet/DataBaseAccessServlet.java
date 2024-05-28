/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.sobczyk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import pl.polsl.sobczyk.model.GraphE;

/**
 * Servlet for accessing and displaying graph data from the database. Retrieves
 * and displays initial and final graphs stored in the database.
 *
 * The servlet uses Java Persistence API (JPA) to interact with the database. It
 * retrieves GraphE entities from the database and displays their information.
 *
 * @author Agata Sobczyk
 * @version 1.0
 */
@WebServlet("/DataBaseAccess")
public class DataBaseAccessServlet extends HttpServlet {

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
            out.println("<title>Servlet DataBaseAccess</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h2>Twoje grafy początkowe i końcowe odczytane z bazy danych:</h2>");
            out.println("<td><input type=\"text\" \" style=\"width: 50px;\" readonly value=\"ID\" /></td>");
            out.println("<td><input type=\"text\" \" style=\"width: 70px;\" readonly value=\"typ\" /></td>");
            out.println("<td><input type=\"text\" \" style=\"width: 50px;\" readonly value=\"rozmiar\" /></td>");
            out.println("<td><input type=\"text\" \" style=\"width: 500px;\" readonly value=\"wartości\" /></td>");
            out.println("<hr>");
            findObject(out);
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Retrieves and displays GraphE entities from the database.
     *
     * @param out PrintWriter for writing HTML content
     */
    public void findObject(PrintWriter out) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT g FROM GraphE g");
            List<GraphE> graphList = query.getResultList();
            for (GraphE graph : graphList) {

                out.println("<td><input type=\"text\" \" style=\"width: 50px;\" readonly value=" + graph.getId() + " /></td>");
                out.println("<td><input type=\"text\" \" style=\"width: 70px;\" readonly value=" + graph.getGraphType() + " /></td>");
                out.println("<td><input type=\"text\" \" style=\"width: 40px;\" readonly value=" + graph.getSize() + " /></td>");
                out.println(graph.getGraphsValues());
                out.println("<hr>");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
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
        return "Servlet for accessing and displaying graph data from the database.";
    }// </editor-fold>

}
