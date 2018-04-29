package cc.tinker.restrofitTools;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RetrofitFactory {

    private static final Logger logger = LoggerFactory.getLogger(RetrofitFactory.class);

    private static Map<RetrofitKey, Object> products = new HashMap<>();

    private static OkHttpClient client = okHttpClient();

    private final static int CONNECT_TIMEOUT = 30;
    private final static int READ_TIMEOUT = 30;
    private final static int WRITE_TIMEOUT = 30;

    @SuppressWarnings("unchecked")
    public static <T> T create(String url, Class<T> interfaces) {
        RetrofitKey key = new RetrofitKey(url, interfaces);
        synchronized (logger) {
            Object value = products.get(key);
            if (value == null) {
                value = retrofit(url).create(interfaces);
                products.put(key, value);
            }
            logger.info(url);
            return (T) value;
        }
    }

    private static OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.NONE));
        return builder.build();
    }

    private static Retrofit retrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
        return retrofit;
    }

    @SuppressWarnings("unused")
    private static class RetrofitKey {

        private String url;
        private Class<?> type;

        public RetrofitKey() {
        }

        public RetrofitKey(String url, Class<?> type) {
            this.url = url;
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Class<?> getType() {
            return type;
        }

        public void setType(Class<?> type) {
            this.type = type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.url, this.type);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null) {
                return false;
            }
            if (getClass() != other.getClass()) {
                return false;
            }
            RetrofitKey o = (RetrofitKey) other;
            return Objects.equals(this.url, o.getUrl()) && Objects.equals(this.type, o.getType());
        }

    }

}
