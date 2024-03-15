package com.aues.usersms.services;



import com.aues.usersms.model.User;
import com.aues.usersms.model.enums.Role;

import java.util.List;

public interface AdminService {

    User addUser(User user);
    void updateUser(Long userId, User user);
    void deleteUser(Long userId);
    void changeUserRole(Long userId, Role newRole);
    void activateUser(Long userId);
    void deactivateUser(Long userId);
    List<User> getAllUsers(); // Add this line

    User getUserById(Long id);

}
