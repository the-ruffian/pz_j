/*
 * @Description:用户模块
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date:
 * @LastEditTime: 2021-07-05 21:25:12
 * @LastEditors: the-ruffian
 */
package com.example.demo.comtroller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.UserDeleteDto;
import com.example.demo.model.dto.UserLoginDto;
import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.model.dto.UserUpdateDto;
import com.example.demo.service.UserDeleteService;
import com.example.demo.service.UserLoginService;
import com.example.demo.service.UserRegisterService;
import com.example.demo.service.UserUpdateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.model.OpenResponse;



@RestController
@Api(tags = "用户模块")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserUpdateService userUpdateService;
    @Autowired
    private UserDeleteService userDeleteService;

    /*
    * 1.获取所有信息
    * */
    @ApiOperation(value = "获取所有用户信息")
    @PostMapping(value = "/api/user/list", produces = "application/json; charset=UTF-8")
    public Object select(@RequestBody JSONObject jsonParam){
        JSONObject search;
        search = jsonParam.getJSONObject("search");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Page<User> pageNo = new Page<>(jsonParam.getInteger("pageNo"), jsonParam.getInteger("pageSize"));
        IPage<User> userList =userDao.selectPage(pageNo,userQueryWrapper
                .select("phone","gender","username","email","login_time")
                .like((null!=search.get("username")&& ""!=search.get("username")),"username",search.get("username"))
                .like((null!=search.get("phone")&& ""!= search.get("phone")),"phone",search.getString("phone"))
                .eq((null!=search.get("gender")&& ""!=search.get("gender")),"gender",search.get("gender"))
                .like((null!=search.get("email")&&""!=search.get("email")),"email",search.get("email")));
        JSONObject obj = new JSONObject();
        obj.put("code",200);
        obj.put("result",userList);
        return obj.toJSONString();
    }


    /*
    * 2.注册接口
    * */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/api/user/register", produces = "application/json;charset=UTF-8")
    public OpenResponse create(@RequestBody UserRegisterDto userRegisterDto){
        return userRegisterService.registerUser(userRegisterDto);
    }

    /*
    *  修改用户数据
    * */
    @ApiOperation(value = "修改用户信息")
    @PutMapping(value = "/api/user/update", produces = "application/json;charset=UTF-8")
    public OpenResponse update(@RequestBody UserUpdateDto userUpdateDto) {
        return userUpdateService.update(userUpdateDto);
    }


    /*
    * 删除用户
    * */
    @ApiOperation(value = "删除账号")
    @DeleteMapping("/api/user/delete")
    public OpenResponse delete(@RequestBody UserDeleteDto userDeleteDto){
        return userDeleteService.delete(userDeleteDto);
    }

    @ApiOperation("用户登录")
    @PostMapping(value = "/api/user/login", produces = "application/json;charset=UTF-8")
    public OpenResponse login(@RequestBody UserLoginDto userLoginDto){

        return userLoginService.login(userLoginDto);
    }
}
