package cc.tinker.service;

import cc.tinker.event.Event;
import cc.tinker.request.Request;
import cc.tinker.response.TextResponse;
import cc.tinker.util.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HandleRequestService {

    public String handleRequest(Map<String, String> map) {
        String response = "success";
        String msgType = map.get("MsgType");
        TextResponse text = new TextResponse(map);
        switch (msgType) {
            case Request.TEXT:
                text.setContent("暂不支持回复文本消息。");
                response = MessageUtil.toXml(text);
                break;
            case Request.IMAGE:
                break;
            case Request.VOICE:
                break;
            case Request.LOCATION:
                break;
            case Request.VIDEO:
                break;
            case Request.SHORTVIDEO:
                break;
            case Request.LINK:
                break;
            case Request.EVENT:
                response = handleEvent(map);
                break;
            default:
                break;
        }

        return response;
    }

    private String handleEvent(Map<String, String> map) {
        String response = "success";
        String eventType = map.get("Event");
        String openId = map.get("FromUserName");
        TextResponse text = new TextResponse(map);
        switch (eventType) {
            case Event.CLICK:
                break;
            case Event.LOCATION:
                break;
            case Event.LOCATION_SELECT:
                break;
            case Event.PIC_PHOTO_OR_ALBUM:
                break;
            case Event.PIC_SYSPHOTO:
                break;
            case Event.PIC_WEIXIN:
                break;
            case Event.SCAN:
                break;
            case Event.SCANCODE_PUSH:
                break;
            case Event.SCANCODE_WAITMSG:
                response = MessageUtil.toXml(text);
                break;
            case Event.SUBSCRIBE:
                text.setContent("欢迎关注 车前半夏当归！");
                response = MessageUtil.toXml(text);
                break;
            case Event.UNSUBSCRIBE:
                text.setContent("再见！");
                response = MessageUtil.toXml(text);
                break;
            case Event.VIEW:
                break;
            default:
                break;
        }
        return response;
    }

}
