package com.codenames.filter;

import com.codenames.provider.UserAuthProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null){
            String[] elements = header.split(" ");

            if (elements.length == 2 && "Bearer".equals(elements[0])){
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                        userAuthProvider.validateToken(elements[1])
                    );

                } catch (RuntimeException e){
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
