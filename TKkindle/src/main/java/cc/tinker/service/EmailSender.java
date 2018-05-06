package cc.tinker.service;

import cc.tinker.restrofitTools.DownTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author Tinker
 * @date 3/26/2018
 */
@Service
public class EmailSender {
    private final static String srcEmailAddress = "whoszus@126.com";
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private DownTools downTools;
    Logger logger  = LoggerFactory.getLogger(EmailSender.class);

    /**
     * 发送带附件的邮件，fileMap 传null则不发附件；
     *
     * @param dstEmailAddress 接受地址；
     * @param subject         主题
     * @param text            内容
     * @param fileMap<fileName,absPath></>         文件 key:文件名 value：文件地址；
     * @throws Exception
     */
    public void sendLocalFile(String dstEmailAddress, String subject, String text, Map<String, String> fileMap) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(srcEmailAddress);
            mimeMessageHelper.setTo(dstEmailAddress);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            if (fileMap != null) {
                for (Map.Entry<String, String> fileEntry : fileMap.entrySet()) {
                    FileSystemResource file = new FileSystemResource(new File(fileEntry.getValue()));
                    mimeMessageHelper.addAttachment(fileEntry.getKey(), file);
                }
            }
            mailSender.send(mimeMessage);
            logger.info("推送文件成功",dstEmailAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendMailByFileUrl(String dstEmailAddress, String subject, String text, String httpFileUrl) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(srcEmailAddress);
            mimeMessageHelper.setTo(dstEmailAddress);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            downTools.downLoadFileByUrl(httpFileUrl, dstEmailAddress);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
