package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.service.UserDisplayService;

@RestController
public class UserDisplayController {

    private final UserDisplayService userDisplayService;
    @Autowired
    public UserDisplayController(UserDisplayService userDisplayService) {
        this.userDisplayService = userDisplayService;
    }

    @GetMapping("/display")
    public MyUser currentUserName() {
        return userDisplayService.currentUserName();
    }

}
