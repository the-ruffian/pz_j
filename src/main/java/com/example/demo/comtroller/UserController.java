/*
 * @Description:用户模块
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date:
 * @LastEditTime: 2021-08-15 19:27:22
 * @LastEditors: the-ruffian
 */
package com.example.demo.comtroller;


import com.example.demo.model.dto.*;
import com.example.demo.service.MailService;
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
    @Autowired
    private MailService mailService;

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


    /**
     * @Description 删除
     * @param userDeleteDto
     * @return
     */
    @ApiOperation(value = "删除账号")
    @DeleteMapping("/api/user/delete")
    public OpenResponse delete(@RequestBody UserDeleteDto userDeleteDto){
        return userService.delete(userDeleteDto);
    }

    /**
     * @Description 登录
     * @param userLoginDto
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping(value = "/api/user/login", produces = "application/json;charset=UTF-8")
    public OpenResponse login(@RequestBody UserLoginDto userLoginDto){

        return userService.login(userLoginDto);
    }

    /**
     * @Description 查询用户
     * @param userListDto
     * @return
     */
    @PostMapping(value = "/api/user/list",produces = "application/json;charset=UTF-8")
    public OpenResponse search(@RequestBody UserListDto userListDto){
        return userService.search(userListDto);
    }

    /**
     * @Description 忘记密码
     * @param userResetPasswordDto
     * @return
     */
    @PostMapping(value = "/api/forgetPassword",produces = "application/json;charset=UTF-8")
    public OpenResponse forget(@RequestBody UserResetPasswordDto userResetPasswordDto){
        return userService.forgetPassword(userResetPasswordDto);
    }

    /**
     *
     * @Description 获取验证码
     * @param toEmailDto
     * @return 验证码
     */
    @PostMapping(value = "/api/getCode",produces = "application/json;charset=UTF-8")
    public OpenResponse code(@RequestBody ToEmailDto toEmailDto){
        return mailService.toEmail(toEmailDto);
    }

    /**
     *
     * @Description 退出登录
     * @param userLogoutDto
     * @return
     */
    @PostMapping(value = "/api/logout", produces = "application/json;charset=UTF-8")
    public OpenResponse logout(@RequestBody UserLogoutDto userLogoutDto){
        return userService.logout(userLogoutDto);
    }
}
