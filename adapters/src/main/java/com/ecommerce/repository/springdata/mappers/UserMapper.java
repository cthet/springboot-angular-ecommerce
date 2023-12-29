package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.auth.SignupRequest;
import com.ecommerce.model.user.User;
import com.ecommerce.repository.springdata.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CivilityMapper.class})
public interface UserMapper {


//    @Mapping(target = "password", ignore = true)
//    @Mapping(ignore = true, target = "civilityEntity")
//    @Mapping(ignore = true, target = "id")
//    @Mapping(ignore = true, target = "role")
//    @Mapping(ignore = true, target = "cart")
//    @Mapping(ignore = true, target = "orders")
//    @Mapping(ignore = true, target = "addresses")
//    UserEntity signupRequestToUser(SignupRequest signup);


    User toUser(UserEntity user);

    @Mapping(ignore = true, target = "civilityEntity")
    UserEntity toUserEntity(User user);
}
