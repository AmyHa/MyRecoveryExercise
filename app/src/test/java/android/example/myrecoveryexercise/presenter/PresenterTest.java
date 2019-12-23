package android.example.myrecoveryexercise.presenter;

import android.example.myrecoveryexercise.Contract;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PresenterTest {

    @Mock
    private Presenter presenter;

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

    @Before
    public void setUp() {

        //Make sure a new mock is created for each new test and so all tests are independent
        MockitoAnnotations.initMocks(this);

        presenter = new Presenter(mockContractPresenter);

    }

    // Check whether the Presenter can communicate with the Model i.e. ask Model
    // to provide the data that Presenter wants
    @Test
    public void askModelForData() {

            mockContractModel.getToastData(onToastLoadFinishedListener);
            mockContractModel.getNotificationData(onNotificationFinishedListener);

    }

    // On success of data retrieval, check whether the Presenter
    // can set retrieved data for View to consume
    @Test
    public void setDataForViewOnSuccess(){

        presenter.onToastSuccess(toastContent);

        if(mockContractPresenter != null) {
            mockContractPresenter.setToastData(toastContent);
            mockContractPresenter.setNotificationData(notContent);
        }

    }

    @Test
    public void throwErrorOnFailure() {

        presenter.onToastFailed(new Throwable());

    }
}