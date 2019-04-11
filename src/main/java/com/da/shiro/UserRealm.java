package com.da.shiro;

import com.da.po.User;
import com.da.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑(资源 角色授权)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
//        info.addStringPermission("user:add");

        //从数据库查询当前登录用户的授权字符串
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();

        User u = userService.findById(user.getId());

        info.addStringPermission(u.getPerms());

        return info;
    }


    @Autowired
    private UserService userService;

    /**
     * 执行认证逻辑(登录)
     * @param authenticationToken   Controller传过来的token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //在这里判断用户名密码
//        String name = "dada";
//        String password = "123456";

        //判断用户名密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

//        if(!token.getUsername().equals(name)){
//            //用户名不存在
//            return null;//shiro底层会抛出UnknownAccountException异常
//        }
//
        //判断密码 AuthenticationInfo的子类SimpleAuthenticationInfo
        //SimpleAuthenticationInfo参数:
        // principal 需要返回方法的数据 会传到doGetAuthorizationInfo的principal中
        // credentials 数据库的密码 Shiro自动判断
        // realmName Shiro的名字
//        return new SimpleAuthenticationInfo("",password,"");

        User user = userService.findByName(token.getUsername());
        if(user == null){
            //用户名不存在
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");

    }
}
