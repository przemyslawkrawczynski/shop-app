package com.tt.shop.controller;

import com.tt.shop.exception.JwtValidateException;
import com.tt.shop.security.JwtTokenDto;
import com.tt.shop.security.UserAuthenticationDto;
import com.tt.shop.security.UserSecurityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/shop")
@CrossOrigin(origins = "*")
public class JwtUserController {

    private final UserSecurityService userSecurityService;

    public JwtUserController(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @PostMapping("/signin")
    public JwtTokenDto login(@RequestBody UserAuthenticationDto userAuthenticationDto) throws JwtValidateException {
        return userSecurityService.signin(userAuthenticationDto.getUsername(), userAuthenticationDto.getPassword());
    }
}
