package cz.thepetas.carregister.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    @Size(min = 1, max = 30)
    private String street;

    @NotNull
    @Column
    @Size(min = 1, max = 30)
    private String houseNumber;

    @NotNull
    @Column
    @Size(min = 1, max = 30)
    private String zipCode;

    @NotNull
    @Column
    @Size(min = 1, max = 30)
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<Person> persons;


    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    public boolean compareDetails(Address address) {
        return street.equals(address.street) && houseNumber.equals(address.houseNumber)
                && zipCode.equals(address.zipCode) && city.equals(address.city);
    }
}

