package com.apilab.user.service;

import com.apilab.user.model.User;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface UserService {
     User saveUser(User user);
     List<User> getAllUsers();
     Optional<User> getById(long id);
     void deleteUser(long id);
     Optional<User> updateUser(long id, User user);
}
