package com.example.demo.comtroller;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    /*
    *  修改用户数据
    * */
    @PutMapping("user/{id}")
    public User update(@PathVariable(value = "id")Integer id,
                       @RequestParam(value = "password", required = false)String password,
                       @RequestParam(value = "sex" ,required = false)String sex,
                       @RequestParam(value = "email", required = false)String email,
                       @RequestParam(value = "username",required = false)String username){
        Optional<User> optional = repository.findById(id);
        User user;
        if(optional.isPresent()){
            user = optional.get();
        }else {
            return null;
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setEmail(email);
        return repository.save(user);
    }

    /*
    * 删除用户
    * */
    @DeleteMapping("/user/{id}")
    public User delete(@PathVariable("id")Integer id){
        repository.delete(findById(id));
        return null;
    }
}
