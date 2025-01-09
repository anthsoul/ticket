package org.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.entity.enums.UserRole;
import org.example.domain.exception.InvalidArgumentException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private long id;
    private String name;
    private String image;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private String phoneNumber;

    public User(String name, String image, String username, String email, String password, String role, String phoneNumber) {
        if (name == null || name.isBlank()) {
            throw new InvalidArgumentException("Name must not null");
        }
        if (!isValidEmail(email)) {
            throw new InvalidArgumentException("Email is not valid");
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new InvalidArgumentException("Phone number is not valid");
        }
        if (!isValidPassword(password)) {
            throw new InvalidArgumentException("Password must contain lower, upper, special character and number");
        }
        if (!isValidUserRole(role)) {
            throw new InvalidArgumentException("Role is not valid");
        }
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        UserRole userRole = UserRole.valueOf(role);
        this.name = name;
        this.image = image;
        this.username = username;
        this.email = email;
        this.password = hashPassword;
        this.role = userRole;
        this.phoneNumber = phoneNumber;
    }

    //change password
    public void changePassword(String oldPassword, String newPassword) {
        if (!BCrypt.checkpw(oldPassword, password)) {
            throw new InvalidArgumentException("Password is wrong!");
        }
        if (!isValidPassword(newPassword)) {
            throw new InvalidArgumentException("Password must contain lower, upper, special character and number");
        }
        this.password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
    }

    //compare password
    public boolean comparePassword(String inputPassword) {
        return BCrypt.checkpw(inputPassword, this.password);
    }

    //update profile
    public void updateProfile(String name, String email, String phoneNumber) {
        if (name == null || name.isBlank()) {
            throw new InvalidArgumentException("Name must not null");
        }
        if (!isValidEmail(email)) {
            throw new InvalidArgumentException("Email is not valid");
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new InvalidArgumentException("Phone number is not valid");
        }
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //update avatar
    public void updateAvatar(String url) {
        if (url == null || url.isBlank()) {
            throw new InvalidArgumentException("Avatar is null or empty");
        }
        this.image = url;
    }

    //check valid email
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // check valid phone number
    public boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\+?[0-9]{10,15}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    //valid password
    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //valid role
    private boolean isValidUserRole(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }
}
