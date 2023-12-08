package com.pond.build.expression;

import com.pond.build.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ex")
public class QXExpressionRoot {

    //也可以在这里抛出其他的错误,但是验证通过的话要返回true


    //String authority 这里是后端赋给它的权限
    //从数据库获取登录用户的权限功能  和authority 进行对比
    public boolean hasAuthority(String authority){
        //获取当前用户得权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在  authority
        return permissions.contains(authority);
    }

}
