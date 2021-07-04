/*
 * @Description:UserLoginServiceImpl
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-04 17:06
 * @LastEditTime: 2021-07-04 17:06
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.mapper.TokenDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.UserLoginDto;
import com.example.demo.model.vo.UserLoginVo;
import com.example.demo.service.UserLoginService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.model.OpenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.HashMap;
import java.util.Map;


@Service("UserLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    TokenDao tokenDao;
    @Autowired
    UserDao userDao;

    public OpenResponse login(UserLoginDto userLoginDto){
        String phone = userLoginDto.getPhone();
        String password = userLoginDto.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        Integer integer = userDao.selectCount(userQueryWrapper);
        if (phone.equals("")||phone==null) {
            return OpenResponse.ok("手机号不能为空");
        }
        if (password.equals("")||password==null){
            return OpenResponse.ok("密码不能为空");
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
            }   return OpenResponse.ok("密码错误");
        }else {
            return OpenResponse.ok("账号不存在");
        }
    }
}
