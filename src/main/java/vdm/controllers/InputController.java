package vdm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vdm.entity.User;
import vdm.repository.UserRepository;
import vdm.security.SecurityUser;

@Controller
public class InputController {

    private UserRepository userRepository;

    @Autowired
    InputController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = {"/","/login/ok"})
    public String getPage(Authentication authentication){
        SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
        int userId = securityUser.getUserId();
        User user = userRepository.findOne(userId);
        switch (user.getRole()){
            case ROLE_ADMIN: return "admin";
            case ROLE_MANAGER: return "manager";
            case ROLE_DEVELOPER: return "developer";
            default: return "registration";
        }
    }
}
