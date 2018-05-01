package cc.tinker.restrofitTools;

import com.google.common.base.Strings;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.*;

/**
 * @author Eagle on 2018/3/27.
 */
@Component
public class DownTools {

    @Value("file.download.temp.path")
    private String tempPath;

    private static final String pushUrl = "/tinker/kindle/push/file";

    /**
     * 根据url下载文件
     *
     * @param fileUrl         url
     * @param dstEmailAddress e
     */
    public void downLoadFileByUrl(String fileUrl, String dstEmailAddress) {
        if (Strings.isNullOrEmpty(fileUrl)) {
            return;
        }
        RestrofitTools downloadService = ServiceGenerator.createService(RestrofitTools.class, fileUrl);
        Call<ResponseBody> call = downloadService.downloadFileWithDynamicUrlSync(fileUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                    String absolutePath = writeResponseBodyToDisk(response.body(), tempPath, fileName);
                    RestrofitTools restrofitTools = RetrofitFactory.create(pushUrl, RestrofitTools.class);
                    restrofitTools.pushFileToKindle(absolutePath, dstEmailAddress);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    /**
     * 写磁盘文件
     *
     * @param body         ResponseBody
     * @param tempFilePath 文件路径
     * @param fileName     文件名
     * @return true
     */
    private String writeResponseBodyToDisk(ResponseBody body, String tempFilePath, String fileName) {
        File file = new File(tempFilePath + File.separator + fileName);
        long fileSizeDownloaded = 0;
        byte[] fileReader = new byte[4096];
        try (InputStream inputStream = body.byteStream(); OutputStream outputStream = new FileOutputStream(file)) {
            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
            }
            outputStream.flush();
            return tempFilePath + File.separator + fileName;
        } catch (IOException e) {
            return null;
        }
    }
}
