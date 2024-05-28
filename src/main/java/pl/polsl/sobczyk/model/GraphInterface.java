/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.polsl.sobczyk.model;
import java.util.*;

/**
 * This interface defines methods for a graph.
 * 
 * @author Agata Sobczyk
 * @version 1.0
 */
public interface GraphInterface {
    /**
     * Sets the graph representation using a 2D array of integers.
     *
     * @param graph 2D array representing the graph
     */
    void setGraph(ArrayList<ArrayList<Integer>> graph);
    
    /**
     * Gets the size (number of vertices) of the graph.
     *
     * @return Size of the graph
     */    
    int getV();
    
    /**
     * Checks if a given string is numeric.
     *
     * @param str The input string
     * @return True if the input is numeric, false otherwise
     */
    boolean isNumeric(String str);
    
    /**
     * Checks and converts input data for a valid type.
     *
     * @param input The input data
     * @return Converted data for a valid type
     * @throws InvalidInputDataException If the input is invalid
     */
    int checkData(String input) throws InvalidInputDataException;
}
