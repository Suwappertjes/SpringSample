package nl.highway.iungomain.Datamodel.Project;

import nl.highway.iungomain.Datamodel.Bouwbedrijf.Bouwbedrijf;
import nl.highway.iungomain.Datamodel.TableResource;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project extends TableResource {
    // Table fields
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    private String naam;
    private String locatie;
    private Date startDatum;
    private Date eindDatum ;

    @ManyToOne
    @JoinColumn
    private Bouwbedrijf bouwbedrijf;


    // Getters and setters
    public Integer getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Project withNaam(String naam) {
        updateModified();
        this.naam = naam;
        return this;
    }

    public String getLocatie() {
        return locatie;
    }

    public Project withLocatie(String locatie) {
        updateModified();
        this.locatie = locatie;
        return this;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public Project withStartDatum(Date startDatum) {
        updateModified();
        this.startDatum = startDatum;
        return this;
    }

    public Date getEindDatum() {
        return eindDatum;
    }

    public Project withEindDatum(Date eindDatum) {
        updateModified();
        this.eindDatum = eindDatum;
        return this;
    }

    public Bouwbedrijf getBouwbedrijf() {
        return bouwbedrijf;
    }

    public Project withBouwbedrijf(Bouwbedrijf bouwbedrijf) {
        updateModified();
        this.bouwbedrijf = bouwbedrijf;
        return this;
    }
}
