package com.github.dge1992.mybatis.controller;

import com.github.dge1992.mybatis.domain.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 董感恩
 * @date 2020-02-10 13:01
 * @desc 校验控制器
 */
@RequestMapping("/validator")
@RestController
@Validated
public class ValidatorController {

    @Autowired
    private Validator validator;

    /**
     * @author 董感恩
     * @date 2020-02-10 13:05:17
     * @desc 测试一
     **/
    @PostMapping("/testOne")
    public Object testOne(@RequestBody @Valid ValidatorDomain validatorDomain, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        return validatorDomain;
    }

    /**
     * @author 董感恩
     * @date 2020-02-10 13:43:43
     * @desc 测试二
     **/
    @GetMapping("/testTwo")
    public Object testTwo(@Range(min = 1, max = 9, message = "年龄只能从1-9")
                              @RequestParam(name = "age")Integer age,
                          @Min(value = 1, message = "班级最小只能1")
                          @Max(value = 99, message = "班级最大只能99")
                          @RequestParam(name = "classroom")
                                  Integer classroom){
        return age + " " + classroom;
    }

    /**
     * @author 董感恩
     * @date 2020-02-10 14:48:38
     * @desc 测试三
     **/
    @GetMapping("/testThree")
    public Object testThree(){
        ValidatorTwoDomain domain = new ValidatorTwoDomain();
        domain.setLength("book");
        domain.setAge("1000");
        domain.setHigh(100);
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        domain.setList(list);
        Set<ConstraintViolation<ValidatorTwoDomain>> validate =
                validator.validate(domain);
        for (ConstraintViolation<ValidatorTwoDomain> model : validate) {
            System.out.println(model.getMessage());
        }
        return "success";
    }

    /**
     * @author 董感恩
     * @date 2020-02-10 15:00:39
     * @desc 测试四
     **/
    @GetMapping("/testFour")
    public Object testFour(){
        ValidatorThreeDomain domain = new ValidatorThreeDomain();
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        domain.setList(list);
        ValidatorFourDomain fourDomain = new ValidatorFourDomain();
        fourDomain.setExtField("like");
        domain.setDemo3(fourDomain);
        Set<ConstraintViolation<ValidatorThreeDomain>> validate =
                validator.validate(domain);
        for (ConstraintViolation<ValidatorThreeDomain> model : validate) {
            System.out.println(model.getMessage());
        }
        return "success";
    }

    /**
     * @author 董感恩
     * @date 2020-02-10 15:00:39
     * @desc 测试五
     **/
    @PostMapping("/testFive")
    public Object testFive(@RequestBody @Validated({GroupA.class, GroupB.class}) Person person, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        return person;
    }

    /**
     * 
     * @author 董感恩
     * @date 2020-02-10 15:56:09
     * @desc 测试六
     **/
    @GetMapping("/testSix")
    public Object testSix(){
        Person person = new Person();
        person.setUserId(-1);
        person.setUserName("cat");
        person.setAge(1000);
        person.setSex(12);
        Set<ConstraintViolation<Person>> validate =
                validator.validate(person, GroupA.class, GroupB.class);
        for (ConstraintViolation<Person> model : validate) {
            System.out.println(model.getMessage());
        }
        return person;
    }

    /**
     *
     * @author 董感恩
     * @date 2020-02-10 15:56:09
     * @desc 测试七
     **/
    @GetMapping("/testSeven")
    public Object testSeven(){
        Person person = new Person();
        person.setUserId(1);
        person.setUserName("cat");
        person.setAge(1000);
        person.setSex(12);
        Set<ConstraintViolation<Person>> validate =
                validator.validate(person, GroupOrder.class);
        for (ConstraintViolation<Person> model : validate) {
            System.out.println(model.getMessage());
        }
        return person;
    }

    /**
     * @author 董感恩
     * @date 2020-02-10 16:29:52
     * @desc 测试大小写验证
     **/
    @PostMapping("/CheckCase")
    public Object CheckCase(@RequestBody @Valid CheckCaseDomain checkCaseDomain, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        return checkCaseDomain;
    }

}
