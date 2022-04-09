package milo.app.ezlo.test.ui.devices;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import milo.app.ezlo.test.data.DeviceRepository;
import milo.app.ezlo.test.data.Result;
import milo.app.ezlo.test.data.model.LoggedInUser;
import milo.app.ezlo.test.R;
import milo.app.ezlo.test.data.model.ResponseDevice;

public class DevicesViewModel extends ViewModel {

    private MutableLiveData<DevicesFormState> deviceFormState = new MutableLiveData<>();
    private MutableLiveData<ResponseDevice> deviceResult = new MutableLiveData<>();
    private DeviceRepository deviceRepository;

    DevicesViewModel(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public LiveData<DevicesFormState> getDevicesFormState() {
        return deviceFormState;
    }

    public LiveData<ResponseDevice> getDevicesResult() {
        return deviceResult;
    }

    public void FetchDevices() {
        // can be launched in a separate asynchronous job
        Result<ResponseDevice> result = deviceRepository.getAllDevices();

        if (result instanceof Result.Success) {
            ResponseDevice data = ((Result.Success<ResponseDevice>) result).getData();
            deviceResult.setValue(data);
        } else {
            deviceResult.setValue(new ResponseDevice());
        }
    }

    public void DevicesDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            deviceFormState.setValue(new DevicesFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            deviceFormState.setValue(new DevicesFormState(null, R.string.invalid_password));
        } else {
            deviceFormState.setValue(new DevicesFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}