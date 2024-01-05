package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     *通过在方法参数中声明 HttpServletResponse，Spring MVC 会自动将当前请求的 HttpServletResponse 对象传递给你的方法。在方法内部，你就可以通过 response 对象进行操作，比如设置 Cookie。

//   @GetMapping("/set-cookie")
     public String setCookie(HttpServletResponse response) {
     // 创建一个名为 "token" 的 Cookie
     Cookie cookie = new Cookie("token", "abc123");

     // 设置 Cookie 的路径
     cookie.setPath("/");

     // 设置 Cookie 的过期时间（以秒为单位，这里设置为一天）
     cookie.setMaxAge(24 * 60 * 60);

     // 将 Cookie 添加到响应头中
     response.addCookie(cookie);

     return "Cookie set successfully!";
     }



//     @Controller
     public class YourController {

//     @RequestMapping("/yourEndpoint")
     public String yourEndpoint(@CookieValue(value = "token", required = false) String token) {
     // 处理获取到的 token
     if (token != null) {
     // 执行相应的操作
     }
     return "yourView"; // 返回视图或其他响应
     }
     }

     */
    @PostMapping("/user/login")
//    @PreAuthorize("permitAll")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @PostMapping("/user/refresh")
//    @PreAuthorize("permitAll")
    public ResponseResult refreshToken(@RequestParam("token") String refreshToken){
        return loginService.refreshToken(refreshToken);
    }

    @GetMapping("/onlineNum")
    public ResponseResult getOnlineNum(){
        return loginService.getOnlineNum();
    }

}