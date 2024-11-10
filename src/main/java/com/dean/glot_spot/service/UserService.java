package com.dean.glot_spot.service;

import com.dean.glot_spot.model.User;
import com.dean.glot_spot.model.User.AccountStatus;
import com.dean.glot_spot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    // Register a new user and send a magic link
    public User registerUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setAccountStatus(AccountStatus.INACTIVE); // Set initial status to INACTIVE
        user.setMagicLinkToken(generateMagicLinkToken());
        user.setMagicLinkExpiry(LocalDateTime.now().plusMinutes(15)); // Token expires in 15 minutes
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        sendMagicLink(user);

        return user;
    }

    // Validate the magic link token
    public boolean validateMagicLinkToken(String token) {
        Optional<User> userOptional = userRepository.findByMagicLinkToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getMagicLinkExpiry().isAfter(LocalDateTime.now())) {
                // Token is valid, update user's status if necessary
                user.setAccountStatus(AccountStatus.ACTIVE);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    // Enable 2FA for the user
    public void enableTwoFA(User user) {
        String secretKey = UUID.randomUUID().toString(); // Placeholder for TOTP secret generation
        user.setTwofaSecretKey(secretKey);
        user.setIs2faEnabled(true);
        userRepository.save(user);

        // Here, you would typically send instructions for setting up 2FA (e.g., a QR code)
    }

    // Send a magic link email
    private void sendMagicLink(User user) {
        String magicLinkUrl = "https://yourapp.com/login?token=" + user.getMagicLinkToken();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@hidemy.pics"); // Set a valid email address here
        message.setTo(user.getEmail());
        message.setSubject("Your Magic Link for Login");
        message.setText("Click the following link to log in: " + magicLinkUrl);

        mailSender.send(message); // Send the email
        System.out.println("Magic link sent to " + user.getEmail() + ": " + magicLinkUrl); // For logging
    }

    // Generate a unique magic link token
    private String generateMagicLinkToken() {
        return UUID.randomUUID().toString();
    }
}
