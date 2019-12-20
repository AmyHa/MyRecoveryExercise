package android.example.myrecoveryexercise.presenter;

import android.example.myrecoveryexercise.Contract;
import android.example.myrecoveryexercise.model.Repository;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;
import android.util.Log;

public class Presenter implements Contract.View, Contract.Model.OnToastLoadFinishedListener,
        Contract.Model.OnNotificationFinishedListener {

    private static final String TAG = "Presenter";

    private Contract.Presenter presenter;
    private Contract.Model model;

    public Presenter(final Contract.Presenter presenter) {
        this.presenter = presenter;
        this.model = new Repository();
    }

    @Override
    public void onToastSuccess(ToastContent toastContent) {
        if(presenter != null) {
            presenter.setToastData(toastContent);
        }
    }

    @Override
    public void onToastFailed(Throwable t) {
        Log.e(TAG, "onToastFailed: " + t);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void addToastResults() {
        if(presenter != null) {
            model.getToastData(this);
        }
    }

    @Override
    public void addNotificationResults() {
        if(presenter != null) {
            model.getNotificationData(this);
        }
    }

    @Override
    public void onNotificationSuccess(NotificationContent notificationContent) {
        if(presenter != null) {
            presenter.setNotificationData(notificationContent);
        }
    }

    @Override
    public void onNotificationFailed(Throwable t) {
        Log.e(TAG, "onNotificationFailed: " + t);
    }
}
