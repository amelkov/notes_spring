package by.amelkov.controller;

import by.amelkov.model.User;
import by.amelkov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/sign**", "/"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("sign");
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/notes");
        }
        return model;
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView model = new ModelAndView();
        model.setViewName("registration");
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/notes");
        }
        return model;
    }

    @Secured("isAnonymous()")
    @RequestMapping(value = {"registrationUser"}, method = RequestMethod.POST)
    public @ResponseBody String registerUser(@RequestBody Object object) {
        User user = new User((String)((Map)object).get("username"),(String)((Map)object).get("password"));
        String confirmPassword = (String)((Map)object).get("confirmPassword");
        if (user.getPassword() == null || user.getPassword().isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || user.getUsername() == null || user.getUsername().isEmpty()) {
            return "All fields are required!";
        }
        if(!isAlphanumeric(user.getUsername()) || !isAlphanumeric(user.getPassword()) || ! isAlphanumeric(confirmPassword)){
            return "Login and password must contains only letters or numbers!";
        }
        String password = user.getPassword();
        if(userService.getUser(user.getUsername())!=null){
            return "This user already exist!";
        }else{
            if(!user.getPassword().equals(confirmPassword)){
                return "Passwords aren't match!";
            }
            user.setPassword(passwordEncoder.encodePassword(password,null));
            userService.add(user);
        }
        try {
            UserDetails userDetails = userService.getUser(user.getUsername());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(auth);
            if(auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "You have registered successfully, but automatic authorization is failed.Please log in manually.";
    }

}