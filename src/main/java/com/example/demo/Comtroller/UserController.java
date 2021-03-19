package com.example.demo.comtroller;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserRepository repository;

    /*
    * 1.获取所有信息
    * */
    @GetMapping("/user")
    public List<User> getAll(){
        return repository.findAll();
    }

    /*
    * 2.创建一条信息
    * */
    @PostMapping("/user")
    public User create(@RequestParam("username")String username,
                       @RequestParam("password")String password,
                       @RequestParam("phone")String phone,
                       @RequestParam("sex")String sex,
                       @RequestParam("email")String email){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        return repository.save(user);
    }

    /*
    * 3.通过id查用户
    * */
    @GetMapping("user/{id}")
    public User findById(@PathVariable("id")Integer id){
        return repository.findById(id).orElse(null);
    }
}
