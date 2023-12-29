//package com.ecommerce.repository.springdata.mappers;
//
//import com.ecommerce.model.user.User;
//import com.ecommerce.repository.springdata.entity.UserEntity;
//
//
//public class UserConverter {
//
//    public static User toUser(UserEntity userEntity) {
//        User user = new User();
//        user.setId(userEntity.getId());
//        user.setFirstName(userEntity.getFirstName());
//        user.setLastName(userEntity.getLastName());
//        user.setEmail(userEntity.getEmail());
//        user.setRole(userEntity.getRole());
//        user.setPassword(userEntity.getPassword());
//        user.setCivility(CivilityConverter.toCivility(userEntity.getCivilityEntity()));
//        return user;
//    }
//
//    public static UserEntity toUserEntity(User user) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(user.getId());
//        userEntity.setFirstName(user.getFirstName());
//        userEntity.setLastName(user.getLastName());
//        userEntity.setEmail(user.getEmail());
//        userEntity.setPassword(user.getPassword());
//        userEntity.setRole(user.getRole());
//        userEntity.setCivilityEntity(CivilityConverter.toCivilityEntity(user.getCivility()));
//        return userEntity;
//    }
//}
