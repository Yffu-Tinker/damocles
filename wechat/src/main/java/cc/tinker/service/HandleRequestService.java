package cc.tinker.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cc.tinker.entity.Relation;
import cc.tinker.entity.Sign;
import cc.tinker.entity.Student;
import cc.tinker.event.ClickEvent;
import cc.tinker.event.Event;
import cc.tinker.event.LocationEvent;
import cc.tinker.event.ScanCodeEvent;
import cc.tinker.menu.UsingButton;
import cc.tinker.request.Request;
import cc.tinker.response.TextResponse;
import cc.tinker.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class HandleRequestService {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private SignService signService;

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

    public String handleEvent(Map<String, String> map) {
        String response = "success";
        String eventType = map.get("Event");
        String openId = map.get("FromUserName");
        Student student = studentService.findWithBinding(openId, 1);
        TextResponse text = new TextResponse(map);
        switch (eventType) {
            case Event.CLICK:
                ClickEvent clickEvent = new ClickEvent(map);
                if (clickEvent.getEventKey().equals(UsingButton.SIGN)) {
                    studentService.updateBinding(false, openId);
                    text.setContent("解绑成功");
                } else if (clickEvent.getEventKey().equals(UsingButton.HELP)) {
                    text.setContent("帮助");
                }
                response = MessageUtil.toXml(text);
                break;
            case Event.LOCATION:
                LocationEvent locationEvent = new LocationEvent(map);
                if (student != null) {
                    Double longitude = locationEvent.getLongitude();
                    Double latitude = locationEvent.getLatitude();
                    studentService.updateLocation(longitude, latitude, openId);
                }
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
                ScanCodeEvent code = new ScanCodeEvent(map);
                String codeInfo = code.getScanResult();
                JSONObject object = JSON.parseObject(codeInfo);
                Integer courseId = object.getInteger("id");
                Integer expire = object.getInteger("expire");
                Long signTime = object.getLong("date");
                if (student != null) {
                    Integer studentId = student.getId();
                    Relation relation = relationService.findByCourseIdAndStudentId(courseId, studentId);
                    if (relation == null) {
                        relation = new Relation();
                        relation.setCourseId(courseId);
                        relation.setStudentId(studentId);
                        relationService.save(relation);
                    }
                    Date now = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date past = null;
                    try {
                        past = format.parse(format.format(now));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Sign sign = signService.findTodaySign(courseId, studentId, past);
                    Double longitude = student.getLongitude();
                    Double latitude = student.getLatitude();
                    if (sign == null) {
                        sign = new Sign();
                        sign.setCourseId(courseId);
                        sign.setStudentId(studentId);
                        sign.setTime(now);
                        sign.setLongitude(longitude);
                        sign.setLatitude(latitude);
                        if (signTime + expire < now.getTime()) {
                            sign.setResult("签到");
                            text.setContent("签到成功！");
                        } else {
                            sign.setResult("迟到");
                            text.setContent("你已迟到！");
                        }
                        signService.save(sign);
                    } else {
                        if (sign.getLatitude() == null || sign.getLongitude() == null) {
                            Long signId = sign.getId();
                            if (signTime + expire < now.getTime()) {
                                signService.updateSign(longitude, latitude, now, "签到", signId);
                                text.setContent("签到成功！");
                            } else {
                                signService.updateSign(longitude, latitude, now, "迟到", signId);
                                text.setContent("你已迟到！");
                            }
                        } else {
                            text.setContent("你已经签到过了！");
                        }
                    }
                } else {
                    text.setContent("请先绑定学生信息！");
                }
                response = MessageUtil.toXml(text);
                break;
            case Event.SUBSCRIBE:
                text.setContent("欢迎关注趣课微信公主号！");
                response = MessageUtil.toXml(text);
                break;
            case Event.UNSUBSCRIBE:
                break;
            case Event.VIEW:
                break;
            default:
                break;
        }
        return response;
    }

}
