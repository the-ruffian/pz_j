package com.example.demo.Comtroller;

/*import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;*/
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

/*@ResponseBody
@Controller @RestController可以代替前两个*/
@RestController  /*这个类的所有方法返回的所有方法直接写给浏览器（如果是对象转为json数据）*/
public class HelloController {

    @PostMapping(value = "/h")
    public String hello(@RequestParam( "username") String username,
                        @RequestParam( "password") String password
                        ) {
        JSONObject jsonObject = new JSONObject();
        if (username.equals("bugPZ") && password.equals("123456")){
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("code", 200);
            jsonObject.put("msg", "登录成功");
            return jsonObject.toJSONString();
        }
        return "hello, world";
    }
}
