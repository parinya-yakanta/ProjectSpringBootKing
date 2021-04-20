package com.parinya.backend.service;

import com.parinya.backend.entity.Social;
import com.parinya.backend.entity.User;
import com.parinya.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {

    private final SocialRepository socialRepository;

    public SocialService(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }


    public Optional<User> findByUser(User user) {
        return socialRepository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok) {



        Social entity = new Social();

        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return socialRepository.save(entity);
    }
}
