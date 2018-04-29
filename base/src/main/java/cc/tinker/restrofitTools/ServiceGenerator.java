package cc.tinker.restrofitTools;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Eagle on 2018/3/27.
 */
class ServiceGenerator {


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    static <S> S createService(Class<S> serviceClass, String apiBaseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(apiBaseUrl).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
