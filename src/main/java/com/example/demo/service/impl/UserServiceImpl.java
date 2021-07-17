/*
 * @Description:用户模块实现类
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-01 19:44
 * @LastEditTime: 2021-07-17 17:55:54
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.*;
import com.example.demo.model.vo.UserListVo;
import com.example.demo.model.vo.UserLoginVo;
import com.example.demo.model.vo.UserUpdateVo;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.PublicMethod;
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

    @Override
    public OpenResponse registerUser(UserRegisterDto userRegisterDto) {
        Integer gender = userRegisterDto.getGender();//.equals("男")  ? 1 : userRegisterDto.getGender().equals("女") ? 0 : 2;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Integer integer = userDao.selectCount(userQueryWrapper.select("phone")
                .eq("phone",userRegisterDto.getPhone()));
        if (
                userRegisterDto.getUsername().equals("") || userRegisterDto.getUsername() == null ||
                userRegisterDto.getGender() == null ||
                userRegisterDto.getEmail().equals("") || userRegisterDto.getEmail() == null ||
                userRegisterDto.getPassword().equals("") || userRegisterDto.getPassword() == null ||
                userRegisterDto.getPhone().equals("") || userRegisterDto.getPhone() == null ) {
            return OpenResponse.fail("请正确填写内容");
        } else if (integer ==1){
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
            return OpenResponse.ok("尊敬的"+userRegisterDto.getUsername()+",恭喜你注册成功");
        } else {
            return OpenResponse.fail("未知错误");
        }
    }

    @Override
    public OpenResponse update(@Param("userUpdateDto") UserUpdateDto userUpdateDto){
        String username = userUpdateDto.getUsername();
        String email = userUpdateDto.getEmail();
        String phone = userUpdateDto.getPhone();
        String password = userUpdateDto.getPassword();
        String remark = userUpdateDto.getRemark();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        User userList = userDao.selectOne(userQueryWrapper.select("id").eq("phone", phone));

        User user = new User();
        UserUpdateVo userUpdateVo = new UserUpdateVo();
        if (userList!=null){
            Integer userID = userList.getId();
            if (null != email && !email.equals("")){
                user.setEmail(email);
                userUpdateVo.setEmail(email);
            }
            if (null != username && !username.equals("")){
                user.setUsername(username);
                userUpdateVo.setUsername(username);
            }
            if (null != remark && !remark.equals("")){
                user.setRemark(remark);
                userUpdateVo.setRemark(remark);
            }
            if (null !=password && !password.equals("")){
                user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            }
            if (
                    null != email && !email.equals("")
                            || null != username && !username.equals("")
                            || null != remark && !remark.equals("")
                            ||null !=password && !password.equals("")
            ){
                user.setId(userID);
                userDao.updateById(user);
                return OpenResponse.ok("修改成功", userUpdateVo);
            }
            return OpenResponse.fail("未做任何修改");
        }else if (userList==null){
            return OpenResponse.fail("没有此用户");
        }else {
            return OpenResponse.fail("未做任何修改");
        }
    }

    @Override
    public OpenResponse login(UserLoginDto userLoginDto){
        String phone = userLoginDto.getPhone();
        String password = userLoginDto.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        Integer integer = userDao.selectCount(userQueryWrapper);
        if (phone.equals("")||phone==null) {
            return OpenResponse.fail("手机号不能为空");
        }
        if (password.equals("")||password==null){
            return OpenResponse.fail("密码不能为空");
        }
        if (integer != 0){
            QueryWrapper<User> userQueryWrapper1 = new QueryWrapper<>();
            userQueryWrapper1.select("username","password").eq("phone",phone);
            User user = userDao.selectOne(userQueryWrapper1);
            if (md5Password.equals(user.getPassword())){
                Map<String, String> payload = new HashMap<>();
                payload.put("phone", phone);
                payload.put("md5Password", md5Password);
                String token = JWTUtils.getToken(payload);
                QueryWrapper<Token> tokenQueryWrapper = new QueryWrapper<>();
                tokenQueryWrapper.select("id").eq("phone",phone);
                Integer integer1 = tokenDao.selectCount(tokenQueryWrapper);
                if (integer1 == 1){
                    Token tokenList = tokenDao.selectOne(tokenQueryWrapper);
                    Integer tokenId = tokenList.getId();
                    Token token1 = new Token();
                    token1.setId(tokenId);
                    token1.setToken(token);
                    tokenDao.updateById(token1);
                } else {
                    Token token1 = new Token();
                    token1.setPhone(phone);
                    token1.setToken(token);
                    tokenDao.insert(token1);
                }
                user.setLoginTime(PublicMethod.getNowTime());
                userDao.update(user, userQueryWrapper.eq("phone",phone));
                UserLoginVo userLoginVo = new UserLoginVo();
                userLoginVo.setToken(token);
                userLoginVo.setUsername(user.getUsername());
                return OpenResponse.ok("登录成功",userLoginVo);
            } else {
            }   return OpenResponse.fail("密码错误");
        }else {
            return OpenResponse.fail("账号不存在");
        }
    }

    @Override
    public OpenResponse delete(UserDeleteDto userDeleteDto){
        String phone=userDeleteDto.getPhone();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (userDao.selectCount(userQueryWrapper.eq("phone",phone))==1){
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("phone",phone);
            userDao.deleteByMap(stringObjectHashMap);
            return OpenResponse.ok("删除成功");
        }
        if (userDao.selectCount(userQueryWrapper.eq("phone",phone))==0){
            return OpenResponse.fail("用户不存在");
        }
        return OpenResponse.fail("参数错误");
    }

    @Override
    public OpenResponse search(UserListDto userListDto){
        PageMethod.startPage(userListDto.getPageNo(),userListDto.getPageSize());
        List<UserListVo> userListVos = userDao.userSearch(userListDto);
        return OpenResponse.ok("ok",new PageInfo<>(userListVos));
    }
}
