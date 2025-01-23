package com.example.redis.demo.service;

public interface AuthenticationService {

    /**
     * Get OTP on provided mobile number
     * (This is demo that why not implemented to send otp on mobile number)
     *
     * @param mobileNumber The mobile number to receive OTP (Saving OTP in redis (key:value)
     * @return true if otp sent successfully
     */
    boolean getOtp(String mobileNumber);

    /**
     * Authenticate the mobile number and otp.
     *
     * @param mobileNumber mobile number received otp
     * @param otp otp to verify
     * @return true if otp verification successfully
     */
    boolean authenticate(String mobileNumber, String otp);
}
