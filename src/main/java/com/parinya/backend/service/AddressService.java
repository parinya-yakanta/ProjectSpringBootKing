package com.parinya.backend.service;

import com.parinya.backend.entity.Address;
import com.parinya.backend.entity.User;
import com.parinya.backend.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findByUser(User user) {
        return addressRepository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipcode) {



        Address entity = new Address();

        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);

        return addressRepository.save(entity);
    }
}
