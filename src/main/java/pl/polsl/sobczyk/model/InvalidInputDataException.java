/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.sobczyk.model;

/**
 * Exception class for invalid input data.
 *
 * @author Agata Sobczyk
 * @version 1.0
 */
public class InvalidInputDataException extends Exception {

    /**
     *
     * @param message
     */
    public InvalidInputDataException(String message) {
        super(message);
    }
}
