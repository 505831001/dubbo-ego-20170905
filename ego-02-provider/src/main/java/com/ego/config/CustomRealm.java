package com.ego.config;

import com.ego.dao.TbUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuweiwei
 * @since  2020-8-14
 */
public class CustomRealm extends AuthorizingRealm {

    protected TbUserMapper tbUserMapper;

    @Autowired
    private void setUserMapper(TbUserMapper userMapper) {
        this.tbUserMapper = userMapper;
    }

    /**
     * 获取授权信息
     *
     * @param authority
     * @return AuthorizationInfo 返回封装了用户信息的实例
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection authority) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获得该用户角色
        String role = tbUserMapper.getRole(username);
        Set<String> set = new HashSet<>();
        // 需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        // 设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

    /**
     * 1. 获取身份验证信息
     * 2. Shiro 中最终是通过 Realm 来获取应用程序中的用户角色及权限信息的
     *
     * @param authentic 用户身份信息
     * @return AuthenticationInfo 返回封装了用户信息的实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authentic) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authentic;
        // 从数据库获取对应用户名密码的用户
        String password = tbUserMapper.getPassword(token.getUsername());
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
}
