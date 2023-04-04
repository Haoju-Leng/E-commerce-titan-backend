package com.titans.ecommerce.auth;


import com.titans.ecommerce.config.JwtService;
import com.titans.ecommerce.models.entity.Role;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.models.vo.UserVO;
import com.titans.ecommerce.repository.UserRepository;
import com.titans.ecommerce.service.CartService;
import com.titans.ecommerce.utils.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


@Service
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  private final CartService cartService;

  private final AuthenticationManager authenticationManager;

  private final Jedis jedis = new Jedis("redis://localhost:6379");

  private final MailUtil mailUtil;

  public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, CartService cartService, AuthenticationManager authenticationManager, MailUtil mailUtil) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.cartService = cartService;
    this.authenticationManager = authenticationManager;
    this.mailUtil = mailUtil;
  }

  public UserVO register(RegisterRequest request) {
    if (repository.findByEmail(request.getEmail()).isPresent()) {
      throw new RuntimeException("Username already exists");
    }
    var user = User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
            .address(request.getAddress())
            .city(request.getCity())
            .state(request.getState())
            .country(request.getCountry())
            .zipcode(request.getZipcode())
        .build();
    User addedUser = repository.save(user);
    Integer id = addedUser.getId();
    cartService.create(id);
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

  public UserVO queryUser() {

    Integer userId = getUser().getId();
    User user = repository.findUserById(userId);

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

  public UserVO edit(RegisterRequest request) {
    Integer userId = getUser().getId();
    User user = repository.findUserById(userId);
    user.setState(request.getState());
    user.setCity(request.getCity());
    user.setAddress(request.getAddress());
    user.setZipcode(request.getZipcode());
    user.setCountry(request.getCountry());
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

  public void sendVerificationCode(String email) {
    String verificationCode = Integer.toString((int) (Math.random() * 900000 + 100000));
    try {
      mailUtil.sendCodeMail(email, verificationCode);
    } catch (Exception e) {
      e.printStackTrace();
    }
    jedis.set(email, verificationCode);
    jedis.expire(email, 300);
  }

  public boolean verifyVerificationCode(String email, String code) {
    if (null != code && !"".equals(code)) {
      return jedis.get(email).equals(code);
  }
    return false;
  }

  public UserVO setUserPassword(String email, String password) {
    User user = repository.findByEmail(email).orElse(null);
    if (null == user) {
      return null;
    } else{
      user.setPassword(passwordEncoder.encode(password));
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
  }

  private User getUser() {
    return (User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
  }
}
