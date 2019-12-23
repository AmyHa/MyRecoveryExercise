package android.example.myrecoveryexercise;

import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;
import android.widget.ProgressBar;

public interface Contract {

    interface Model {

        interface OnToastLoadFinishedListener {
            void onToastSuccess(ToastContent toastContent);
            void onToastFailed(Throwable t);
        }

        interface OnNotificationFinishedListener {
            void onNotificationSuccess(NotificationContent notificationContent);
            void onNotificationFailed(Throwable t);
        }

        void getToastData(OnToastLoadFinishedListener onToastLoadFinishedListener);
        void getNotificationData(OnNotificationFinishedListener onNotificationFinishedListener);

    }

    interface Presenter {

        void loadToastData();
        void setToastData(ToastContent toastContent);

        void loadNotificationData();
        void setNotificationData(NotificationContent notificationContent);


    }

    interface View {

        void showProgress(ProgressBar progressBar);
        void hideProgress(ProgressBar progressBar);

        void addToastResults();
        void addNotificationResults();

    }
}
