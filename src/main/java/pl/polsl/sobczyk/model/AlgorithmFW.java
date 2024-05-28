/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.sobczyk.model;

import java.util.*;
import java.util.stream.*;

/**
 * Represents the Floyd-Warshall algorithm for finding shortest paths in a
 * graph.
 *
 * @author Agata Sobczyk
 * @version 3.0
 */
public class AlgorithmFW {

    private int inf = Integer.MAX_VALUE; //Represents infinity
    private Graph graph;
    private ArrayList<ArrayList<Integer>> d; //Array 2D for distances of the graph's edges

    /**
     * Default constructor.
     */
    public AlgorithmFW() {
    }

    /**
     * Constructor for AlgorithmFW.
     *
     * @param graph The graph for which the algorithm is applied
     */
    public AlgorithmFW(Graph graph) {
        this.graph = graph;
        this.d = new ArrayList<>(graph.getGraph());
    }

    /**
     * Execute the Floyd-Warshall algorithm to find the shortest paths.
     */
    public void executeAlgorithm() {
        IntStream.range(0, graph.getV())
                .forEach(i -> IntStream.range(0, graph.getV())
                .forEach(j -> IntStream.range(0, graph.getV())
                .forEach(k -> {
                    int ik = d.get(i).get(k);
                    int kj = d.get(k).get(j);

                    if (ik != inf && kj != inf && ik + kj < d.get(i).get(j)) {
                        d.get(i).set(j, ik + kj);
                    }
                })
                )
                );
    }

    /**
     * Get the matrix representing the shortest paths.
     *
     * @return 2D array representing the shortest paths matrix
     */
    public ArrayList<ArrayList<Integer>> getD() {
        return this.d;
    }
    
    /**
     * Get the value representing infinity.
     *
     * @return The value representing infinity
     */
    public int getInf() {
        return this.inf;
    }
}
