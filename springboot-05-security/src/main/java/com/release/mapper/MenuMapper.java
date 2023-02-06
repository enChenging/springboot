package com.release.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.release.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单 Mapper 接口
 *
 * @author yancheng
 * @since 2023/1/31
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询权限
     * @param userId
     * @return
     */
    List<String> selectPermsByUserId(Long userId);
}
