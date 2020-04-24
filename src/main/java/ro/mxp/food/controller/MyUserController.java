package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.MyUserDto;
import ro.mxp.food.service.MyUserService;

import java.util.List;

@RestController
@RequestMapping("/myUser")
public class MyUserController {

    private MyUserService myUserService;
    @Autowired
    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping
    public List<MyUserDto> getAllMyUser() {
        return myUserService.getAllMyUser();
    }

    @PostMapping
    public void addMyUser(@RequestBody MyUserDto myUserDto) throws Exception {
        myUserService.addMyUser(myUserDto);
    }

    @PutMapping("/{id}")
    public void updateMyUser(@PathVariable Long id, @RequestParam String email, @RequestParam String username) {
        myUserService.updateMyUser(id, email, username);
    }

    @DeleteMapping("/{id}")
    public void deleteMyUser(@PathVariable Long id) {
        myUserService.deleteMyUser(id);
    }

}
