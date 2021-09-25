package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Permission;
import com.example.demo.model.dto.UserMenuListDto;
import com.example.demo.model.vo.AllPermissionVo;
import com.example.demo.model.vo.UserMenuListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author bugpz
 * @date 2021-09-25 22:38:05
 */
@Component
public interface PermissionDao extends BaseMapper<Permission> {
    @Select("<script>" +
            "select p.icon,p.id,p.level,p.parent_id,p.permission_name,p.remark,p.sort_num,p.type,p.url " +
            "from user u left join user_role ur on u.id = ur.user_id " +
            "            left join role_permission rp  on rp.role_id = ur.role_id " +
            "            left join role r on r.id = ur.role_id " +
            "            left join permission p on p.id = rp.permission_id " +
            "where u.phone= #{userMenuListDto.phone}" +
            "</script>")
    /**
     * @param UserMenuListDto userMenuListDto
     * @return List<UserMenuListVo>
     */
    List<UserMenuListVo> allMenu(@Param("userMenuListDto")UserMenuListDto userMenuListDto);

    @Select("<script>" +
            "select id,type,icon,level,url,code,permission_name,sort_num,parent_id,remark\n" +
            "from permission\n" +
            "</script>")
    /**
     * @return 获取所有菜单资源
     */
    List<AllPermissionVo> allPermission();
}
