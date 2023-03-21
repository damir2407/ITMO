package com.example.weblabb4.service;


import com.example.weblabb4.entity.RoleEntity;
import com.example.weblabb4.entity.UserEntity;
import com.example.weblabb4.exception.UserAlreadyExistException;
import com.example.weblabb4.exception.UserNotFoundException;
import com.example.weblabb4.repository.RoleRepo;
import com.example.weblabb4.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntity saveUser(UserEntity userEntity) throws UserAlreadyExistException {
        if (userRepo.findByUsername(userEntity.getUsername()) != null) {
            throw new UserAlreadyExistException("A user with the same name already exists");
        }
        if (roleRepo.findByName("ROLE_USER") == null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName("ROLE_USER");
            roleEntity.setId(1);
            roleRepo.save(roleEntity);
        }
        RoleEntity userRole = roleRepo.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepo.save(userEntity);
    }


    public UserEntity findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public UserEntity findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        UserEntity userEntity = findByUsername(username);
        if (userEntity == null) {
            throw new UserNotFoundException("This user does not exist!");
        } else if (passwordEncoder.matches(password, userEntity.getPassword())) {
            return userEntity;
        } else throw new UserNotFoundException("Incorrect username or password!");
    }
}

