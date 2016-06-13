package cz.thepetas.carregister.data.model;


import com.fasterxml.jackson.annotation.JsonView;
import cz.thepetas.carregister.data.json.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_VEHICLE", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class})
    @Id
    @GeneratedValue
    private Long id;

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class})
    @NotNull
    @Size(min = 1, max = 30)
    private String brand;

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class})
    @NotNull
    @Size(min = 1, max = 30)
    private String model;

    @JsonView(View.SummaryFromCar.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
