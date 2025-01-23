package com.example.redis.demo.service;

public interface OtpService {

    /**
     * Generates a random string OTP
     * @return a generated random OTP
     */
    String generateOtp();

    /**
     * Sends OTP to provided mobile number
     *
     * @param mobileNumber Mobile number to send OTP
     * @param OTP OTP to send
     * @param ttl time to live in seconds
     */
    void sendOtp(String mobileNumber, String OTP, long ttl);

    /**
     * Verifies the OTP sent to provided mobile number
     *
     * @param mobileNumber Mobile number to verify OTP
     * @param OTP OTP to verify
     * @return true if the OTP is verified
     */
    boolean verifyOtp(String mobileNumber, String OTP);

    /**
     * Checks if OTP is present for provided mobile number
     *
     * @param mobileNumber Mobile number to check OTP
     * @return true if OTP is present
     */
    boolean isOtpPresent(String mobileNumber);

}
