//2021-07-18 16:46:35
package com.example.demo.service;

import com.example.demo.model.dto.UserMenuListDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface MenuService {
    OpenResponse userMenu(@Param("userMenuListDto")UserMenuListDto userMenuListDto);
}
