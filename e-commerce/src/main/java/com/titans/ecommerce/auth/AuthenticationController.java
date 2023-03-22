package com.titans.ecommerce.auth;

import com.titans.ecommerce.models.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<UserVO> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PutMapping
  public ResponseEntity<UserVO> edit(
          @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.edit(request));
  }

  @PostMapping("/login")
  public ResponseEntity<UserVO> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
