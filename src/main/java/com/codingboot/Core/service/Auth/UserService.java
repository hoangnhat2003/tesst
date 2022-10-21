package com.codingboot.Core.service.Auth;

import com.codingboot.Core.domain.entity.Role;
import com.codingboot.Core.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    List<User> getUsers();
    User getUser(String username);
    User findByEmail(String email);
}
