package de.neuefische.backend.user;

import java.util.List;

public record AppUser(
        String id,
        String role,
        String username,
        String avatarUrl,
        List<String> favoriteColors
) {

}
