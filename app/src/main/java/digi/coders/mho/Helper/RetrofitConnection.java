package digi.coders.mho.Helper;

import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    public static Retrofit retrofit;

    public static Retrofit getRetrofitConnection() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Constant.BASEURL).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        }
        return retrofit;
    }
}
