package cc.tinker.web.controller;

import cc.tinker.entry.entity.FrontEndResponse;
import cc.tinker.web.entity.AuthenticationEntity;
import cc.tinker.web.entity.TokenEntity;
import cc.tinker.web.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Tinker on 2016/12/16.
 */
@RestController
@RequestMapping("/hs/auth")
public class AuthController {

    @Autowired
    private
    AuthenticationService authenticationService;

    /**
     * 验证用户是否登录，获取用户权限；
     *
     * @param token
     * @param account
     * @param password
     * @param response
     * @return
     */
    @RequestMapping("/loginWithToken.do")
    @CrossOrigin
    public FrontEndResponse authentication(@CookieValue(value = "token", defaultValue = "empty") String token, String account, String password, HttpServletResponse response) {
        TokenEntity tokenEntity = authenticationService.isTokenValid(token);
        if (!"empty".equals(token) && tokenEntity != null) {
            AuthenticationEntity authenticationEntity = authenticationService.findOne(tokenEntity.getUserId());
            authenticationService.updateTokenValidTime(tokenEntity);
            return new FrontEndResponse(true, authenticationEntity.getUserName());
        } else {
            return new FrontEndResponse(false, "token 不存在或已超期，请使用账户密码登录");
        }
    }

    @RequestMapping("/loginWithAccount.do")
    @CrossOrigin
    public FrontEndResponse loginWithAccount(AuthenticationEntity authenticationEntity, HttpServletResponse response) {
        AuthenticationEntity authenticationEntityDB = authenticationService.authentication(authenticationEntity.getUserEmail(), authenticationEntity.getUserPassword());
        if (authenticationEntityDB != null) {
            String token = authenticationService.generateNewToken(authenticationEntityDB.getId());
            Cookie responseCookie = new Cookie("token", token);
            responseCookie.setPath("/");
            responseCookie.setMaxAge(20 * 60);
            response.addCookie(responseCookie);
            response.addCookie(new Cookie("test", "test"));
            return new FrontEndResponse(true, authenticationEntityDB.getUserName());
        } else {
            return new FrontEndResponse(false, "用户名或密码不正确！");
        }
    }

    /**
     * 用户注册接口,根据实体传入参数
     *
     * @param auth
     * @return
     */
    @RequestMapping("/register.do")
    @CrossOrigin
    public FrontEndResponse register(@CookieValue(value = "token", defaultValue = "empty") String token, AuthenticationEntity auth, HttpServletResponse response, HttpServletRequest request) {
        /*新增用户，新增token，返回用户名*/
        Map map = authenticationService.register(auth);
        if ((boolean) map.get("success")) {
            Cookie responseCookie = new Cookie("token", (String) map.get("token"));
            responseCookie.setPath("/");
            responseCookie.setMaxAge(20 * 60);
            response.addCookie(responseCookie);
            AuthenticationEntity authenticationEntityDB = (AuthenticationEntity) map.get("auth");
            return new FrontEndResponse(true, authenticationEntityDB.getUserName());
        } else {
            return new FrontEndResponse(false, "Email已存在");
        }
    }


}
