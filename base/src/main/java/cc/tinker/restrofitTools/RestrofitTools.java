package cc.tinker.restrofitTools;

import cc.tinker.conveyer.JsonResponse;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author Eagle on 2018/3/27.
 */
@Component
public interface RestrofitTools {
    /**
     * option 1: a resource relative to your base URL
     *
     * @return
     */
    @GET("/resource/example.zip")
    Call<ResponseBody> downloadFileWithFixedUrl();

//    @GET("/tinker/kindle/push/http/{url}")
//    Call<JsonResponse> pushToKindle(@Path("url") String url);

    @GET("/tinker/kindle/push/file")
    Call<JsonResponse> pushFileToKindle(@Query("filePath") String absolutePath, @Query("emailAddress") String dstEmailAddress);

    /**
     * using a dynamic URL
     *
     * @param fileUrl fileUrl
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

}
