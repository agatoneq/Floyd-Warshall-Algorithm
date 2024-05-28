/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.sobczyk.model;

import java.util.*;

/**
 * This class represents a graph.
 *
 * @author Agata Sobczyk
 * @version 3.0
 */
public class Graph implements GraphInterface {

    private ArrayList<ArrayList<Integer>> graph;
    private int v; //size of the graph

    /**
     * Default constructor.
     */
    public Graph() {
    }

    /**
     * Parameterized constructor.
     *
     * @param size Size of the graph
     * @param weights 2D array of edge weights
     */
    public Graph(int size, ArrayList<ArrayList<Integer>> weights) {
        this.v = size;

        this.graph = new ArrayList<>(size);
        for (ArrayList<Integer> row : weights) {
            ArrayList<Integer> newRow = new ArrayList<>(size);
            for (Integer weight : row) {
                newRow.add(weight);
            }
            this.graph.add(newRow);
        }
    }

    /**
     * Get the graph.
     *
     * @return 2D array representing the graph
     */
    ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }

    /**
     * Set the graph.
     *
     * @param graph 2D array representing the graph
     */
    public void setGraph(ArrayList<ArrayList<Integer>> graph) {
        this.graph = graph;
        this.v = graph.size();
    }

    /**
     * Get the size of the graph.
     *
     * @return Size of the graph
     */
    public int getV() {
        return v;
    }

    /**
     * Check if a string is numeric.
     *
     * @param str The input string
     * @return True if the input is numeric, false otherwise
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check and convert input data.
     *
     * @param input The input data
     * @return Converting data for valid type
     * @throws InvalidInputDataException If the input is invalid
     */
    public int checkData(String input) throws InvalidInputDataException {
        if (input.equalsIgnoreCase("x")) {
            return Integer.MAX_VALUE;
        } else if (isNumeric(input)) {
            return Integer.parseInt(input);
        } else {
            throw new InvalidInputDataException("Niepoprawne dane");
        }
    }
    
    /**
     * Create a graph with parameters.
     *
     * It is used in unit tests.
     *
     * @param parameters Array of parameters (size and weights)
     * @return Graph object created with the provided parameters
     */
    public Graph createGraphWithParameters(String... parameters) {
        int size = Integer.parseInt(parameters[0]);
        ArrayList<ArrayList<Integer>> weights = new ArrayList<>();
        int weight;
        int currentIndex = 0;
        String input = null;

        for (int i = 0; i < size; i++) {
            weights.add(new ArrayList<>());

            for (int j = 0; j < size; j++) {
                if (!(i == size - 1 && j == size - 1)) {
                    input = parameters[currentIndex];
                    currentIndex++;
                }

                if (i == j) {
                    weights.get(i).add(0);
                    continue;
                } else {
                    if (input.equalsIgnoreCase("x")) {
                        weight = Integer.MAX_VALUE;
                    } else {
                        weight = Integer.parseInt(input);
                    }
                }
                weights.get(i).add(weight);
            }
        }

        Graph graph = new Graph(size, weights);
        return graph;
    }
}
