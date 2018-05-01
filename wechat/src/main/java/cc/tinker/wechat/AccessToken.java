package cc.tinker.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cc.tinker.tool.HttpClientImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AccessToken {

	private String accessToken;
	private Date createTime;

	private String url;
	private String appId;
	private String appSecret;

	public AccessToken(String url, String appId, String appSecret) {
		this.url = url;
		this.appId = appId;
		this.appSecret = appSecret;
		refreshToken();
		this.createTime = new Date();
	}

	private void refreshToken() {
		HttpClientImpl httpclient = HttpClientImpl.getInstance();
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "client_credential");
		params.put("appid", appId);
		params.put("secret", appSecret);
		try {
			String result = httpclient.getMethod(url, params);
			JSONObject object = JSON.parseObject(result);
			this.accessToken = (String) object.get("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isExpire() {
		boolean expire = false;
		Date now = new Date();
		long between = (now.getTime() - createTime.getTime()) / 1000;
		if (between > 7150) {
			expire = true;
		}
		return expire;
	}

	public synchronized String getAccessToken() {
		if (isExpire()) {
			refreshToken();
		}
		return accessToken;
	}

}
