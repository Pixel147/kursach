package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.config.jwt.JwtFilter;
import ua.com.online.appointment.config.jwt.JwtProvider;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.repository.UserRepository;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Service
public class JwtService {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private UserRepository userRepository;
    public User getUserByToken(ServletRequest servletRequest){
        String token = jwtFilter.getTokenFromRequest((HttpServletRequest) servletRequest);
        if(token != null && jwtProvider.validateToken(token)){
            String username = jwtProvider.getLoginFromToken(token);
            User user = userRepository.findByUsername(username);
            return user;
        }
        return null;
    }
}
