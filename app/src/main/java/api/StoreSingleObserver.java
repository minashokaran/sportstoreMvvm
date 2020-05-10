package api;

import com.example.spstore.BaseActivity;

import exception.ExceptionMessageFactory;
import io.reactivex.SingleObserver;
import retrofit2.HttpException;

public abstract class StoreSingleObserver<T> implements SingleObserver<T> {
    public BaseActivity baseActivity;

    public StoreSingleObserver(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onError(Throwable e) {
        baseActivity.showSnackBarMessage(ExceptionMessageFactory.getMessage(e));
        if (e instanceof HttpException) {

        }
    }
}