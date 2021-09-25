/*
 * @Description:邮件服务
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-08-14 16:14
 * @LastEditTime: 2021-09-25 22:17:02
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.model.dto.ToEmailDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

/**
 * @author bugpz
 * @return "发送邮件"
 */
public interface MailService {
    OpenResponse toEmail(@Param("toEmailDto")ToEmailDto toEmailDto);
}
