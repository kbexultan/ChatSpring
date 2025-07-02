package com.example.demo.authentification;

import com.example.demo.config.JwtService;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.Map;
import org.springframework.stereotype.Service;
import java.util.HashMap;
@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegisterRequest request) {//метод регистрации
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(Role.USER)
                .active(true)
                .build();

        userService.createUser(user);


        String token = jwtService.generateToken(new HashMap<>(), user);

        return AuthentificationResponse.builder()
                .token(token)
                .build();
    }

    public AuthentificationResponse authenticate(AuthentificationRequest request) {//метод аутенфикации
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );


        var userDto = userService.findByUsername(request.getUsername());


        User user = User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(Role.USER)
                .active(true)
                .build();


        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());

        String token = jwtService.generateToken(claims, user);

        return AuthentificationResponse.builder()
                .token(token)
                .build();
    }
}