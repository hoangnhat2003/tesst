package com.codingboot.Core.service.Auth;

import com.codingboot.Core.domain.entity.Role;
import com.codingboot.Core.domain.entity.User;
import com.codingboot.Core.domain.repository.RoleRepository;
import com.codingboot.Core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User saveUser(User user) {
//      log.info("save user {} to database",user.getUserName());
      return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
//        log.info("save role {} to database",role.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role  {} to user {} ",roleName,username);
       // AppUser user = userRepository.findByUsername(username);
      //  AppRole role = roleRepository.findByName(roleName);
       // user.getRoles().add(role);
    }


    @Override
    public List<User> getUsers(){
        log.info("Fetching all users");
        //String sql="select id as \"id\", name as \"name\", username as \"username\",password as  \"password\" from user ";
      //  String sql="SELECT  [id]  as \"id\",[name] as \"name\" ,[username] as \"username\" ,[password] as \"password\" FROM [dbfhs].[dbo].[User]";
//        String sql="SELECT  distinct\n" +
//                "t1.[USER_ID]  as \"userId\",\n" +
//                "t1.[USER_NAME] as \"userName\" ,\n" +
//                "t1.[ENCRYTED_PASSWORD] as \"encrytedPassword\",\n" +
//                "t1.[ENABLED] as \"enabled\",\n" +
//                "t2.[ROLE_ID]  as \"roles\"\n" +
//                "FROM [dbfhs].[dbo].[APP_USER] t1\n" +
//                "left join [dbfhs].[dbo].[USER_ROLE] t2 on t2.[USER_ID] = t1.[USER_ID]";
//        List<AppUser> users = new ArrayList<AppUser>();
//        users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AppUser.class));
//        return users;
        return userRepository.findAll();
    }
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
