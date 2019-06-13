package nl.highway.iungomain.Datamodel.Gebruiker;

import nl.highway.iungomain.Datamodel.RechtNaam;
import nl.highway.iungomain.Datamodel.TableResource;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Gebruiker extends TableResource {
    // Table fields
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @NotBlank
    @Size(max = 64)
    private String gebruikersnaam;

    @NotBlank
    @Size(max = 64)
    private String voornaam;

    @NotBlank
    @Size(max = 64)
    private String achternaam;

    @NotBlank
    @Size(max = 64)
    @Email
    private String email;

    @NotBlank
    @Size(max = 128)
    private String wachtwoord;

    @ElementCollection(targetClass = RechtNaam.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<RechtNaam> rechten = new HashSet<>();

    public Gebruiker(String gebruikersnaam,
                     String voornaam,
                     String achternaam,
                     String email,
                     String wachtwoord,
                     Set<RechtNaam> rechten) {
        this.gebruikersnaam = gebruikersnaam;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
    }

    public Gebruiker(){

    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public Gebruiker withGebruikersnaam(String gebruikersnaam) {
        updateModified();
        this.gebruikersnaam = gebruikersnaam;
        return this;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public Gebruiker withVoornaam(String voornaam) {
        updateModified();
        this.voornaam = voornaam;
        return this;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Gebruiker withAchternaam(String achternaam) {
        updateModified();
        this.achternaam = achternaam;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Gebruiker withEmail(String email){
        updateModified();
        this.email = email;
        return this;
    }

    //TODO: If I JSONIGNORE THIS WE CAN"T CHANGE A PLAINTEXT PASSWORD TO AN ENCRYPTED ONE

    public String getWachtwoord() {
        return wachtwoord;
    }

    public Gebruiker withWachtwoord(String wachtwoord) {
        updateModified();
        this.wachtwoord = wachtwoord;
        return this;
    }

    public Set<RechtNaam> getRechten() {
        return rechten;
    }

    public Gebruiker withRechten(Set<RechtNaam> rechten) {
        this.rechten = rechten;
        return this;
    }
}
