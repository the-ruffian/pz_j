package com.example.demo.comtroller;

/*import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;*/
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

/*@ResponseBody
@Controller @RestController可以代替前两个*/
@RestController  /*这个类的所有方法返回的所有方法直接写给浏览器（如果是对象转为json数据）*/
public class HelloController {

/*
    @PostMapping(value = "/h")
*/
    @ResponseBody
    @RequestMapping(value = "/api/h", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String hello(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam);
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        JSONObject jsonObject = new JSONObject();
        if (username.equals("bugPZ") && password.equals("123456")){
            jsonObject.put("code", 200);
            jsonObject.put("msg", "登录成功");
        } else {
            jsonObject.put("code",400);
            jsonObject.put("msg", "账号或密码错误");

        }
        return jsonObject.toJSONString();
    }
}
