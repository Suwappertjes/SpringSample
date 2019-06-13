package nl.highway.iungomain.Datamodel.User;

import nl.highway.iungomain.Datamodel.RoleName;
import nl.highway.iungomain.Datamodel.TableResource;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class UserEntity extends TableResource {
    // Table fields
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @NotBlank
    @Size(max = 64)
    private String username;

    @NotBlank
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @Size(max = 64)
    private String lastName;

    @NotBlank
    @Size(max = 64)
    @Email
    private String email;

    @NotBlank
    @Size(max = 128)
    private String password;

    @ElementCollection(targetClass = RoleName.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<RoleName> roles = new HashSet<>();

    public UserEntity(String username,
                      String firstName,
                      String lastName,
                      String email,
                      String password,
                      Set<RoleName> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity(){

    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity withUsername(String username) {
        updateModified();
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity withFirstName(String firstName) {
        updateModified();
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity withLastName(String lastName) {
        updateModified();
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity withEmail(String email){
        updateModified();
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity withPassword(String password) {
        updateModified();
        this.password = password;
        return this;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public UserEntity withRoles(Set<RoleName> roles) {
        this.roles = roles;
        return this;
    }
}
