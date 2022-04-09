package milo.app.ezlo.test.data;

import milo.app.ezlo.test.data.model.LoggedInUser;
import milo.app.ezlo.test.data.model.ResponseDevice;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class DeviceRepository {

    private static volatile DeviceRepository instance;

    private DeviceDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private ResponseDevice responseDevice = null;

    // private constructor : singleton access
    private DeviceRepository(DeviceDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static DeviceRepository getInstance(DeviceDataSource dataSource) {
        if (instance == null) {
            instance = new DeviceRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return responseDevice != null;
    }

    public void logout() {
        responseDevice = null;
        dataSource.logout();
    }

    private void getResponse(ResponseDevice user) {
        this.responseDevice = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<ResponseDevice> getAllDevices() {
        Result<ResponseDevice> result = dataSource.allDevices();
        if (result instanceof Result.Success) {
            getResponse(((Result.Success<ResponseDevice>) result).getData());
        }
        return result;
    }
}