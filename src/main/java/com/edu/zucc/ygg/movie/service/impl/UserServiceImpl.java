package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.UserInfoMapper;
import com.edu.zucc.ygg.movie.dao.UserMapper;
import com.edu.zucc.ygg.movie.domain.User;
import com.edu.zucc.ygg.movie.domain.UserInfo;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.dto.UserDto;
import com.edu.zucc.ygg.movie.service.UserService;
import com.edu.zucc.ygg.movie.util.DateUtil;
import com.edu.zucc.ygg.movie.util.EncryptUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public ResultDto registerUser( UserDto userDto) {
        if(userDto.getUsername()==null)
            return ResultDtoFactory.toNack("账号名不能为空");
        if(userDto.getPassword()==null)
            return ResultDtoFactory.toNack("密码不能为空");
        User user = userMapper.getUser(userDto.getUsername());
        if (user!=null){
            return ResultDtoFactory.toNack("该账号名已经存在");
        }
        User addUser = convertToUser(userDto);
        addUser.setRole("user");
        addUser.setPermission("normal");
        if(insertSelective(addUser)!=null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(addUser.getId());
            userInfoMapper.insertSelective(userInfo);
            if (userInfo.getId()==null)
                return ResultDtoFactory.toAck("用户详细信息表插入数据库错误，注册失败");
            return ResultDtoFactory.toAck("注册成功", addUser);
        }
        else
            return ResultDtoFactory.toAck("用户表插入数据库错误，注册失败");
    }

    @Override
    public ResultDto updateUserInfo(UserDto userDto) {
        try {
            UserInfo userInfo = convertToUserInfo(userDto);
            userInfo.setUpdateTime(DateUtil.getNowTime());
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            return ResultDtoFactory.toAck("用户信息更新完成");
        }catch(Exception e){
            return ResultDtoFactory.toNack("数据库错误");
        }
    }

    @Override
    public ResultDto registerAdmin(UserDto userDto) {
        if(userDto.getUsername()==null)
            return ResultDtoFactory.toNack("账号名不能为空");
        if(userDto.getPassword()==null)
            return ResultDtoFactory.toNack("密码不能为空");
        User user = userMapper.getUser(userDto.getUsername());
        if (user!=null){
            return ResultDtoFactory.toNack("该账号名已经存在");
        }
        User addAdmin = convertToUser(userDto);
        addAdmin.setRole("admin");
        addAdmin.setPermission("normal");
        if(insertSelective(addAdmin)!=null)
            return ResultDtoFactory.toAck("注册成功",addAdmin);
        else
            return ResultDtoFactory.toAck("用户表数据库错误，注册失败");
    }

    @Override
    public ResultDto deleteAdmin(int adminId) {
        if (userMapper.deleteByPrimaryKey(adminId)==1)
            return ResultDtoFactory.toAck("删除成功");
        return ResultDtoFactory.toNack("数据库删除失败");
    }
    @Override
    public List<UserDto> getUserList(UserDto userDto) {
        return userMapper.getUserList(userDto);
    }

    @Override
    public List<UserDto> getAdminList(UserDto userDto) {
        return userMapper.getAdminList(userDto);
    }

    @Override
    public boolean banUser(String username) {
        int updateNumber= userMapper.banUser(username);
        if (updateNumber>0)
            return true;
        else
            return false;
    }

    private User convertToUser(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setPassword(EncryptUtil.encryptMd5(userDto.getPassword(),userDto.getUsername()));
        return user;
    }

    private UserInfo convertToUserInfo(UserDto userDto){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userDto.getId());
        userInfo.setEmail(userDto.getEmail());
        userInfo.setLocation(userDto.getLocation());
        userInfo.setOccupation(userDto.getOccupation());
        userInfo.setPersonalitySignature(userDto.getPersonalitySignature());
        userInfo.setSex(userDto.getSex());
        userInfo.setBirthDay(userDto.getBirthDay());
        userInfo.setPhonenumber(userDto.getPhoneNumber());
        userInfo.setNickname(userDto.getNickname());
        return userInfo;
    }
}
