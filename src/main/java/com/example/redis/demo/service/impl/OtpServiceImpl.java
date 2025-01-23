package com.example.redis.demo.service.impl;

import com.example.redis.demo.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link OtpService} interface
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateOtp() {
        double randomOtp = Math.random() * 100000;
        log.info("Generated OTP {}", randomOtp);
        return String.valueOf(Math.round(randomOtp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendOtp(String mobileNumber, String OTP, long ttl) {
        // Store the OTP in Redis with a TTL of 300 seconds
        redisTemplate.opsForValue().set(mobileNumber,OTP, 300, TimeUnit.SECONDS);
        log.info("Sent OTP {} to {}", OTP, mobileNumber);

        // TODO: Write your own logic to send the OTP to mobile number...
        // Example:
        // Send SMS using a third-party service, whatsapp services or using an email service
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyOtp(String mobileNumber, String OTP) {
        String storedOtp = (String) redisTemplate.opsForValue().get(mobileNumber);
        log.info("Received OTP {} from {}", OTP, mobileNumber);

        // Check if the received OTP matches the stored one and delete the OTP from Redis if it matches
        if (storedOtp!= null && storedOtp.equals(OTP)) {
            redisTemplate.delete(mobileNumber);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOtpPresent(String mobileNumber) {
        return redisTemplate.hasKey(mobileNumber);
    }
}
