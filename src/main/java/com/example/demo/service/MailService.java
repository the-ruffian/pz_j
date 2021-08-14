/*
 * @Description:邮件服务
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-08-14 16:14
 * @LastEditTime: 2021-8-14 17:30:29
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.model.dto.ToEmailDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface MailService {
    OpenResponse toEmail(@Param("toEmailDto")ToEmailDto toEmailDto);
}
