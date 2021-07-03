package com.example.demo.comtroller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.enums.Status;
import com.example.demo.mapper.TokenDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.PublicMethod;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.model.ApiResponse;


import java.util.*;

@RestController
@Api(tags = "用户模块")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private UserService userService;

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
    * 2.创建一条信息
    * */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/api/user/register", produces = "application/json;charset=UTF-8")
    public Object create(@RequestBody JSONObject jsonParam){
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        String email = jsonParam.getString("email");
        String phone = jsonParam.getString("phone");
        Integer gender = jsonParam.getString("gender").equals("男")  ? 1 : jsonParam.getString("gender").equals("女") ? 0 : 2;
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("phone").like("phone",phone);
        Integer integer = userDao.selectCount(userQueryWrapper);
        JSONObject obj = new JSONObject();
        if (integer == 0){
            User user = new User();
            user.setPhone(phone);
            user.setPassword(md5Password);
            user.setGender(gender);
            user.setUsername(username);
            user.setEmail(email);
            user.setCreateTime(PublicMethod.getNowTime());
            userDao.insert(user);
            obj.put("code",200);
            obj.put("msg", "尊敬的" + username + "用户，恭喜你注册成功");
        } else {
            obj.put("code",400);
            obj.put("msg","该手机号已注册");
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
        String email = jsonParam.getString("email");
        QueryWrapper<User> userHave = new QueryWrapper<>();
        userHave.select("id").eq("phone",phone);
        Integer integer = userDao.selectCount(userHave);
        JSONObject obj = new JSONObject();
        if (integer == 1
        && username != null && !username.equals("")
        && email != null && !email.equals("")){
            User user = new User();
            User userList = userDao.selectOne(userHave);
            Integer userid = userList.getId();
            user.setId(userid);
            user.setEmail(email);
            user.setUsername(username);
            user.setUpdateTime(PublicMethod.getNowTime());
            userDao.updateById(user);
            obj.put("msg","资料修改成功");
            obj.put("code", 200);
        } else if (username == null || !username.equals("")){
            obj.put("msg","修改失败，username不能为空");
            obj.put("code", 401);
        } else if (email == null || !email.equals("")) {
            obj.put("msg","修改失败，email不能为空");
            obj.put("code", 401);
        }
        return obj.toJSONString();
    }

    /*
    * 删除用户
    * */
    @ApiOperation(value = "删除账号")
    @DeleteMapping("/api/user/delete/{phone}")
    public Object delete(@PathVariable("phone")String phone){
        QueryWrapper<User> userHave = new QueryWrapper<>();
        userHave.select("id").eq("phone",phone);
        Integer integer = userDao.selectCount(userHave);
        JSONObject obj = new JSONObject();
        if (integer == 1){
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("phone",phone);
            userDao.deleteByMap(stringObjectHashMap);
            obj.put("msg","删除成功");
            obj.put("code", 200);
        } else {
            obj.put("msg", "没有此用户");
            obj.put("code", 400);
        }

        return obj.toJSONString();
    }

    @ApiOperation("用户登录")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 400, message = "请求参数没填好"),
//            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
//    })
    @PostMapping(value = "/api/user/login", produces = "application/json;charset=UTF-8")
    public Object login(@RequestBody JSONObject jsonParam){
        String phone = jsonParam.getString("phone");
        String password = jsonParam.getString("password");
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(phone + password);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("password").eq("phone",phone);
        Integer integer = userDao.selectCount(userQueryWrapper);
        JSONObject obj = new JSONObject();
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

    @PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public Object registerUser(@RequestBody  UserRegisterDto userRegisterDto) {
        userService.registerUser(userRegisterDto);
        return ApiResponse.ok("成功");
    }
}
