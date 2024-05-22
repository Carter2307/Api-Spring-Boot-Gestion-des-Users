package com.apilab.user.utils;

import com.apilab.user.model.User;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public  void userPatcher(User existingUser, User incompleteUser) throws IllegalAccessException {

        //GET THE COMPILED VERSION OF THE CLASS
        Class<?> userClass = User.class;
        Field[] userFields = userClass.getDeclaredFields();

        System.out.println(userFields.length);

        for (Field field : userFields) {
            System.out.println(field.getName());
            //CANT ACCESS IF THE FIELD IS PRIVATE
            field.setAccessible(true);

            //CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING INTERN
            Object value = field.get(incompleteUser);
            if (value != null) {
                field.set(existingUser, value);
            }
            //MAKE THE FIELD PRIVATE AGAIN
            field.setAccessible(false);
        }

    }

}