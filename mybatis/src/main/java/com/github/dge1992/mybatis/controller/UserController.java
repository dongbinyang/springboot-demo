package com.github.dge1992.mybatis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dge1992.mybatis.mutidatasource.annotion.DataSource;
import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.service.IUserService;
import com.github.dge1992.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/7
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/selectUserList")
    public List<User> selectUserList(){
        return userService.selectUserList();
    }

    @GetMapping("/selectUserById/{id}")
    public User selectUserById(@PathVariable Integer id){
        Optional<User> user = userService.selectUserById(id);
        return user.orElse(null);
    }

    @GetMapping("/selectList")
    public Page<Map<String, Object>> selectList(){
        Page<User> page = new Page<>(1, 10);
        return userService.selectList(page);
    }

    @GetMapping("/deleteById/{id}")
    public Object deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        return "删除成功";
    }

    @GetMapping("/getList")
    public List<User> getList(){
        return userService.getList();
    }

    @PostMapping("/insertUser")
    public Object insertUser(){
        User user = new User();
        user.setUserName("sw");
        user.setDes("31321");
        user.setAge(12);
        return userService.insertUser(user);
    }

    @PostMapping("/updateAll")
    public Object updateAll(){
        return userService.updateAll();
    }

    @GetMapping("/getCompanyList")
    public Object getCompanyList(){
        userService.getCompanyList();
        return "查询成功";
    }

    @PostMapping("/updateById")
    public Object updateById(){
        return userService.updateById();
    }

    @DataSource(name = "second")
    @RequestMapping("/testTransactional")
    public Object testTransactional(){
        User user = new User();
        user.setUserName("sw");
        user.setDes("31321");
        user.setAge(12);
        return userService.testTransactional(user);
    }

    @RequestMapping("/testOracle")
    public Object testOracle(){
        return userService.testOracle();
    }

    @Autowired
    IUserService userService2;

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        Optional<User> optionalUser = userService2.getUserById(null);
        return optionalUser.orElse(new User());
    }
}
