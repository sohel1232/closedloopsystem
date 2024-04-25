package com.card91.closedloopsystem.util;

import com.card91.closedloopsystem.service.UserDetailsService;
import com.card91.closedloopsystem.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("authorizationHeader" + authorizationHeader);

        System.out.println("inside jwt");
        String jwt = null, username = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("Inside the auth hewad null");
            jwt = authorizationHeader.substring(7);
            System.out.println("jwt : " + jwt);
            username = jwtUtil.extractUsername(jwt);
            System.out.println("username" + username);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Inside the else");
            UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
            System.out.println("User details are as follow : " + userDetails);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
