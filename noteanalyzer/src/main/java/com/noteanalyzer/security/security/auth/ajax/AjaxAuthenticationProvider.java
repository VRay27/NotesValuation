package com.noteanalyzer.security.security.auth.ajax;

import static com.noteanalyzer.mvc.constant.NoteConstant.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.impl.UserServiceImpl;
import com.noteanalyzer.security.security.exceptions.UnverifiedUserException;
import com.noteanalyzer.security.security.model.UserContext;


@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder encoder;
    private final UserServiceImpl userService;

    @Autowired
    public AjaxAuthenticationProvider(final UserServiceImpl userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        System.out.println("Arvind "+username+", password "+password);

        UserModel user = userService.getByUsernameWithPassword(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        if(user.getIsActive() == null || IN_ACTIVE_USER_FLAG.equalsIgnoreCase(user.getIsActive())){
        	throw new UnverifiedUserException(UNVERIFIED_USER_CODE_UI);
        }else if(BLOCK_USER_FLAG.equalsIgnoreCase(user.getIsActive())){
        	throw new UnverifiedUserException(BLOCKED_USER_CODE_UI);
        }
        if (!encoder.matches(password, user.getPassword())) {
        	userService.updateUnsuccessfullAttempt(LOGIN_FAIL,user.getUserId(),user.getEmail());
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }else{
        	userService.updateUnsuccessfullAttempt(LOGIN_SUCCESS,user.getUserId(),user.getEmail());
        }

/*        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
*/        
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        UserContext userContext = UserContext.create(user.getEmail(), authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    
}
