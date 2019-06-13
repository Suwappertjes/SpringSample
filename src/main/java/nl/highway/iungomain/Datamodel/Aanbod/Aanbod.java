package nl.highway.iungomain.Datamodel.Aanbod;

import nl.highway.iungomain.Datamodel.TableResource;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Aanbod extends TableResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;


    // Getters and Setters
    public Integer getId() {
        return id;
    }
}
