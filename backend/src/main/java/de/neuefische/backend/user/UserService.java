package de.neuefische.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AppUser findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    public AppUser register(OAuth2User oAuth2User) {
        return userRepository.save(new AppUser(oAuth2User.getName(),
                "User",
                oAuth2User.getAttributes().get("login").toString(),
                oAuth2User.getAttributes().get("avatar_url").toString(),
                Collections.emptyList()));
    }
}
