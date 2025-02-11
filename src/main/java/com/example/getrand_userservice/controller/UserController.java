package com.example.getrand_userservice.controller;

import com.example.getrand_userservice.dto.UserRequestDTO;
import com.example.getrand_userservice.dto.UserResponseDTO;
import com.example.getrand_userservice.serivce.UserService;
import jakarta.ws.rs.core.Request;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("userId") String userid,
            @RequestParam("password") String password) {

        userService.registerUser(new UserRequestDTO(userid, password));
        // 요청 처리 로직
        return "회원가입이 성공적으로 완료되었습니다";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password) {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUserId(userId);
        requestDTO.setPassword(password);

        try {
            UserResponseDTO response = userService.loginUser(requestDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생: " + e.getMessage());
        }
    }

}