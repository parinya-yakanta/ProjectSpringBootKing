package com.parinya.backend.mapper;

import com.parinya.backend.entity.User;
import com.parinya.backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}
