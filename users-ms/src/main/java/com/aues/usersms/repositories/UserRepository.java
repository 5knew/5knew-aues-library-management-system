package com.aues.usersms.repositories;
import com.aues.usersms.model.User;
import com.aues.usersms.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{


    User findByEmail(String email);

    User findByRole(Role role);
}
