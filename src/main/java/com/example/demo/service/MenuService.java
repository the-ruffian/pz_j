package com.example.demo.service;

import com.example.demo.model.dto.UserMenuListDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;


/**
 * @author bugpz
 * @return "获取用户菜单"
 */
public interface MenuService {
    OpenResponse userMenu(@Param("userMenuListDto")UserMenuListDto userMenuListDto);
}
