package cz.thepetas.musicregister.service.impl;


import cz.thepetas.musicregister.Repository.AddressRepository;
import cz.thepetas.musicregister.exception.AddressNotFound;
import cz.thepetas.musicregister.model.Address;
import cz.thepetas.musicregister.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    @Resource
    private AddressRepository addressRepositoty;

    @Override
    @Transactional
    public Address create(Address address) {
        Address finded = findByDetails(address);

        if (finded != null)
            return finded;
        else
            return addressRepositoty.save(address);
    }

    @Override
    @Transactional(rollbackFor = AddressNotFound.class)
    public Address delete(long id) throws AddressNotFound {
        Address deleted = addressRepositoty.findOne(id);

        if (deleted == null) {
            throw new AddressNotFound();
        }
        addressRepositoty.delete(deleted);
        return deleted;
    }

    @Override
    public List<Address> findAll() {
        return addressRepositoty.findAll();
    }

    @Override
    public Address findById(long id) {
        return addressRepositoty.findOne(id);
    }

    @Override
    public Address findByDetails(Address address) {
        Address finded = addressRepositoty.findByDetails(address.getStreet(), address.getHouseNumber(),
                address.getZipCode(), address.getCity());
        return finded;
    }
}
