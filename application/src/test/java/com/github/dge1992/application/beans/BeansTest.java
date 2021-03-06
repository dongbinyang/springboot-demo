package com.github.dge1992.application.beans;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author 董感恩
 * @date 2020-04-07 10:12
 * @desc
 */
public class BeansTest {

    public static void main(String[] args) {
        final BeanCopier copier = BeanCopier.create(UserPo.class, UserVo.class, false);
        UserPo userPo = new UserPo("dge", 12, "横街");
        UserVo userVo = new UserVo();
        UserVo userVo2 = new UserVo();
        copier.copy(userPo, userVo, null);
        System.out.println(userVo);

        BeanUtils.copyProperties(userPo, userVo2);
        System.out.println(userVo2);


        System.out.println("========================");

        UserVo userVo4 = new UserVo();
        UserVo userVo5 = new UserVo();

        userVo4.setName("dge");
        userVo4.setAge(11);

        userVo5.setName("dge");
        userVo5.setAge(11);

        System.out.println(userVo4.equals(userVo5));
    }
}
