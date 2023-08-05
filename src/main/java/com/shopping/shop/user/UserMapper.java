package com.shopping.shop.user;

import org.mapstruct.*;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @AfterMapping
    default void afterEachMappingToUser(@MappingTarget User user){
        if(user.getNationality() == null)
            user.setNationality("unknown");
    }
    @Mappings({
            @Mapping(source = "birthDay",target = "birthDay",qualifiedByName = "birthDayTimestampToDate")
    })
    User toUser(UserDTO userDTO);


    @AfterMapping
    default void afterEachMappingToUserDTO(User user,@MappingTarget UserDTO userDTO){
        if(userDTO.getFirstName() == null)
            userDTO.setFirstName("unknown");
    }
    @Mappings({
            @Mapping(source = "birthDay",target = "birthDay",qualifiedByName = "birthDayDateToTimestamp"),
            @Mapping(source = "password",target = "password",ignore = true)
    })
    UserDTO toUserDTO(User user);


    List<User> toUsers(List<UserDTO> userDTOS);


    List<UserDTO> toUserDTOs(List<User> users);

    @Named("birthDayTimestampToDate")
    default Date toDate(Long timestamp){
        if(timestamp != null)
            return new Date(timestamp);
        return null;
    }

    @Named("birthDayDateToTimestamp")
    default Long toTimestamp(Date date){
        if(date != null)
            return date.getTime();
        return null;
    }


}

