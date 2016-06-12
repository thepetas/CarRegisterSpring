package cz.thepetas.carregister.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "cars")
public class Car extends Vehicle {

    @Column
    @NotNull
    private String idMark;


    public String getIdMark() {
        return idMark;
    }

    public void setIdMark(String idMark) {
        this.idMark = idMark;
    }
}
