//package com.example.Event_Management_System.Config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenProvider tokenProvider;
//    private final CustomUserDetailsService userDetailsService;
//
////    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider,
////                                   CustomUserDetailsService userDetailsService) {
////        this.tokenProvider = tokenProvider;
////        this.userDetailsService = userDetailsService;
////    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        try {
//            String jwt = getJwtFromRequest(request);
//
//            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//
//                Long userId = tokenProvider.getUserIdFromJWT(jwt);
//
//                var userDetails = userDetailsService.loadUserById(userId);
//
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities()
//                        );
//
//                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        } catch (Exception ignored) {}
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//
//        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }
//}
