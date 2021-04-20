package com.parinya.backend.repository;

import com.parinya.backend.entity.Social;
import com.parinya.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<User> findByUser(User user);


}
