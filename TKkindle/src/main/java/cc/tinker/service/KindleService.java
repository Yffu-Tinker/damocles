package cc.tinker.service;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class KindleService {

    @Autowired
    private EmailSender emailSender;

    /**
     * 推送整个文件夹下所有文件
     *
     * @param kindleEmail   email地址
     * @param directoryPath 文件夹
     * @return
     */
    public Boolean pushAllFile(String kindleEmail, String directoryPath) {
        File file = new File(directoryPath);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] fileList = file.listFiles();
        if (fileList == null) {
            return false;
        }
        Map<String, String> map = Maps.newHashMap();
        for (File file1 : fileList) {
            String fileName = file1.getName();
            String absolutePath = file1.getAbsolutePath();
            map.put(fileName, absolutePath);
        }
        emailSender.sendLocalFile(kindleEmail, "Tinker推送", "文件夹整个推送", map);
        return true;
    }
}
