package com.xaviervinicius.labschedule.services;

import com.xaviervinicius.labschedule.dto.CreateUserDto;
import com.xaviervinicius.labschedule.exceptions.EmailAlreadyInUseException;
import com.xaviervinicius.labschedule.exceptions.UnauthorizedException;
import com.xaviervinicius.labschedule.models.UserModel.AccountState;
import com.xaviervinicius.labschedule.models.UserModel.Role;
import com.xaviervinicius.labschedule.models.UserModel.UserModel;
import com.xaviervinicius.labschedule.repository.UserRepository.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public UserModel register(@NonNull CreateUserDto data, @Nullable String creatorToken){
        userRepository.findByEmail(data.email()).ifPresent(u -> {
            throw new EmailAlreadyInUseException("Email " + data.email() + " is already being used");
        });

        /* The algorithm won't proceed if the role is admin and there is not a creator,
         * or if the creator is not an admin
        */
        if(data.isAdminCreation()){
            if(creatorToken != null && !creatorToken.isEmpty()){
                String creatorEmail = jwtService.decode(creatorToken);
                if(userRepository.existsByEmailAndRole(creatorEmail, Role.ADMIN)){
                    log.info("Email: {} is trying to create a new admin", creatorEmail);
                }
            }
            throw new UnauthorizedException();
        }

        if(!data.samePasswords()){
            throw new RuntimeException("Password and confirm password doesn't match");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(data, userModel, "password");

        String encodedPassword = passwordEncoder.encode(data.password());

        userModel.setPassword(encodedPassword);
        userModel.setState(AccountState.REGISTERING);

        return userRepository.save(userModel);
    }
}
