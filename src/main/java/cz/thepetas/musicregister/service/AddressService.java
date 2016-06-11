package cz.thepetas.musicregister.service;

import cz.thepetas.musicregister.exception.AddressNotFound;
import cz.thepetas.musicregister.model.Address;

import java.util.List;

public interface AddressService {

    public Address create(Address address);

    public Address delete(long id) throws AddressNotFound;

    public List<Address> findAll();

//    public Address update(Address address) throws AddressNotFound;

    public Address findById(long id);

    public Address findByDetails(Address address);

}
