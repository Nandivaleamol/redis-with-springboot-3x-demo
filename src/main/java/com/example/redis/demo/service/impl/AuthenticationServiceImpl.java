package com.example.redis.demo.service.impl;

import com.example.redis.demo.service.AuthenticationService;
import com.example.redis.demo.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final OtpService otpService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getOtp(String mobileNumber) {
        try {
            var generatedOtp = otpService.generateOtp();
            log.info("Generated OTP: {}", generatedOtp);

            // Send to provided mobile number and invalid after 5 minutes
            otpService.sendOtp(mobileNumber, generatedOtp, 300);
            return true;
        }catch (Exception e) {
            log.error("Error while generating OTP: {}", e.getMessage());
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean authenticate(String mobileNumber, String otp) {
        if (mobileNumber==null || otp == null) {
            log.error("Mobile number or OTP is null");
            throw new IllegalArgumentException("Mobile number or OTP is cannot be null");
        }

        log.info("Attempting OTP authentication for mobile number: {}", mobileNumber);
        var verified = otpService.verifyOtp(mobileNumber, otp);

        // Check otp is verified or not
        if (verified) {
            log.info("OTP authentication successful for mobile number: {}", mobileNumber);
            return true;
        } else {
            log.info("Invalid OTP for mobile number: {}", mobileNumber);
            return false;
        }
    }
}
