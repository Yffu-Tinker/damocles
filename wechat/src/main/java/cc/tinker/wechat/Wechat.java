package cc.tinker.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cc.tinker.menu.*;
import cc.tinker.tool.HttpClientImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Wechat {

	private static final String appId = "wx15d11bec71e28224";
	private static final String appSecret = "d2c249e836b829ccb94aeffa708560a8";
	private static final String WEBSITE= "http://whoszus.6655.la";

	private static final String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	private static final String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String deleteMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	private static final String oAuthTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String oAuthPageUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	private volatile static Wechat wechat = null;
	private AccessToken accessToken;

	private Wechat() {
		this.accessToken = new AccessToken(accessTokenUrl, appId, appSecret);
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
			String url = createMenuUrl.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
			String menu = menuData();
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
			String url = deleteMenuUrl.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
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
		View register = new View();
		register.setName("申请注册");
		String registerUrl = createMenuUrl(WEBSITE+"/teacher/registerPage", "snsapi_base");
		register.setUrl(registerUrl);
		View publish = new View();
		publish.setName("发布作业");
		String publishUrl = createMenuUrl(WEBSITE+"/teacher/publishPage", "snsapi_base");
		publish.setUrl(publishUrl);
		Second teacher = new Second();
		teacher.setName("教师");
		teacher.addSub_button(register);
		teacher.addSub_button(publish);

		Click sign = new Click();
		sign.setName("签到");
		sign.setType(Button.SCANCODE_WAITMSG);
		sign.setKey(UsingButton.SIGN);
		View info = new View();
		info.setName("个人信息");
		String bindingUrl = createMenuUrl(WEBSITE+"/student/bindingPage", "snsapi_base");
		info.setUrl(bindingUrl);
		Click unbinding = new Click();
		unbinding.setName("解绑");
		unbinding.setKey(UsingButton.SIGN);
		View leave = new View();
		leave.setName("请假");
		String leaveUrl = createMenuUrl(WEBSITE+"/student/leavePage", "snsapi_base");
		leave.setUrl(leaveUrl);
		View getHomework = new View();
		getHomework.setName("查看作业");
		String getHmwUrl = createMenuUrl(WEBSITE+"/student/getHmwPage", "snsapi_base");
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
			url = oAuthPageUrl.replace("APPID", appId).replace("SCOPE", scope).replace("REDIRECT_URI", encoderUrl);
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
			params.put("appid", appId);
			params.put("secret", appSecret);
			params.put("code", code);
			params.put("grant_type", "authorization_code");
			result = httpclient.getMethod(oAuthTokenUrl, params);
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
