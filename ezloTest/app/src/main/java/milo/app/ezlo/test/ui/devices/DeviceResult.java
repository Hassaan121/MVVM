package milo.app.ezlo.test.ui.devices;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class DeviceResult {
    @Nullable
    private DevicesView success;
    @Nullable
    private Integer error;

    DeviceResult(@Nullable Integer error) {
        this.error = error;
    }

    DeviceResult(@Nullable DevicesView success) {
        this.success = success;
    }

    @Nullable
    DevicesView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}