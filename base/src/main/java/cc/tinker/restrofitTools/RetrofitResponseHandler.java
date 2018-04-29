package cc.tinker.restrofitTools;

import retrofit2.Call;
import retrofit2.Response;

public class RetrofitResponseHandler {

    /**
     * 请求处理器
     *
     * @param call
     * @return
     * @throws Exception
     */
    public static <T> T execute(Call<T> call) throws Exception {
        Response<T> response = null;
        response = call.execute();
        T t = response.body();
        return t;
    }

}
