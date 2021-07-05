/*
 * @Description:UserUpdateServiceImpl
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-05 14:54
 * @LastEditTime: 2021-07-05 21:10:29
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.UserUpdateDto;
import com.example.demo.model.vo.UserUpdateVo;
import com.example.demo.service.UserUpdateService;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service("UserUpdateService")
public class UserUpdateServiceImpl implements UserUpdateService {

    @Autowired
    UserDao userDao;

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
            return OpenResponse.ok("未做任何修改");
        }else if (userList==null){
            return OpenResponse.ok("没有此用户");
        }else {
            return OpenResponse.ok("未做任何修改");
        }
    }
}
