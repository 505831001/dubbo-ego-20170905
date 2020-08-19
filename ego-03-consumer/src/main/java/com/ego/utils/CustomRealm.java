package com.ego.utils;

import com.ego.service.TbUserServiceApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @Author liuweiwei 505831001@qq.com
 * @Description 自定义域 Realm
 * @since 2020-05-20
 */
@Component
public class CustomRealm extends AuthorizingRealm {
    private TbUserServiceApi tbUserServiceApi;

    @Autowired
    public CustomRealm(TbUserServiceApi tbUserServiceApi) {
        this.tbUserServiceApi = tbUserServiceApi;
    }

    /**
     * 获取身份鉴定信息(Authentication)
     *
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("<<Shiro 身份鉴定认证>> - Login 登录信息：" + authenticationToken);
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String password = tbUserServiceApi.getPassword(token.getUsername());
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
        System.out.println(info.getCredentials());
        System.out.println(info.getCredentialsSalt());
        System.out.println(info.getPrincipals());
        System.out.println(info.toString());
        return info;
    }

    /**
     * 获取授权信息(Authority)
     *
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("<<Shiro 鉴定认证授权>> - Login 登录信息：" + principalCollection);
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo authority = new SimpleAuthorizationInfo();
        System.out.println(authority.getObjectPermissions());
        System.out.println(authority.getStringPermissions());
        System.out.println(authority.getRoles());
        System.out.println(authority.toString());
        String role = tbUserServiceApi.getRole(username);
        Set<String> set = new LinkedHashSet<>();
        set.add(role);
        authority.setRoles(set);
        return authority;
    }
}