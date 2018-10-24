package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.User;
import com.edu.zucc.ygg.movie.dto.UserDto;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper extends MyMapper<User> {
    /**
     * 获得密码
     * @param username 用户名
     */
    String getPassword(String username);

    /**
     * 获得角色权限
     * @param username 用户名
     * @return user/admin
     */
    String getRole(String username);

    /**
     * 修改密码
     */
    void updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

    /**
     * 获得存在的用户
     */
//    List<String> getUser();

    /**
     * 封号
     */
    void banUser(String username);

    /**
     * 检查用户状态
     */
    int checkUserBanStatus(String username);

    /**
     * 获得用户角色默认的权限
     */
    String getRolePermission(String username);

    /**
     * 获得用户的权限
     */
    String getPermission(String username);

    User getUser(String username);

    List<UserDto> getUserList(UserDto userDto);
}