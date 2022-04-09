package milo.app.ezlo.test.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseDevice {

    @SerializedName("Devices")
    @Expose
    private ArrayList<Devices> devices = null;

    public ArrayList<Devices> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Devices> devices) {
        this.devices = devices;
    }
}