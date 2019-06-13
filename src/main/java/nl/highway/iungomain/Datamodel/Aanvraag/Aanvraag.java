package nl.highway.iungomain.Datamodel.Aanvraag;

import javax.persistence.*;
import java.util.Date;
import nl.highway.iungomain.Datamodel.Functie;
import nl.highway.iungomain.Datamodel.TableResource;

@Entity
public class Aanvraag extends TableResource {
    // Table fields
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    private Functie functie;
    private Integer aantal;
    private Date startdatum;
    private Date einddatum;


    // Getters and setters
    public Integer getId() {
        return id;
    }

    public Enum getFunctie() {
        return functie;
    }

    public void setFunctie(Functie functie) {
        updateModified(); this.functie = functie;
    }

    public Integer getAantal() {
        return aantal;
    }

    public void setAantal(Integer aantal) {
        updateModified(); this.aantal = aantal;
    }

    public Date getStartdatum() {
        return this.startdatum;
    }

    public void setStartdatum(Date startdatum) {
        updateModified(); this.startdatum = startdatum;
    }

    public Date getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(Date einddatum) {
        updateModified(); this.einddatum = einddatum;
    }
}
