package cc.tinker.wechat;

import cc.tinker.menu.*;
import cc.tinker.tool.HttpClientImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Wechat {

    private static final String APP_ID = "wx15d11bec71e28224";
    private static final String APP_SECRET = "d2c249e836b829ccb94aeffa708560a8";
    private static final String WEBSITE = "http://whoszus.6655.la";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    private static final String O_AUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String O_AUTH_PAGE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    private volatile static Wechat wechat = null;
    private AccessToken accessToken;

    private Wechat() {
        this.accessToken = new AccessToken(ACCESS_TOKEN_URL, APP_ID, APP_SECRET);
    }

    public static Wechat getInstance() {
        if (wechat == null) {
            synchronized (Wechat.class) {
                if (wechat == null) {
                    wechat = new Wechat();
                }
            }
        }
        return wechat;
    }

    public void createMenu() {
        HttpClientImpl httpclient = HttpClientImpl.getInstance();
        try {
            String url = CREATE_MENU_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
            String menu = this.menuData();
            String result = httpclient.postString(url, menu);
            JSONObject object = JSON.parseObject(result);
            Integer errcode = (Integer) object.get("errcode");
            if (errcode != 0) {
                System.out.println(object.get("errmsg").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu() {
        HttpClientImpl httpclient = HttpClientImpl.getInstance();
        try {
            String url = DELETE_MENU_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
            String result = httpclient.postMethod(url, null);
            JSONObject object = JSON.parseObject(result);
            Integer errcode = (Integer) object.get("errcode");
            if (errcode != 0) {
                System.out.println(object.get("errmsg").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String menuData() {
        View testButton = new View();
        testButton.setName("测试");
        String registerUrl = createMenuUrl(WEBSITE + "/tinker/test", "snsapi_base");
        testButton.setUrl(registerUrl);
        Second teacher = new Second();
        teacher.setName("Kindle");
        teacher.addSub_button(testButton);

        Click sign = new Click();
        sign.setName("签到");
        sign.setType(Button.SCANCODE_WAITMSG);
        sign.setKey(UsingButton.SIGN);
        View info = new View();
        info.setName("个人信息");
        String bindingUrl = createMenuUrl(WEBSITE + "/student/bindingPage", "snsapi_base");
        info.setUrl(bindingUrl);
        Click unbinding = new Click();
        unbinding.setName("解绑");
        unbinding.setKey(UsingButton.SIGN);
        View leave = new View();
        leave.setName("请假");
        String leaveUrl = createMenuUrl(WEBSITE + "/student/leavePage", "snsapi_base");
        leave.setUrl(leaveUrl);
        View getHomework = new View();
        getHomework.setName("查看作业");
        String getHmwUrl = createMenuUrl(WEBSITE + "/student/getHmwPage", "snsapi_base");
        getHomework.setUrl(getHmwUrl);
        Second student = new Second();
        student.setName("学生");
        student.addSub_button(sign);
        student.addSub_button(info);
        student.addSub_button(unbinding);
        student.addSub_button(leave);
        student.addSub_button(getHomework);

        Click help = new Click();
        help.setName("帮助");
        help.setKey(UsingButton.HELP);
        Menu menu = new Menu();
        menu.addButton(teacher);
        menu.addButton(student);
        menu.addButton(help);
        return JSON.toJSONString(menu, true);
    }

    private String createMenuUrl(String redirectUrl, String scope) {
        String url = null;
        try {
            String encoderUrl = URLEncoder.encode(redirectUrl, "utf-8");
            url = O_AUTH_PAGE_URL.replace("APPID", APP_ID).replace("SCOPE", scope).replace("REDIRECT_URI", encoderUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    public OAuthToken getOAuthToken(String code) {
        OAuthToken oAuthToken = null;
        HttpClientImpl httpclient = HttpClientImpl.getInstance();
        String result = null;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("appid", APP_ID);
            params.put("secret", APP_SECRET);
            params.put("code", code);
            params.put("grant_type", "authorization_code");
            result = httpclient.getMethod(O_AUTH_TOKEN_URL, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            JSONObject object = JSON.parseObject(result);
            oAuthToken = new OAuthToken();
            oAuthToken.setAccessToken(object.getString("access_token"));
            oAuthToken.setExpiresIn(object.getInteger("expires_in"));
            oAuthToken.setOpenId(object.getString("openid"));
            oAuthToken.setRefreshToken(object.getString("refresh_token"));
            oAuthToken.setScope(object.getString("scope"));
        }
        return oAuthToken;
    }

}
