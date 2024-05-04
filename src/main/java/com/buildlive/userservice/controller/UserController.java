package com.buildlive.userservice.controller;

import com.buildlive.userservice.dto.OtpDto;
import com.buildlive.userservice.dto.VerifyDto;
import com.buildlive.userservice.entity.UserCredential;
import com.buildlive.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save-user")

    public ResponseEntity<UserCredential> registerUser(@RequestBody OtpDto otpDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(otpDto));
    }



    @PostMapping ("/verify-user")
    public ResponseEntity<UserCredential> verifyUser(@RequestBody VerifyDto request){
       return ResponseEntity.status(HttpStatus.OK)
               .body(userService.verify(request));
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity <UserCredential> editUser(@PathVariable UUID id,
                                                    @RequestBody  UserCredential userCredential
    ){
        System.out.println("id"+id);
        return ResponseEntity.ok(userService.editUser(id,userCredential));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserCredential>> searchUsersByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.findUsersByEmail(email));
    }

    @GetMapping("/test")
    public String testDep(){
        return "hellooo";
    }



}
