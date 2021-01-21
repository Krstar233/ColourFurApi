package com.colourfur.userservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colourfur.userservice.entity.vo.LoginVO;
import com.colourfur.userservice.entity.vo.ProfileEditVO;
import com.colourfur.userservice.entity.vo.RegisterVO;
import com.colourfur.userservice.service.UserService;
import com.colourfur.commonutils.Result;
import com.colourfur.userservice.entity.User;
import com.colourfur.userservice.utils.CustomPasswordEncoder;
import com.colourfur.securityfilter.utils.TokenUtil;
import com.colourfur.servicebase.exception.MyException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVO loginVO, HttpServletResponse response){
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User doaUser = userService.getOne(wrapper);
        if (doaUser != null && CustomPasswordEncoder.matches(password, doaUser.getPassword())){
            TokenUtil.setToken(doaUser.getId(), doaUser.getClfGroupId(), response);
            return Result.ok().message("登录成功！");
        }
        return Result.error().message("登录失败！请检查用户名或密码是否正确！");
    }
    @ApiOperation("登出（删除token）")
    @PostMapping("/loginout")
    public Result loginout(HttpServletResponse response){
        TokenUtil.removeToken(response);
        return Result.ok().message("已登出！");
    }
    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVO registerVO) throws MyException {
        User check = userService.selectByUsername(registerVO.getUsername());
        if (check != null)
            throw new MyException("该用户名已被注册!");
        String encodedPassword = CustomPasswordEncoder.encode(registerVO.getPassword());    // 对密码加密
        User userToSave = new User();
        userToSave.setUsername(registerVO.getUsername());
        userToSave.setNickname(registerVO.getNickname());
        userToSave.setPassword(encodedPassword);
        userToSave.setGmtCreate(new Date());
        userToSave.setGmtUpdate(new Date());
        userService.save(userToSave);
        return Result.ok().message("注册成功！");
    }
    @ApiOperation("修改我的个人信息")
    @PatchMapping("/myProfile")
    public Result editProfileWithToken(@RequestBody ProfileEditVO profileEditVO,
                                       @ApiIgnore
                                       @RequestAttribute String uid){
        User userToUpdate = userService.getById(uid);
        String password = profileEditVO.getPassword();
        String nickname = profileEditVO.getNickname();
        if (password != null)
            userToUpdate.setPassword(CustomPasswordEncoder.encode(password));
        if (nickname != null)
            userToUpdate.setNickname(nickname);
        userToUpdate.setGmtUpdate(new Date());
        userService.updateById(userToUpdate);
        return Result.ok().message("修改成功！");
    }
    @ApiOperation("注销我的账号")
    @DeleteMapping("/me")
    public Result deleteUserWithToken(@ApiIgnore
                                      @RequestAttribute String uid, HttpServletResponse response){
        userService.removeById(uid);
        TokenUtil.removeToken(response);
        return Result.ok().message("注销成功！");
    }
}