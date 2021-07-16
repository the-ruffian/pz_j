/*
 * @Description:角色
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 10:08
 * @LastEditTime: 2021-7-16 17:11:40
 * @LastEditors: the-ruffian
 */
package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Role;
import com.example.demo.model.dto.RoleListDto;
import com.example.demo.model.vo.RoleListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleDao extends BaseMapper<Role> {

    @Select("<script> " +
            "select " +
            "role_name,note,create_time,update_time " +
            "from role " +
            "where 1=1 " +
            "<if test=\"roleListDto!=null and roleListDto.roleName!=null and roleListDto.roleName!='' \">and role_name like CONCAT('%',#{roleListDto.roleName},'%')</if>" +
            "<if test=\"roleListDto!=null and roleListDto.note!=null and roleListDto.note!='' \">and note like CONCAT('%',#{roleListDto.note},'%')</if>" +
            "</script>")
    List<RoleListVo> search(@Param("roleListDto")RoleListDto roleListDto);
}
