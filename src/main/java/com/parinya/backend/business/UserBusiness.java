package com.parinya.backend.business;

import com.parinya.backend.entity.User;
import com.parinya.backend.exception.BaseException;
import com.parinya.backend.exception.FileException;
import com.parinya.backend.exception.UserException;
import com.parinya.backend.mapper.UserMapper;
import com.parinya.backend.model.MLoginRequest;
import com.parinya.backend.model.MRegisterRequest;
import com.parinya.backend.model.MRegisterResponse;
import com.parinya.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public String login(MLoginRequest request) throws BaseException {
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();
        }

        //TODO:
        String token = "JWT To Do";


        return token;
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {

        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        return userMapper.toRegisterResponse(user);

    }

    public String uploadProfilePicture(MultipartFile file) throws BaseException {
        if (file == null) {
            throw FileException.fileNull();
        }

        if (file.getSize() > 1048576 * 2) {
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw FileException.unsupported();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportedTypes.contains(contentType)) {
            throw FileException.unsupported();
        }

        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
