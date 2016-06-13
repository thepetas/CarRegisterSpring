package cz.thepetas.carregister.data.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.thepetas.carregister.data.json.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class, View.SummaryFromAddress.class})
    @Id
    @GeneratedValue
    private Long id;

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class, View.SummaryFromAddress.class})
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class, View.SummaryFromAddress.class})
    @NotNull
    @Size(min = 1, max = 30)
    private String surname;

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class, View.SummaryFromAddress.class})
    @NotNull
    @Size(min = 9, max = 10)
    @Column(unique = true)
    private String birthNumber;

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonView(View.SummaryFromPerson.class)
    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicles;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Long getCntCars() {
        return (vehicles != null) ? vehicles.size() : (long) 0;
    }
}
