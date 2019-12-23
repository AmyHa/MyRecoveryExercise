package android.example.myrecoveryexercise.view;

import android.example.myrecoveryexercise.Contract;
import android.example.myrecoveryexercise.R;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;
import android.example.myrecoveryexercise.presenter.Presenter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ToastFragment extends Fragment implements Contract.Presenter {

    //View uses presenterImp to talk to the Presenter
    private Contract.View presenterImp;

    private ToastContent toastContent;

    // Widgets
    private TextView slugTextView;
    private TextView typeTextView;
    private TextView titleTextView;
    private TextView rateableTextView;
    private TextView textTextView;

    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toast_fragment, container, false);

        presenterImp = new Presenter(this);

        // Find all text views
        slugTextView = view.findViewById(R.id.toast_slug_text_view);
        typeTextView = view.findViewById(R.id.toast_type_text_view);
        titleTextView = view.findViewById(R.id.toast_title_text_view);
        rateableTextView = view.findViewById(R.id.toast_rateable_text_view);
        textTextView = view.findViewById(R.id.toast_text_text_view);

        progressBar = view.findViewById(R.id.progress_bar_toast);

        loadToastData();

        return view;

    }


    @Override
    public void loadToastData() {
        presenterImp.addToastResults();
        presenterImp.showProgress(progressBar);
    }

    @Override
    public void setToastData(ToastContent toastContent) {
        this.toastContent = toastContent;
        setViews();
    }

    @Override
    public void loadNotificationData() {
        // Left blank intentionally
    }

    @Override
    public void setNotificationData(NotificationContent notificationContent) {
        // Left blank intentionally
    }

    // Once View has received a result from the network request - set all views
    private void setViews() {
        slugTextView.setText(toastContent.getSlug());
        typeTextView.setText(toastContent.getType());
        titleTextView.setText(toastContent.getTitle());
        rateableTextView.setText(String.valueOf(toastContent.isRateable()));
        textTextView.setText(toastContent.getText());

        presenterImp.hideProgress(progressBar);
    }

}
