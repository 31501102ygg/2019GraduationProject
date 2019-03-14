package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.User;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.dto.UserDto;

import java.util.List;

public interface UserService extends Service<User>{
    ResultDto registerUser(UserDto userDto);

    ResultDto updateUserInfo(UserDto userDto);

    ResultDto registerAdmin(UserDto userDto);

    ResultDto deleteAdmin(int adminId);

    List<UserDto> getUserList(UserDto userDto);

    List<UserDto> getAdminList(UserDto userDto);

    boolean banUser(String username);

    boolean unBanUser(String username);

    UserDto getUserByUserName(String username);

    UserDto getUser(Integer userId);

    User getUser(String username);

    void updateUserHeaderImg(String username,String headerImg);

    Integer getUserId(String username);

    public void upgradeToPro(int id);
}
