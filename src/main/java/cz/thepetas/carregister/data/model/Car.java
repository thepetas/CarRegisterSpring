package cz.thepetas.carregister.data.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.thepetas.carregister.data.enums.VehicleType;
import cz.thepetas.carregister.data.json.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
@DiscriminatorValue(value = VehicleType.CAR)
public class Car extends Vehicle {

    @JsonView({View.SummaryFromPerson.class, View.SummaryFromCar.class})
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
