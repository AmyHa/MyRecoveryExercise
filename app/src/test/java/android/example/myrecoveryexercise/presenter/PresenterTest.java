package android.example.myrecoveryexercise.presenter;

import android.example.myrecoveryexercise.Contract;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {

    @Mock
    private Contract.Presenter mockContractPresenter;
    @Mock
    private Contract.Model mockContractModel;

    @Mock
    private ToastContent toastContent;

    @Mock
    private NotificationContent notContent;

    @Mock
    private Contract.Model.OnToastLoadFinishedListener onToastLoadFinishedListener;

    @Mock
    private Contract.Model.OnNotificationFinishedListener onNotificationFinishedListener;

    private Presenter presenter;

    private Throwable t;
    private Log log;
    private static final String toastFailure = "onToastFailed: ";
    private static final String notFailure = "onNotificationFailed: ";

    @Before
    public void setUp() {
        presenter = new Presenter(mockContractPresenter);

        // Dummy data (this data would be fetched from the web in production)
        toastContent.setSlug("slug");
        toastContent.setRateable(true);
        toastContent.setText("text");
        toastContent.setTitle("title");
        toastContent.setType("type");

        notContent.setSlug("slug");
        notContent.setRateable(true);
        notContent.setText("text");
        notContent.setTitle("title");
        notContent.setType("type");

    }

    // Check whether the Presenter can talk to the Model i.e. ask Model
    // to provide the data Presenter wants
    @Test
    public void askModelForData() {

            mockContractModel.getToastData(onToastLoadFinishedListener);
            mockContractModel.getNotificationData(onNotificationFinishedListener);

    }

    // On success of data retrieval, check whether the Presenter
    // can set retrieved data for View to consume
    @Test
    public void onSuccessSetDataForView(){

        if(mockContractPresenter != null) {
            mockContractPresenter.setToastData(toastContent);
            mockContractPresenter.setNotificationData(notContent);
        }

    }

    // We need to somehow test what happens when retrieval of data fails

}