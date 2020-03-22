package com.asher;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;

import java.util.logging.Logger;

public class ShiroTest {
    public static void main(String[] args) {
        //获取SecutiryManager工厂，加载shiro.ini配置文件初始化SecutiryManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //得到SecurityManager实例 并绑定给SecurityUitls
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        Subject subject1 = ThreadContext.getSubject();
        System.out.println(subject == subject1);

        //得到subject及创建用户名、密码身份验证token(即用户身份/凭证)
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //登陆，即身份验证
            subject.login(token);
            System.out.println("登陆成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //登陆失败
            System.out.println("登录失败");
        }
        //退出
        subject.logout();
    }
}
