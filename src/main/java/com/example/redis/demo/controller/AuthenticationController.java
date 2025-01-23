package com.example.redis.demo.controller;

import com.example.redis.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Rest controller to authentication service apis
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Api to get OTP on provided mobile number
     *
     * @param mobileNumber the mobile number to send OTP
     * @return a successful message
     */
    @PostMapping(value = "/getOtp")
    public ResponseEntity<Map<String, String>> getOtp(@RequestParam String mobileNumber) {
        var getOtp = this.authenticationService.getOtp(mobileNumber);
        if (!getOtp) {
            return ResponseEntity.badRequest().body(Map.of("message", "Failed to send OTP"));
        }
        return ResponseEntity.ok().body(Map.of("message", "OTP sent successfully"));
    }

    /**
     * Api to authentication the mobile number and OTP.
     *
     * @param mobileNumber mobile number to authenticate
     * @param otp otp to verify
     * @return success or failure message
     */
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestParam String mobileNumber, @RequestParam String otp) {
        var authenticated = authenticationService.authenticate(mobileNumber, otp);

        if (authenticated) {
            return ResponseEntity.ok().body(Map.of("message", "Authentication successful"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid mobile number or OTP"));
        }
    }
}
