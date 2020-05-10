package providers;

import api.ApiService;
import api.RetrofitSingleton;
import retrofit2.Retrofit;

public class ApiServiceProvider {
    private static ApiService apiService;
    public static ApiService provideApiService(){
        if (apiService==null){
            apiService= RetrofitSingleton.getInstance().create(ApiService.class);
        }
        return apiService;
    }
}
