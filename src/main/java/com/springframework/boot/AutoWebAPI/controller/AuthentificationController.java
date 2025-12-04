package com.springframework.boot.AutoWebAPI.controller;


import com.springframework.boot.AutoWebAPI.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthenticationService service;
    private final String nameRegex = "^[a-zA-Z]+(?:[\\s'-][a-zA-Z]+)*$";
    private static final String[] domainNames = {".com", ".ro"};

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request
    ) {
        Pattern pattern = Pattern.compile(nameRegex);
        String email = request.getEmail();
        String firstName = request.getFirstname();
        String lastName = request.getLastname();

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);

        if (!validateEmail(email))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        boolean bool = pattern.matcher(firstName).matches() && pattern.matcher(lastName).matches();

        if (!bool){
            System.out.println("Name must only be characters");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    private static boolean validateEmail(String email){
        if (!email.contains("@")) {
            System.out.println("nu are @");
            return false;
        }
        if(email.length() < 10)
        {
            System.out.println("lungimea e de vina");
            return false;
        }

        int ok = 0;
        for (String v : domainNames){
            if (email.endsWith(v))
                ok = 1;
        }
        if (ok == 0){
            System.out.println("EMAIL DOES NOT END IN DOMAIN NAME!");
            return false;
        }
        return true;
    }

}
