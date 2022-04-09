package milo.app.ezlo.test.data;

import android.util.Log;

import com.google.gson.Gson;

import milo.app.ezlo.test.data.model.LoggedInUser;
import milo.app.ezlo.test.data.model.ResponseDevice;
import milo.app.ezlo.test.ui.activity.Constants;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class DeviceDataSource {

    public Result<ResponseDevice> allDevices() {

        try {
            // TODO: handle loggedInUser authentication
            ResponseDevice response = Constants.response;
            return new Result.Success<>(response);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}