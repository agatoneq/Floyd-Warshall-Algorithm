/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.sobczyk.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Entity class representing the Graph entity in the database. The class is
 * annotated with the @Entity annotation, indicating that instances of this
 * class are entities that can be stored in the database.
 *
 * A Graph entity has a many-to-one relationship with an AlgorithmE entity,
 * representing the algorithm associated with the graph.
 *
 * @author Agata Sobczyk
 * @version 1.0
 */
@Entity
public class GraphE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int size;
    private String graphsValues;
    private String graphType;

    // Many-to-one relationship with AlgorithmE entity
    @ManyToOne
    private AlgorithmE algorithm;

    /**
     * Get the unique identifier of the Graph entity.
     *
     * @return The unique identifier (ID) of the Graph entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the Graph entity.
     *
     * @param id The unique identifier (ID) to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Algorithm entity associated with this Graph entity.
     *
     * @return The Algorithm entity associated with this Graph.
     */
    public AlgorithmE getAlgorithm() {
        return algorithm;
    }

    /**
     * Set the Algorithm entity associated with this Graph entity.
     *
     * @param algorithm The Algorithm entity to be associated with this Graph.
     */
    public void setAlgorithm(AlgorithmE algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Get the size of the Graph.
     *
     * @return The size of the Graph.
     */
    public int getSize() {
        return size;
    }

    /**
     * Set the size of the Graph.
     *
     * @param size The size to be set for the Graph.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Get the values of the Graph.
     *
     * @return The values of the Graph.
     */
    public String getGraphsValues() {
        return graphsValues;
    }

    /**
     * Set the values of the Graph.
     *
     * @param graphsValues The values to be set for the Graph.
     */
    public void setGraphsValues(String graphsValues) {
        this.graphsValues = graphsValues;
    }

    /**
     * Get the type of the Graph.
     *
     * @return The type of the Graph.
     */
    public String getGraphType() {
        return graphType;
    }

    /**
     * Set the type of the Graph.
     *
     * @param graphType The type to be set for the Graph.
     */
    public void setGraphType(String graphType) {
        this.graphType = graphType;
    }

    /**
     * Generate a hash code value for the Graph entity.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Check if this Graph entity is equal to another object.
     *
     * @param object The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GraphE)) {
            return false;
        }
        GraphE other = (GraphE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Get a string representation of the Graph entity.
     *
     * @return A string representation of the Graph entity.
     */
    @Override
    public String toString() {
        return "pl.polsl.sobczyk.model.Graph[ id=" + id + " ]";
    }

}
