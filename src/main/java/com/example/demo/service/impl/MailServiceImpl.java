/*
 * @Description:邮件服务
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-08-14 16:41
 * @LastEditTime: 2021-09-25 22:17:10
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.SysCode;
import com.example.demo.entity.User;
import com.example.demo.mapper.SysCodeDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.ToEmailDto;
import com.example.demo.service.MailService;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.model.OpenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service("MailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserDao userDao;
    @Autowired
    SysCodeDao sysCodeDao;
    @Value("${spring.mail.username}")
    private String from;


    @Override
    public OpenResponse toEmail(ToEmailDto toEmailDto) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Integer userMail = userDao.selectCount(userQueryWrapper.eq("email",toEmailDto.getTos()));
        if (userMail==1){
            String code = PublicMethod.code().toString();
            SysCode sysCode = new SysCode();
            sysCode.setEmail(toEmailDto.getTos());
            sysCode.setCode(code);
            sysCode.setCreateTime(PublicMethod.getNowTime());
            sysCodeDao.insert(sysCode);
            //创建简单邮件消息
            SimpleMailMessage message = new SimpleMailMessage();
            //谁发的
            message.setFrom(from);
            //给谁发
            message.setTo(toEmailDto.getTos());
            //邮件标题
            message.setSubject("验证码");
            //邮件类容
            message.setText(code);
            try {
                mailSender.send(message);
                return OpenResponse.ok("邮件发送成功");
            } catch (MailException e) {
                e.printStackTrace();
                return OpenResponse.fail("邮件发送失败");
            }
        }else {
            return OpenResponse.fail(toEmailDto.getTos()+"在系统中不存在,请重新输入");
        }

    }
}
