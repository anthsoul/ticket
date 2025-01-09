package org.example.application.service;

import lombok.AllArgsConstructor;
import org.example.application.common.repository.RedisRepository;
import org.example.application.user.exception.InternalServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class OTPService {
    private final RedisRepository redisRepository;
    private static  final Logger logger= LoggerFactory.getLogger(OTPService.class);

    private int genOtp(String email) {
        try {
            String otpKey = genKeyOtpRegister(email);
            int value = genOtpCode();
            redisRepository.setWithTimeExpire(otpKey, String.valueOf(value), 60);
            logger.info("Otp {} with email created {}", value, email);
            return value;
        } catch (Exception e) {
            logger.error("Error to save otp with email {} to redis", email);
            throw new InternalServerError("Error to save otp");
        }
    }
    private boolean verifyOtp(String email, int otp) {
        String otpKey =  genKeyOtpRegister(email);
        try {
            String otpCode = redisRepository.get(otpKey);
            if (otpCode == null || Integer.parseInt(otpCode) != otp) {
                return  false;
            } else {
                return  true;
            }
        } catch (Exception e) {
            logger.error("Error to get key {}", email);
            throw new InternalServerError("Error to get otp");
        }
    }
    private int genOtpCode() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i=0 ; i< 6; i++) {
            otp.append(random.nextInt(10));
        }
        return  Integer.parseInt(otp.toString());
    }

    private String  genKeyOtpRegister(String email) {
        return  "opt_" + email;
    }
}
