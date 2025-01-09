package org.example.application.user.service;

import lombok.AllArgsConstructor;
import org.example.application.client.FileCloudStorage;
import org.example.application.service.OTPService;
import org.example.application.user.dto.*;
import org.example.application.user.exception.ResourceNotFoundException;
import org.example.application.user.exception.InternalServerError;
import org.example.application.common.repository.RedisRepository;
import org.example.application.user.repository.UserRepository;
import org.example.domain.entity.User;
import org.example.domain.exception.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
//    @Value("${cloud.aws.avatar}")
    private static final String avatarBucket = "avatar";

    private final UserRepository userRepository;
    private final RedisRepository redisRepository;
    private final FileCloudStorage fileCloudStorage;
    private final RenameFileService renameFileService;
    private final JwtService jwtService;
    private final OTPService otpService;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final static long TIME_REDIS_TOKEN_STORE = 60 * 60;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findUserByUsername(loginRequestDto.getUsername());
        if (user.isEmpty()) {
            logger.error("Username {} not found",loginRequestDto.getUsername());
            throw new ResourceNotFoundException("Username not found");
        }
        boolean checkPassword = user.get().comparePassword(loginRequestDto.getPassword());
        if (checkPassword) {
            String token = jwtService.generateToken(user.get().getId());
            return LoginResponseDto.builder()
                    .userId(user.get().getId())
                    .name(user.get().getName())
                    .avatar(user.get().getImage())
                    .token(token)
                    .build();
        } else {
            throw new InvalidArgumentException("Password is wrong");
        }
    }

    public RegisterResponseDto register(RegisterRequestDTO requestDTO) {
        Optional<User> user = userRepository.findUserByUsername(requestDTO.getUsername());
        if (user.isPresent()) {
            logger.error("Username {} is existed", requestDTO.getUsername());
            throw  new InvalidArgumentException("User existed");
        }
        //create user
        User userSave = new User(requestDTO.getName(), "",requestDTO.getUsername() ,requestDTO.getEmail()
                ,requestDTO.getPassword() ,requestDTO.getRole() ,requestDTO.getPhoneNumber());

        //save user to db
        User userResponse = userRepository.saveUser(userSave);

        //handle upload file
        String fileName = renameFileService.renameAvatarFile(userResponse.getId(), ".jpg");
        fileCloudStorage.uploadFile(avatarBucket, fileName,requestDTO.getFile());
        String url = fileCloudStorage.getFileUrl(avatarBucket, fileName);

        //change url
        userResponse.updateAvatar(url);
        userRepository.saveUser(userResponse);
        logger.info("Save user success, id {} , name {}", userResponse.getId(), userResponse.getName());
        return RegisterResponseDto.builder()
                .id(userResponse.getId())
                .build();
    }

    public void logout(LogoutRequest logoutRequest) {
        try {
            String redisKey = String.valueOf(logoutRequest.getUserId());
            String result = redisRepository.hashGet(redisKey, "token");
            if (result == null || result.isBlank()) {
                // Lưu token vào Redis
                redisRepository.hashSet(redisKey, "token", logoutRequest.getToken());
                redisRepository.setTimeToLive(redisKey, TIME_REDIS_TOKEN_STORE);
            }
        } catch (Exception e) {
            logger.error("Error occurred while processing logout for userID {}: {}", logoutRequest.getUserId(), e.getMessage(), e);
            throw new InternalServerError("Failed to process logout request");
        }
    }

}
