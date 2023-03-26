package com.titans.ecommerce.auth;

import com.titans.ecommerce.models.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

  @GetMapping("/profile")
  public ResponseEntity<UserVO> queryProfile() {
    return ResponseEntity.ok(service.queryUser());
  }

  @PutMapping("/profile")
  public ResponseEntity<UserVO> editProfile(
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

  @GetMapping("/verification")
  public ResponseEntity<String> verify(@RequestParam("email") String email) {
    service.sendVerificationCode(email);
    return ResponseEntity.ok("success");
  }

  @PostMapping("/password")
  public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> input) {
    String email = input.get("email");
    String code = input.get("code");
    boolean result = service.verifyVerificationCode(email, code);
    if (result) {
      service.setUserPassword(email, input.get("password"));
      return ResponseEntity.ok("success");
    } else {
      return ResponseEntity.ok("code is not correct");
    }
  }
}
