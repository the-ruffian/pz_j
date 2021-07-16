package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import com.example.demo.model.dto.UserListDto;
import com.example.demo.model.vo.UserListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserDao extends BaseMapper<User> {
    @Select("<script>" +
            "select " +
            "u.phone,u.username,u.gender,u.status,u.email,u.login_time," +
            "r.role_name " +
            "from " +
            "user u  left join  user_role ur on u.id = ur.user_id  left join role r  on  r.id = ur.role_id " +
            "where 1=1 " +
            "<if test=\"userListDto!=null and userListDto.phone!=null and userListDto.phone!=''\"> and u.phone like CONCAT('%',#{userListDto.phone},'%') </if>" +
            "<if test=\"userListDto!=null and userListDto.username!=null and userListDto.username!=''\"> and  u.username like CONCAT('%',#{userListDto.username},'%') </if>" +
            "<if test=\"userListDto!=null and userListDto.email!=null and userListDto.email!=''\"> and u.email like CONCAT('%',#{userListDto.email},'%')% </if>" +
            "<if test=\"userListDto!=null and userListDto.gender!=null\"> and u.gender=#{userListDto.gender}</if> " +
            " </script>")
    List<UserListVo> userSearch(@Param("userListDto") UserListDto userListDto);
}
