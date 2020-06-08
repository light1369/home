package com.example.demo.shiro;

import com.example.demo.domain.User;
import com.example.demo.map.UserMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Duan
 * @date 2020/5/21 - 14:24
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserMap userMap;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        //给资源授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //添加资源授权字符串
//        info.addStringPermission("user:add");
        //获取当前登录用户
        Subject subject=SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        User dbUser=userMap.selectId(user.getId());
        //查询当前登录用户的授权字符串
        info.addStringPermission(dbUser.getPerms());
        return info;
    }


    /**
     * 执行认证逻辑
     * @param arg0
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //假设用户密码
//        String name="admin";
//        String password="123";
        //编写shiro判断逻辑,判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)arg0;
        User user=userMap.findByName(token.getUsername());
        if (user==null){
            //用户名不存在
            return null;//shiro底层会抛出异常UnknownAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
