/*
 * @Description:用户模块
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date:
 * @LastEditTime: 2021-7-13 18:09:30
 * @LastEditors: the-ruffian
 */
package com.example.demo.comtroller;


import com.example.demo.model.dto.*;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.model.OpenResponse;



@RestController
@Api(tags = "用户模块")
public class UserController {
    @Autowired
    private UserService userService;


    /*
    * 2.注册接口
    * */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/api/user/register", produces = "application/json;charset=UTF-8")
    public OpenResponse create(@RequestBody UserRegisterDto userRegisterDto){
        return userService.registerUser(userRegisterDto);
    }

    /*
    *  修改用户数据
    * */
    @ApiOperation(value = "修改用户信息")
    @PutMapping(value = "/api/user/update", produces = "application/json;charset=UTF-8")
    public OpenResponse update(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(userUpdateDto);
    }


    /*
    * 删除用户
    * */
    @ApiOperation(value = "删除账号")
    @DeleteMapping("/api/user/delete")
    public OpenResponse delete(@RequestBody UserDeleteDto userDeleteDto){
        return userService.delete(userDeleteDto);
    }

    /*
    *用户登录
    * */
    @ApiOperation("用户登录")
    @PostMapping(value = "/api/user/login", produces = "application/json;charset=UTF-8")
    public OpenResponse login(@RequestBody UserLoginDto userLoginDto){

        return userService.login(userLoginDto);
    }

    /*
    * 用户信息
    * */
    @PostMapping(value = "/api/user/list",produces = "application/json;charset=UTF-8")
    public OpenResponse search(@RequestBody UserListDto userListDto){
        return userService.search(userListDto);
    }
}
