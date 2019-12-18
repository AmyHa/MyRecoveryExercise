package android.example.myrecoveryexercise.model;

import android.example.myrecoveryexercise.Contract;
import android.example.myrecoveryexercise.model.objects.BaseContent;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// the Model implements the Contract.Model interface, and this class interacts with the Presenter
// to provide Toast data

public class Repository implements Contract.Model {

    private static final String TAG = "Repository";

    private static Repository repository;
    private Service service;
    private final String URL_BASE = "https://private-4882c-mobiledeveloperexercise.apiary-mock.com/";

    private ToastContent toastContent = new ToastContent();
    private NotificationContent notificationContent = new NotificationContent();

    public Repository() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Service.class);


    }

    @Override
    public void getToastData(final OnToastLoadFinishedListener onToastLoadFinishedListener) {

        service.getToastResponse().enqueue(new Callback<List<ToastContent>>() {
            @Override
            public void onResponse(Call<List<ToastContent>> call, Response<List<ToastContent>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponseToast: success!");
                    toastContent = response.body().get(0);
                    onToastLoadFinishedListener.onToastSuccess(toastContent);
                }
            }

            @Override
            public void onFailure(Call<List<ToastContent>> call, Throwable t) {
                Log.d(TAG, "onFailureToast: failure");
                onToastLoadFinishedListener.onToastFailed(t);
            }

        });

    }

    @Override
    public void getNotificationData(final OnNotificationFinishedListener onNotificationFinishedListener) {

        service.getNotificationResponse().enqueue(new Callback<List<NotificationContent>>() {
            @Override
            public void onResponse(Call<List<NotificationContent>> call, Response<List<NotificationContent>> response) {
                Log.d(TAG, "onResponseNotification: success!");
                notificationContent = response.body().get(0);
                onNotificationFinishedListener.onNotificationSuccess(notificationContent);
            }

            @Override
            public void onFailure(Call<List<NotificationContent>> call, Throwable t) {
                Log.d(TAG, "onFailureNotification: failure");
                onNotificationFinishedListener.onNotificationFailed(t);

            }
        });
    }

}
