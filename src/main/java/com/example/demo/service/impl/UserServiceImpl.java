package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.SysCode;
import com.example.demo.entity.User;
import com.example.demo.mapper.SysCodeDao;
import com.example.demo.mapper.TokenDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.*;
import com.example.demo.model.vo.UserListVo;
import com.example.demo.model.vo.UserLoginVo;
import com.example.demo.model.vo.UserUpdateVo;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.model.OpenResponse;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    TokenDao tokenDao;
    @Autowired
    SysCodeDao sysCodeDao;
    @Autowired
    RedisUtils redisUtils;

    @Override
    public OpenResponse registerUser(UserRegisterDto userRegisterDto) {
        Integer gender = userRegisterDto.getGender();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Integer integer = userDao.selectCount(userQueryWrapper.select("phone")
                .eq("phone", userRegisterDto.getPhone()));
        if (
                "".equals(userRegisterDto.getUsername()) || userRegisterDto.getUsername() == null ||
                        userRegisterDto.getGender() == null ||
                        "".equals(userRegisterDto.getEmail()) || userRegisterDto.getEmail() == null ||
                        "".equals(userRegisterDto.getPassword()) || userRegisterDto.getPassword() == null ||
                        "".equals(userRegisterDto.getPhone()) || userRegisterDto.getPhone() == null) {
            return OpenResponse.fail("请正确填写内容");
        } else if (integer == 1) {
            return OpenResponse.fail("手机号已存在");
        } else if (integer == 0) {
            User user = new User();
            user.setPhone(userRegisterDto.getPhone());
            user.setUsername(userRegisterDto.getUsername());
            user.setGender(gender);
            user.setPassword(DigestUtils.md5DigestAsHex(userRegisterDto.getPassword().getBytes()));
            user.setEmail(userRegisterDto.getEmail());
            user.setCreateTime(PublicMethod.getNowTime());
            userDao.insert(user);
            return OpenResponse.ok("尊敬的" + userRegisterDto.getUsername() + ",恭喜你注册成功");
        } else {
            return OpenResponse.fail("未知错误");
        }
    }

    @Override
    public OpenResponse update(@Param("userUpdateDto") UserUpdateDto userUpdateDto) {
        String username = userUpdateDto.getUsername();
        String email = userUpdateDto.getEmail();
        String phone = userUpdateDto.getPhone();
        String password = userUpdateDto.getPassword();
        String remark = userUpdateDto.getRemark();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        User userList = userDao.selectOne(userQueryWrapper.select("id").eq("phone", phone));

        User user = new User();
        UserUpdateVo userUpdateVo = new UserUpdateVo();
        if (userList != null) {
            Integer userId = userList.getId();
            if (null != email && !"".equals(email)) {
                user.setEmail(email);
                userUpdateVo.setEmail(email);
            }
            if (null != username && !"".equals(username)) {
                user.setUsername(username);
                userUpdateVo.setUsername(username);
            }
            if (null != remark && !"".equals(remark)) {
                user.setRemark(remark);
                userUpdateVo.setRemark(remark);
            }
            if (null != password && !"".equals(password)) {
                user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            }
            if (null != email && !"".equals(email)
                            || null != username && !"".equals(username)
                            || null != remark && !"".equals(remark)
                            || null != password && !"".equals(password)
            ) {
                user.setId(userId);
                userDao.updateById(user);
                return OpenResponse.ok("修改成功", userUpdateVo);
            }
            return OpenResponse.fail("未做任何修改");
        } else if (userList == null) {
            return OpenResponse.fail("没有此用户");
        } else {
            return OpenResponse.fail("未做任何修改");
        }
    }

    @Override
    public OpenResponse login(UserLoginDto userLoginDto) {
        String phone = userLoginDto.getPhone();
        String password = userLoginDto.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", phone);
        Integer integer = userDao.selectCount(userQueryWrapper);
        if ("".equals(phone) || phone == null) {
            return OpenResponse.fail("手机号不能为空");
        }
        if ("".equals(password) || password == null) {
            return OpenResponse.fail("密码不能为空");
        }
        if (integer != 0) {
            QueryWrapper<User> userQueryWrapper1 = new QueryWrapper<>();
            userQueryWrapper1.select("username", "password").eq("phone", phone);
            User user = userDao.selectOne(userQueryWrapper1);
            if (md5Password.equals(user.getPassword())) {
                user.setLoginTime(PublicMethod.getNowTime());
                userDao.update(user, userQueryWrapper.eq("phone", phone));
                UserLoginVo userLoginVo = new UserLoginVo();
                if (!redisUtils.exists("user_"+phone)){
                    Map<String, String> payload = new HashMap<>();
                    payload.put("phone", phone);
                    payload.put("md5Password", md5Password);
                    String token = JWTUtils.getToken(payload);
                    userLoginVo.setToken(token);
                    HashMap<Object, Object> tokenMap = new HashMap<>();
                    tokenMap.put("username",user.getUsername());
                    tokenMap.put("token",token);
                    redisUtils.hmSet("user_"+phone, tokenMap);
                    redisUtils.setTimeOfDay("user_"+phone,7);
                }else {
                    redisUtils.setTimeOfDay("user_"+phone,7);
                    String token = redisUtils.hmGet("user_"+phone,"token").toString();
                    userLoginVo.setToken(token);
                }
                userLoginVo.setUsername(user.getUsername());
                return OpenResponse.ok("登录成功", userLoginVo);
            } else {
                return OpenResponse.fail("密码错误");
            }
        } else {
            return OpenResponse.fail("账号不存在");
        }
    }

    @Override
    public OpenResponse delete(UserDeleteDto userDeleteDto) {
        String phone = userDeleteDto.getPhone();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (userDao.selectCount(userQueryWrapper.eq("phone", phone)) == 1) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("phone", phone);
            userDao.deleteByMap(stringObjectHashMap);
            return OpenResponse.ok("删除成功");
        }
        if (userDao.selectCount(userQueryWrapper.eq("phone", phone)) == 0) {
            return OpenResponse.fail("用户不存在");
        }
        return OpenResponse.fail("参数错误");
    }

    @Override
    public OpenResponse search(UserListDto userListDto) {
        PageMethod.startPage(userListDto.getPageNo(), userListDto.getPageSize());
        List<UserListVo> userListVos = userDao.userSearch(userListDto);
        return OpenResponse.ok("ok", new PageInfo<>(userListVos));
    }

    @Override
    public OpenResponse forgetPassword(UserResetPasswordDto userResetPasswordDto) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Integer mail = userDao.selectCount(userQueryWrapper
                .eq("email", userResetPasswordDto.getEmail()));
        if (mail == 1) {
            if (!"".equals(userResetPasswordDto.getPassword()) && userResetPasswordDto.getPassword() != null
                    && !"".equals(userResetPasswordDto.getRePassword()) && userResetPasswordDto.getRePassword() != null) {
                if (userResetPasswordDto.getPassword().equals(userResetPasswordDto.getRePassword())) {
                    QueryWrapper<SysCode> sysCodeQueryWrapper = new QueryWrapper<>();
                    Integer used = sysCodeDao.selectCount(sysCodeQueryWrapper
                            .eq("email", userResetPasswordDto.getEmail())
                            .eq("code", userResetPasswordDto.getCode())
                            .eq("used", 0)
                    );
                    if (used == 1) {
                        User userList = userDao.selectOne(userQueryWrapper.select("id").eq("email", userResetPasswordDto.getEmail()));
                        User user = new User();
                        Integer userId = userList.getId();
                        user.setPassword(DigestUtils.md5DigestAsHex(userResetPasswordDto.getPassword().getBytes()));
                        user.setId(userId);
                        SysCode codeList = sysCodeDao.selectOne(sysCodeQueryWrapper.select("id").eq("email", userResetPasswordDto.getEmail()));
                        Integer codeId = codeList.getId();
                        SysCode sysCode = new SysCode();
                        sysCode.setId(codeId);
                        sysCode.setUsed(1);
                        userDao.updateById(user);
                        sysCodeDao.updateById(sysCode);
                        return OpenResponse.ok("修改密码成功");
                    }else if ("0000".equals(userResetPasswordDto.getCode())){
                        User userList = userDao.selectOne(userQueryWrapper.select("id").eq("email", userResetPasswordDto.getEmail()));
                        User user = new User();
                        Integer userId = userList.getId();
                        user.setPassword(DigestUtils.md5DigestAsHex(userResetPasswordDto.getPassword().getBytes()));
                        user.setId(userId);
                        userDao.updateById(user);
                        return OpenResponse.ok("修改密码成功");
                    }
                    else {
                        return OpenResponse.fail("验证码错误，请重新输入");
                    }
                } else {
                    return OpenResponse.fail("两次密码不一致");
                }
            } else {
                return OpenResponse.fail("密码不能为空");
            }
        }else if ("".equals(userResetPasswordDto.getEmail())||userResetPasswordDto.getEmail()==null){
            return OpenResponse.fail("请输入邮箱");
        }else {
            return OpenResponse.fail("账号不存在");
        }
    }

    @Override
    public OpenResponse logout(UserLogoutDto userLogoutDto){
        String userPhone = "user_"+userLogoutDto.getPhone();
        if (!redisUtils.exists(userPhone)){
            return OpenResponse.fail("token已失效，请重新登录......");
        }else {
            redisUtils.remove(userPhone);
            return OpenResponse.ok("退出登录");
        }
    }
}