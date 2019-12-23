package android.example.myrecoveryexercise.presenter;

import android.example.myrecoveryexercise.Contract;
import android.example.myrecoveryexercise.model.Repository;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;
import android.view.View;
import android.widget.ProgressBar;

import static android.view.View.GONE;

public class Presenter implements Contract.View, Contract.Model.OnToastLoadFinishedListener,
        Contract.Model.OnNotificationFinishedListener {

    private static final String TAG = "Presenter";

    private Contract.Presenter presenter;
    private Contract.Model model;

    public Presenter(final Contract.Presenter presenter) {
        this.presenter = presenter;
        this.model = new Repository();
    }

    // View (fragment) will be able to use showProgress() and hideProgress() methods
    // to display progress bar.
    @Override
    public void showProgress(ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress(ProgressBar progressBar) {

        progressBar.setVisibility(GONE);

    }

    // Presenter can ask the Model to fetch data
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

    // On successful data retrieval or failure to do so, the Presenter can pass the
    // the data / error message back to the View
    @Override
    public void onToastSuccess(ToastContent toastContent) {
        if(presenter != null) {
            presenter.setToastData(toastContent);
        }
    }

    @Override
    public void onToastFailed(Throwable t) {
        try {
            throw t;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
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
        try {
            throw t;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
