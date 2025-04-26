package by.mikhalevich.grandcapitaltesttask.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Alex Mikhalevich
 * @created 2025-04-26 14:01
 */
@Component
public class AuthService {

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
