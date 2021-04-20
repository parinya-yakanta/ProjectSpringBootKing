package com.parinya.backend.repository;

import com.parinya.backend.entity.Address;
import com.parinya.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);


}
