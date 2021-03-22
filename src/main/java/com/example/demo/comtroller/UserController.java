package com.example.demo.comtroller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserRepository repository;

    /*
    * 1.获取所有信息
    * */
    @ApiOperation(value = "获取所有用户信息")
    @GetMapping("/api/user")
    public List<User> getAll(){
        return repository.findAll();
    }

    /*
    * 2.创建一条信息
    * */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/api/register", produces = "application/json;charset=UTF-8")
    public User create(@RequestBody JSONObject jsonParam){
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        String email = jsonParam.getString("email");
        String phone = jsonParam.getString("phone");
        String sex = jsonParam.getString("sex");

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
    @ApiOperation(value = "根据id获取用户")
    @GetMapping("/api/user/{id}")
    public User findById(@PathVariable("id")Integer id){

        return repository.findById(id).orElse(null);
    }
    /*
    *  修改用户数据
    * */
    @ApiOperation(value = "修改用户信息")
    @PutMapping(value = "/api/user/{phone}", produces = "application/json;charset=UTF-8")
    public User update(@PathVariable("phone") String phone,
                       @RequestBody JSONObject jsonParam){
        String username = jsonParam.getString("username");
        String sex = jsonParam.getString("sex");
        String password = jsonParam.getString("password");
        String email = jsonParam.getString("email");
        Optional<User> userList= repository.findByPhone(phone);
        System.out.println(userList);

        User user;
        if (userList.isPresent()){
           user = userList.get();
        } else {
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
    @ApiOperation(value = "删除账号")
    @DeleteMapping("/api/user/{id}")
    public User delete(@PathVariable("id")Integer id){
        repository.delete(findById(id));
        return null;
    }

    @ApiOperation("用户登录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping(value = "/api/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam);
        String phone = jsonParam.getString("phone");
        String password = jsonParam.getString("password");
        System.out.println(phone + password);
        Optional<User> userList = repository.findByPhone(phone);
        User user;
        JSONObject obj = new JSONObject();
        if (userList.isPresent()){
            user = userList.get();
            if (password.equals(user.getPassword())){
                obj.put("code", 200);
                obj.put("msg","登录成功");
            } else {
                obj.put("code", 200);
                obj.put("msg", "密码错误");
            }
        }else {
            obj.put("code",200);
            obj.put("msg","账号不存在");
        }
        return obj.toJSONString();
    }
}
