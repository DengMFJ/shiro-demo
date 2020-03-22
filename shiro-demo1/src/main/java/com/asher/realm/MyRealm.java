package com.asher.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {


    public String getName() {
        return "MyRealm";
    }

    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("Realm1执行");
        //获取用户名
        String userName = (String) token.getPrincipal();
        //获取密码
        String password = new String((char[])token.getCredentials());
        System.out.println("用户名=====>>>>>>>"+userName);
        System.out.println("密码=======>>>>>>>"+password);
        if(!"zhangsan".equals(userName)){
            throw new UnknownAccountException();//用户名错误
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();//密码错误
        }
        //如果认证成功，则返回AuthenticationInfo
        return new SimpleAuthenticationInfo(userName,password,getName());
    }
}
