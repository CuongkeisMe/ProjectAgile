package com.example.projectagile.filter;

import com.example.projectagile.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DoFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (isBypassToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User userDetails = (User) userDetailsService.loadUserByUsername(username);
            // Kiểm tra mật khẩu (so sánh mật khẩu đã mã hóa)
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
                return;
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        // Không bypass bất kỳ request nào
        return false;
    }
}
