package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.UserDTO;
import com.asciipic.journalize.models.User;

public class UserTransformer {
    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();



        //complete user
       // userDTO.setUsername();

        return userDTO;
    }

    public User toModel(UserDTO userDTO){
        User user = new User();

        //complete orms
        return user;
    }
}
