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

public class NotificationFragment extends Fragment implements Contract.Presenter {

    //View uses presenterImp to talk to the Presenter
    private Contract.View presenterImp;

    private NotificationContent notContent;

    // Widgets
    private TextView slugTextView;
    private TextView typeTextView;
    private TextView titleTextView;
    private TextView rateableTextView;
    private TextView textTextView;

    private TextView tabTextView;
    private TextView pushPageTextView;
    private TextView milestoneTextView;
    private TextView localTimeTextView;

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notification_fragment, container, false);

        presenterImp = new Presenter(this);

        // Find all views
        slugTextView = view.findViewById(R.id.not_slug_text_view);
        typeTextView = view.findViewById(R.id.not_type_text_view);
        titleTextView = view.findViewById(R.id.not_title_text_view);
        rateableTextView = view.findViewById(R.id.not_rateable_text_view);
        textTextView = view.findViewById(R.id.not_text_text_view);

        tabTextView = view.findViewById(R.id.not_tab_text_view);
        pushPageTextView = view.findViewById(R.id.not_pushPage_text_view);
        milestoneTextView = view.findViewById(R.id.not_milestone_text_view);
        localTimeTextView = view.findViewById(R.id.not_localtime_text_view);

        progressBar = view.findViewById(R.id.progress_bar_notification);

        loadNotificationData();

        return view;

    }

    @Override
    public void loadToastData() {
        // Left blank intentionally
    }

    @Override
    public void setToastData(ToastContent toastContent) {
        // Left blank intentionally
    }

    @Override
    public void loadNotificationData() {
        presenterImp.addNotificationResults();
        presenterImp.showProgress(progressBar);
    }

    @Override
    public void setNotificationData(NotificationContent notContent) {
        this.notContent = notContent;
        setViews();
    }

    // Once View has received a result from the network request - set all views
    private void setViews() {
        slugTextView.setText(notContent.getSlug());
        typeTextView.setText(notContent.getType());
        titleTextView.setText(notContent.getTitle());
        rateableTextView.setText(String.valueOf(notContent.isRateable()));
        textTextView.setText(notContent.getTrimmedText());
        tabTextView.setText(notContent.getTab());
        pushPageTextView.setText(notContent.getPushPage());
        milestoneTextView.setText(notContent.getMilestone());
        localTimeTextView.setText(notContent.getLocalTime());

        presenterImp.hideProgress(progressBar);
    }
}
