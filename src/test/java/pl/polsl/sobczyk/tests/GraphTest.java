/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.sobczyk.tests;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.sobczyk.model.AlgorithmFW;
import pl.polsl.sobczyk.model.Graph;
import pl.polsl.sobczyk.model.InvalidInputDataException;

/**
 * Test class for the Graph class.
 * 
 * @author Agata Sobczyk
 * @version 2.0
 */
public class GraphTest {
    
    /**
     * Default constructor for the GraphTest class.
     */
    public GraphTest() {
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
     * Parameterized test for the isNumeric method.
     *
     * @param input The input string to be tested
     * @param expectedResult The expected result (true or false)
     */
    @ParameterizedTest
    @CsvSource({
            "123, true",
            "-456, true",
            "0, true",
            "12.34, true",
            "abc, false",
            "-, false",
            "12.34.56, false"
    })
    public void testIsNumeric(String input, boolean expectedResult) {
        assertEquals( graph.isNumeric(input), expectedResult, "Niepoprawny wynik");
    }

    /**
     * Parameterized test for the checkData method.
     *
     * @param input The input string to be tested
     * @param expectedValue The expected converted value
     */
    @ParameterizedTest
    @CsvSource({
            "5, 5",
            "-3, -3",
            "x, " + Integer.MAX_VALUE
    })
    public void testCheckData(String input, int expectedValue) {
        try {
            assertEquals(graph.checkData(input), expectedValue, "Wartość zwracana powinna być równa " + expectedValue);
        } catch (InvalidInputDataException e) {
            fail("Niepoprawny wynik dla wartosci = " + input);
        }
    } 
 
    /**
     * Parameterized test for checking the exception in the checkData method.
     *
     * @param input The invalid input string to be tested
     * @param expectedErrorMessage The expected error message for the thrown exception
     */
    @ParameterizedTest
    @CsvSource({
            "abc, Niepoprawne dane",
            "-, Niepoprawne dane",
            "12.34.56, Niepoprawne dane"
    })
    public void testOfException(String input, String expectedErrorMessage) {
        InvalidInputDataException exception = assertThrows(
                InvalidInputDataException.class,
                () -> graph.checkData(input)
        );
        assertEquals(expectedErrorMessage, exception.getMessage());
    }  
}