package exception;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.HttpException;

public class ExceptionMessageFactory {
    private ExceptionMessageFactory(){}

    public static String getMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            switch (((HttpException) throwable).code()) {
                case 422:
                    Gson gson = new Gson();
                    //1:13
                    try {
                        StoreHttpException exception = gson.fromJson(((HttpException) throwable)
                                        .response().errorBody().string()
                                , StoreHttpException.class);
                        return exception.getMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    return "اختلال در دریافت اطلاعات";
            }
        }
        return "خطای نامشخص";
    }

}
