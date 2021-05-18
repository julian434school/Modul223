import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Citizen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "city", foreignKey = @javax.persistence.ForeignKey(name = "city_fk"))
    private City city;

    public Citizen() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
