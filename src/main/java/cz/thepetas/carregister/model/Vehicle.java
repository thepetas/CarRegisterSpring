package cz.thepetas.carregister.model;


import cz.thepetas.carregister.Enum.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String brand;

    @Column
    @NotNull
    private String model;

    @Column
    private VehicleType type;

}
