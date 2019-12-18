package android.example.myrecoveryexercise.model;

import android.example.myrecoveryexercise.model.objects.BaseContent;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("toast")
    Call<List<ToastContent>> getToastResponse();

    @GET("notification")
    Call<List<NotificationContent>> getNotificationResponse();

}
