package nl.highway.iungomain.Datamodel.Bouwbedrijf;

import nl.highway.iungomain.Datamodel.TableResource;

import javax.persistence.*;

@Entity
public class Bouwbedrijf extends TableResource {

    //TODO: Define more qualitfiers like @Email and @Id for the variables in the classes. See Gebruiker as example.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    private Integer kvk;
    private String naam;


    // Getters and setters

    public Integer getId() {
        return id;
    }

    public Integer getKvk() {
        return kvk;
    }

    public Bouwbedrijf withKvk(Integer kvk) {
        updateModified();
        this.kvk = kvk;
        return this;
    }

    public String getNaam() {
        return naam;
    }

    public Bouwbedrijf withNaam(String naam) {
        updateModified();
        this.naam = naam;
        return this;
    }
}
