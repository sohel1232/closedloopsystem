package com.card91.closedloopsystem.controller;

import com.card91.closedloopsystem.entity.AuthRequest;
import com.card91.closedloopsystem.service.OtpService;
import com.card91.closedloopsystem.service.UserDetailsService;
import com.card91.closedloopsystem.service.UserService;
import com.card91.closedloopsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "api/client/auth/")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OtpService otpService;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping({ "hello" })
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping(value = "requestOtp/{phoneNo}",method = RequestMethod.GET)
    public Map<String,Object> getOtp(@PathVariable String phoneNo){
        Map<String,Object> returnMap=new HashMap<>();
        try{
            System.out.println("inside try");
            //generate OTP
            String otp = otpService.generateOtp(phoneNo);
            System.out.println("inside try.otp: " + otp);
            returnMap.put("otp", otp);
            returnMap.put("status","success");
            returnMap.put("message","Otp sent successfully");
        }catch (Exception e){
            System.out.println("HELLLOOO " + e.getMessage());
            returnMap.put("status","failed");
            returnMap.put("message",e.getMessage());
        }
        return returnMap;
    }

    @RequestMapping(value = "verifyOtp/",method = RequestMethod.POST)
    public Map<String,Object> verifyOtp(@RequestBody AuthRequest authenticationRequest){
        Map<String,Object> returnMap=new HashMap<>();
        System.out.println("Inside verify otp");
        try{
            System.out.println("Inside verify otp try.");
            //verify otp
            if(authenticationRequest.getOtp().equals(otpService.getCacheOtp(authenticationRequest.getPhoneNo()))){
                System.out.println("befor going in with auth req " + authenticationRequest.getPhoneNo() + " " + authenticationRequest.getOtp());
                String jwtToken = createAuthenticationToken(authenticationRequest);
                System.out.println("after coming out");
                returnMap.put("status","success");
                returnMap.put("message","Otp verified successfully");
                returnMap.put("jwt",jwtToken);
                System.out.println("inside try : " + jwtToken);
                otpService.clearOtp(authenticationRequest.getPhoneNo());
            }else{
                System.out.println("Inside verify otp.else");
                returnMap.put("status","success");
                returnMap.put("message","Otp is either expired or incorrect");
            }

        } catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.getMessage());
        }

        return returnMap;
    }

    public String createAuthenticationToken(AuthRequest authenticationRequest) throws Exception {
        try {
            System.out.println("inside create auth token method with authreq " + authenticationRequest);
            String username = authenticationRequest.getPhoneNo();
            String password = "";
            System.out.println("Username is " + username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("authented");
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getPhoneNo());
        return jwtTokenUtil.generateToken(userDetails);
    }


}
