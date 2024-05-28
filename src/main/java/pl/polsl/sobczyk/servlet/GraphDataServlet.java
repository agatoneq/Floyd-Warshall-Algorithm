/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.sobczyk.servlet;

import java.io.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pl.polsl.sobczyk.model.AlgorithmE;
import pl.polsl.sobczyk.model.AlgorithmFW;
import pl.polsl.sobczyk.model.Graph;
import pl.polsl.sobczyk.model.GraphE;
import pl.polsl.sobczyk.model.InvalidInputDataException;

/**
 ** Servlet responsible for handling requests related to the graph data.
 * Retrieves the initial graph values, executes the Floyd-Warshall algorithm and
 * displays the result. It also saves the result as a cookie.
 *
 * Additionally, this servlet interacts with a database to persist algorithm and
 * graph data.
 *
 * @author Agata Sobczyk
 * @version 2.0
 */
@WebServlet("/GraphData")
public class GraphDataServlet extends HttpServlet {

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
            int size = Integer.parseInt(request.getParameter("size"));
            Graph graph = new Graph();
            boolean exceptionCaught = false;
            ArrayList<ArrayList<Integer>> weights = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                weights.add(new ArrayList<>());
                for (int j = 0; j < size; j++) {
                    try {
                        String paramName = "pole" + (i + 1) + "_" + (j + 1);
                        String paramValue = request.getParameter(paramName);
                        weights.get(i).add(graph.checkData((String) paramValue));
                    } catch (InvalidInputDataException e) {
                        exceptionCaught = true;
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wystąpił wyjątek niewłaściwych danych. Podaj wartości liczbowe lub x");
                        return;
                    }
                }
            }

            StringBuilder sb2 = new StringBuilder();
            for (ArrayList<Integer> row : weights) {
                for (Integer weight : row) {
                    if (weight == Integer.MAX_VALUE) {
                        sb2.append("INF").append("  ");
                    } else {
                        sb2.append(weight).append("  ");
                    }
                }
                sb2.append("|  ");
            }

            if (sb2.length() >= 5) {
                sb2.delete(sb2.length() - 5, sb2.length());
            }

            String newString = sb2.toString();

            if (!exceptionCaught) {
                graph = new Graph(size, weights);

                GraphE initialGraphEntity = new GraphE();
                initialGraphEntity.setSize(size);
                initialGraphEntity.setGraphsValues(newString);

                AlgorithmFW algorithm = new AlgorithmFW(graph);
                algorithm.executeAlgorithm();

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Wynik algorytmu Floyda-Warshalla</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Oto Twoje rozwiązanie:</h1>");

                StringBuilder sb = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                out.println("<table>");
                sb.append("<table>");
                for (int i = 0; i < size; i++) {
                    out.println("<tr>");
                    sb.append("<tr>");
                    for (int j = 0; j < size; j++) {

                        if (algorithm.getD().get(i).get(j) == algorithm.getInf()) {
                            out.println("<td><input type=\"text\" name=\"pole" + (i + 1) + "_" + (j + 1) + "\" style=\"width: 30px;\" readonly value=\"INF\" /></td>");
                            sb.append("<td><input type=\"text\" name=\"pole");
                            sb.append((i + 1) + "_" + (j + 1));
                            sb.append("\" style=\"width: 30px;\" readonly value=\"INF\" /></td>");
                            sb3.append("INF").append("  ");

                        } else {
                            out.println("<td><input type=\"text\" name=\"pole" + (i + 1) + "_" + (j + 1) + "\" style=\"width: 30px;\" readonly value=" + algorithm.getD().get(i).get(j) + " /></td>");
                            sb.append("<td><input type=\"text\" name=\"pole");
                            sb.append((i + 1) + "_" + (j + 1));
                            sb.append("\" style=\"width: 30px;\" readonly value=");
                            sb.append(algorithm.getD().get(i).get(j));
                            sb.append(" /></td>");
                            sb3.append(algorithm.getD().get(i).get(j)).append("  ");
                        }
                    }
                    out.println("</tr>");
                    sb.append("</tr>");
                    sb3.append("|  ");
                }
                out.println("</table>");
                sb.append("</table>");

                out.println("</form>");
                out.println("</body>");
                out.println("</html>");

                if (sb3.length() >= 5) {
                    sb3.delete(sb3.length() - 5, sb3.length());
                }
                String newString2 = sb3.toString();

                GraphE finalGraphEntity = new GraphE();
                finalGraphEntity.setSize(size);
                finalGraphEntity.setGraphsValues(newString2);

                AlgorithmE algorithmEntity = new AlgorithmE();
                initialGraphEntity.setAlgorithm(algorithmEntity);
                initialGraphEntity.setGraphType("initial");
                finalGraphEntity.setAlgorithm(algorithmEntity);
                finalGraphEntity.setGraphType("final");
                persist(algorithmEntity);
                persist(initialGraphEntity);
                persist(finalGraphEntity);

                String encodedValue = URLEncoder.encode(sb.toString(), "UTF-8");
                Cookie resultCookie = new Cookie("lastGraphResult", encodedValue);
                response.addCookie(resultCookie);
            }
        }
    }

    /**
     * Persists an object to the database.
     *
     * @param object The object to be persisted
     */
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
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
        return "Servlet retrieves initial graph values, executes the algorithm, displays the result, and saves it as a cookie.";
    }// </editor-fold>
}
