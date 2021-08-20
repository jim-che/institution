package com.institution.shiro;

import com.institution.entity.Admin;
import com.institution.mapper.AdminMapper;
import com.institution.service.AdminService;
import com.institution.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenguo
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AdminService adminService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("----正在进行授权----");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("----正在进行认证----");
        JwtToken jwtToken = (JwtToken) token;
        String Id = jwtUtils.getClaimByToken((String)jwtToken.getCredentials()).getSubject();
        Admin admin = adminService.getById(Long.valueOf(Id));
        if(admin==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(admin.getStatus()==-1){
            throw new LockedAccountException("用户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(admin, profile);
        System.out.println("-----------");
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
