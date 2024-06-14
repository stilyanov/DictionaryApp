package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        boolean isUsernameOrEmailTaken = userRepository
                .existsByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());

        if (isUsernameOrEmailTaken) {
            return false;
        }

        User map = modelMapper.map(userRegisterDTO, User.class);
        map.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(map);

        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = userRepository.findByUsername(userLoginDTO.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        User user = byUsername.get();

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return false;
        }

        userSession.login(user);

        return true;
    }

    public void logout() {
        userSession.logout();
    }
}
