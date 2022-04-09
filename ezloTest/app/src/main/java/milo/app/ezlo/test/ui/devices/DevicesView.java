package milo.app.ezlo.test.ui.devices;

/**
 * Class exposing authenticated user details to the UI.
 */
class DevicesView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    DevicesView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}