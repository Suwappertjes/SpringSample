package nl.highway.iungomain.Datamodel.Kandidaat_Werklied;

import nl.highway.iungomain.Datamodel.Functie;
import nl.highway.iungomain.Datamodel.TableResource;

import javax.persistence.*;

@Entity
public class Kandidaat_Werklied extends TableResource {
    // Table fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    private String voornaam;
    private String achternaam;
    private Functie functie;


    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        updateModified(); this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        updateModified(); this.achternaam = achternaam;
    }

    public Functie getFunctie() {
        return functie;
    }

    public void setFunctie(Functie functie) {
        this.functie = functie;
    }
}