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
import java.util.List;
import jakarta.persistence.OneToMany;

/**
 * Entity class representing the Algorithm entity in the database. The class is
 * annotated with the @Entity annotation, indicating that instances of this
 * class are entities that can be stored in the database.
 *
 * An Algorithm entity has a one-to-many relationship with GraphE entities,
 * representing the initial and final graphs associated with the algorithm.
 *
 * @author Agata Sobczyk
 * @version 1.0
 */
@Entity
public class AlgorithmE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // One-to-many relationship with GraphE entities
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "algorithm")
    private List<GraphE> initialFinalgraphs;

    /**
     * Get the unique identifier of the Algorithm entity.
     *
     * @return The unique identifier (ID) of the Algorithm entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the Algorithm entity.
     *
     * @param id The unique identifier (ID) to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the list of GraphE entities associated with this Algorithm entity.
     *
     * @return The list of GraphE entities representing the initial and final
     * graphs.
     */
    public List<GraphE> getGraphs() {
        return initialFinalgraphs;
    }

    /**
     * Set the list of GraphE entities associated with this Algorithm entity.
     *
     * @param initialFinalgraphs The list of GraphE entities to be set.
     */
    public void setGraphs(List<GraphE> initialFinalgraphs) {
        this.initialFinalgraphs = initialFinalgraphs;
    }

    /**
     * Generate a hash code value for the Algorithm entity.
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
     * Check if this Algorithm entity is equal to another object.
     *
     * @param object The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AlgorithmE)) {
            return false;
        }
        AlgorithmE other = (AlgorithmE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Get a string representation of the Algorithm entity.
     *
     * @return A string representation of the Algorithm entity.
     */
    @Override
    public String toString() {
        return "pl.polsl.sobczyk.model.AlgorithmEntity[ id=" + id + " ]";
    }
}
