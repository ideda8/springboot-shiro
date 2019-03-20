package com.da.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken   Controller传过来的token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //在这里判断用户名密码

        String name = "dada";
        String password = "123456";

        //判断用户名密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if(!token.getUsername().equals(name)){
            //用户名不存在
            return null;//shiro底层会抛出UnknownAccountException异常
        }

        //判断密码 AuthenticationInfo的子类SimpleAuthenticationInfo
        //SimpleAuthenticationInfo参数:
        // principal 需要返回方法的数据
        // credentials 数据库的密码 Shiro自动判断
        // realmName Shiro的名字
        return new SimpleAuthenticationInfo("",password,"");

    }
}
