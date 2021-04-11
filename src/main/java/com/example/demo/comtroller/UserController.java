package com.example.demo.comtroller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.JWTUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenRepository repositoryT;

    /*
    * 1.获取所有信息
    * */
    @ApiOperation(value = "获取所有用户信息")
    @GetMapping("/api/user")
    public Object getAll(){
        List<User> userList = repository.findAll();
        JSONObject obj = new JSONObject();
        obj.put("result", userList);
        obj.put("code", 200);
        return obj.toJSONString();
    }

    /*
    * 2.创建一条信息
    * */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/api/user/register", produces = "application/json;charset=UTF-8")
    public Object create(@RequestBody JSONObject jsonParam){
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        String email = jsonParam.getString("email");
        String phone = jsonParam.getString("phone");
        String sex = jsonParam.getString("sex");
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        Optional<User> userParam = repository.findByPhone(phone);
        JSONObject obj = new JSONObject();
        if (userParam.isPresent()){
            obj.put("code",400);
            obj.put("msg","该手机号已注册");
        } else {
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        repository.save(user);
        obj.put("msg", "尊敬的" + username + "用户，恭喜你注册成功");
        obj.put("code",200);
        }
        return obj.toJSONString();
    }

    /*
    * 3.通过手机号查用户
    * */
    @ApiOperation(value = "根据手机号获取用户")
    @GetMapping("/api/user/{phone}")
    public Object findByPhone(@PathVariable("phone")String phone){
        Optional<User> userList = repository.findByPhone(phone);
        User user;
        JSONObject obj = new JSONObject();
        if (userList.isPresent()){
            user = userList.get();
            obj.put("code",200);
            obj.put("result", user);
        }   else {
            obj.put("code", 400);
            obj.put("msg", "没有此用户");
        }
        return obj.toJSONString();
    }
    /*
    *  修改用户数据
    * */
    @ApiOperation(value = "修改用户信息")
    @ApiParam(value = "{test:test}")
    @PutMapping(value = "/api/user/{phone}", produces = "application/json;charset=UTF-8")
    public Object update(@PathVariable("phone") String phone,
                       @RequestBody JSONObject jsonParam){
        String username = jsonParam.getString("username");
        String sex = jsonParam.getString("sex");
        String password = jsonParam.getString("password");
        String email = jsonParam.getString("email");
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        Optional<User> userList= repository.findByPhone(phone);
        System.out.println(userList);
        JSONObject obj = new JSONObject();
        User user;
        if (userList.isPresent()){
           user = userList.get();
            user.setUsername(username);
            user.setPassword(md5Password);
            user.setSex(sex);
            user.setEmail(email);
            repository.save(user);
            obj.put("result",repository.save(user));
            obj.put("msg","资料修改成功");
            obj.put("code", 200);
        } else {
            obj.put("msg","修改失败");
            obj.put("code", 400);
        }
        return obj.toJSONString();
    }

    /*
    * 删除用户
    * */
    @ApiOperation(value = "删除账号")
    @DeleteMapping("/api/user/delete/{phone}")
    public Object delete(@PathVariable("phone")String phone){
        Optional<User> isUser = repository.findByPhone(phone);
        JSONObject obj = new JSONObject();
        if (isUser.isPresent()){
            obj.put("msg","删除成功");
            obj.put("code", 200);
            repository.deleteByPhone(phone);
        } else {
            obj.put("msg", "删除失败");
            obj.put("code", 400);
        }

        return obj.toJSONString();
    }

    @ApiOperation("用户登录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping(value = "/api/user/login", produces = "application/json;charset=UTF-8")
    public Object login(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam);
        String phone = jsonParam.getString("phone");
        String password = jsonParam.getString("password");
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(phone + password);
        Optional<User> userList = repository.findByPhone(phone);
        User user;
        JSONObject obj = new JSONObject();
        if (userList.isPresent()){
            user = userList.get();
            if (md5Password.equals(user.getPassword())){
                Map<String, String> payload = new HashMap<>();
                payload.put("phone", phone);
                payload.put("md5Password", md5Password);
                String token = JWTUtils.getToken(payload);
                Optional<Token> tokenList = repositoryT.findByPhone(phone);
                if (tokenList.isPresent()){
                    repositoryT.updateByPhone(token,phone);
                } else {
                    Token token1 = new Token();
                    token1.setPhone(phone);
                    token1.setToken(token);
                    repositoryT.save(token1);
                }
                obj.put("code", 200);
                obj.put("msg", "登录成功");
                obj.put("token", token);
                obj.put("username", user.getUsername());
            } else {
                obj.put("code", 201);
                obj.put("msg", "密码错误");
            }
        }else {
            obj.put("code",401);
            obj.put("msg","账号不存在");
        }
        return obj.toJSONString();
    }
}
