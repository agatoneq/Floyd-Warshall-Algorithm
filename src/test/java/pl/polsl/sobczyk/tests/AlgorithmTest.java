/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.sobczyk.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.sobczyk.model.AlgorithmFW;
import pl.polsl.sobczyk.model.Graph;

/**
 * Test class for the AlgorithmFW class.
 * 
 * @author Agata Sobczyk
 * @version 2.0
 */
public class AlgorithmTest {
    
    /**
     * Default constructor for the AlgorithmFW class.
     */
    public AlgorithmTest() {
    }
    
    private Graph graph2;
    private AlgorithmFW algorithm;
    private Graph graph;
    
    /**
     * Set up test environment before each test.
     */    
    @BeforeEach
    public void setUp() {
        graph2 = new Graph();   
        graph = new Graph();
        String[] tab = {"2", "3", "4"};
        graph = graph2.createGraphWithParameters(tab); 
        algorithm = new AlgorithmFW(graph);
    }
    
    /**
     * Test the execution of the Floyd-Warshall algorithm.
     */
    @Test
    public void testExecuteAlgorithm() {
        ArrayList<ArrayList<Integer>> weights = new ArrayList<>();
        weights.add(new ArrayList<>(List.of(0, 2, 4, Integer.MAX_VALUE, Integer.MAX_VALUE)));
        weights.add(new ArrayList<>(List.of(Integer.MAX_VALUE, 0 , 3, 3, Integer.MAX_VALUE)));
        weights.add(new ArrayList<>(List.of(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, -1, 4)));
        weights.add(new ArrayList<>(List.of(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 2)));
        weights.add(new ArrayList<>(List.of(Integer.MAX_VALUE, Integer.MAX_VALUE,Integer.MAX_VALUE, Integer.MAX_VALUE, 0)));

        graph = new Graph(5, weights);
        algorithm = new AlgorithmFW(graph);
        
        algorithm.executeAlgorithm();
        
        ArrayList<ArrayList<Integer>> distances = algorithm.getD();

        // Sprawdź wyniki algorytmu
        // Możesz dostosować te asercje do swoich danych testowych
        assertEquals(distances.get(0).get(0), 0);
        assertEquals(distances.get(0).get(1), 2);
        assertEquals(distances.get(0).get(2), 4);
        assertEquals(distances.get(0).get(3), 3);
        assertEquals(distances.get(0).get(4), 5);
        
        assertEquals(distances.get(1).get(0), Integer.MAX_VALUE);
        assertEquals(distances.get(1).get(1), 0);
        assertEquals(distances.get(1).get(2), 3);
        assertEquals(distances.get(1).get(3), 2);
        assertEquals(distances.get(1).get(4), 4);
        
        assertEquals(distances.get(2).get(0), Integer.MAX_VALUE);
        assertEquals(distances.get(2).get(1), Integer.MAX_VALUE);
        assertEquals(distances.get(2).get(2), 0);
        assertEquals(distances.get(2).get(3), -1);
        assertEquals(distances.get(2).get(4), 1);
        
        assertEquals(distances.get(3).get(0), Integer.MAX_VALUE);
        assertEquals(distances.get(3).get(1), Integer.MAX_VALUE);
        assertEquals(distances.get(3).get(2), Integer.MAX_VALUE);
        assertEquals(distances.get(3).get(3), 0);
        assertEquals(distances.get(3).get(4), 2);
        
        assertEquals(distances.get(4).get(0), Integer.MAX_VALUE);
        assertEquals(distances.get(4).get(1), Integer.MAX_VALUE);
        assertEquals(distances.get(4).get(2), Integer.MAX_VALUE);
        assertEquals(distances.get(4).get(3), Integer.MAX_VALUE);
        assertEquals(distances.get(4).get(4), 0);
    }
}
