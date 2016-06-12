package cz.thepetas.carregister.model;

import cz.thepetas.carregister.enums.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
@DiscriminatorValue(value = VehicleType.CAR)
public class Car extends Vehicle {

    @Column
    @NotNull
    @Size(min = 1, max = 30)
    private String idMark;


    public String getIdMark() {
        return idMark;
    }

    public void setIdMark(String idMark) {
        this.idMark = idMark;
    }
}
