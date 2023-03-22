package com.titans.ecommerce.auth;


import com.titans.ecommerce.config.JwtService;
import com.titans.ecommerce.models.entity.Role;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.models.vo.UserVO;
import com.titans.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;



  public UserVO register(RegisterRequest request) {
    var user = User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return UserVO.builder()
            .token(jwtToken)
            .lastName(user.getLastName())
            .firstName(user.getFirstName())
            .email(user.getEmail())
            .address(user.getAddress())
            .city(user.getCity())
            .state(user.getState())
            .country(user.getCountry())
            .zipcode(user.getZipcode())
            .build();
  }

  public UserVO edit(RegisterRequest request) {
    Integer userId = getUser().getId();
    User user = repository.findUserById(userId);
    var editedUser = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();

    BeanUtils.copyProperties(editedUser, user);

    repository.save(user);

    return UserVO.builder()
            .lastName(user.getLastName())
            .firstName(user.getFirstName())
            .email(user.getEmail())
            .address(user.getAddress())
            .city(user.getCity())
            .state(user.getState())
            .country(user.getCountry())
            .zipcode(user.getZipcode())
            .build();
  }

  public UserVO authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return UserVO.builder()
            .token(jwtToken)
            .lastName(user.getLastName())
            .firstName(user.getFirstName())
            .email(user.getEmail())
            .address(user.getAddress())
            .city(user.getCity())
            .state(user.getState())
            .country(user.getCountry())
            .zipcode(user.getZipcode())
            .build();
  }

  private User getUser() {
    return (User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
  }
}
