package cc.tinker.controller;

import cc.tinker.service.HandleRequestService;
import cc.tinker.util.MessageUtil;
import cc.tinker.wechat.Validate;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author Eagle
 */
@RestController
public class WechatController {

    @Autowired
    private HandleRequestService requestService;

    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public String validate(String signature, String timestamp, String nonce, String echostr) {
        String success = "";
        if (Strings.isBlank(signature) || Strings.isBlank(timestamp) || Strings.isBlank(nonce)) {
            return success;
        }
        if (Validate.validateSignature(signature, timestamp, nonce)) {
            success = echostr;
        }
        return success;
    }

    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public String service(HttpServletRequest request) {
        Map<String, String> map = MessageUtil.parseXml(request);
        return requestService.handleRequest(map);
    }

}