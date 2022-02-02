package com.exemple.api.config.security.filter;

import com.exemple.api.entity.User;
import com.exemple.api.common.helper.JWTHelper;
import com.exemple.api.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTHelper jwtHelper;
    private final UserService userService;

    public JWTFilter(JWTHelper jwtHelper, UserService userService) {
        this.jwtHelper = jwtHelper;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String jwt = getTokenDetail(header);

        if (this.jwtHelper.isValid(jwt)) {
            setAuthentication(jwt);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String token) {
        Long subject = this.jwtHelper.getSubject(token);
        User user = this.userService.findById(subject);

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(upat);
    }

    private String getTokenDetail(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        } else {
            return token.substring(7);
        }
    }
}
