package com.example.CostControl.Controller;

import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.CreateAuthTokenException;
import com.example.CostControl.Service.UserService;
import com.example.CostControl.Util.JwtTokenUtils;
import com.example.CostControl.dto.JwtRequest;
import com.example.CostControl.dto.JwtResponse;
import com.example.CostControl.dto.RegistrationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authrequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new CreateAuthTokenException(HttpStatus.BAD_REQUEST.value(),"Incorrect login or password"),HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = userService.loadUserByUsername(authrequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));


    }


    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        if (userService.findByUsername(registrationUserDto.getName()).isPresent()){
            return ResponseEntity.ok("User  already exists");
        }else {
            User user = userService.createNewUser(registrationUserDto);
            return ResponseEntity.ok(user);
        }
    }

}
