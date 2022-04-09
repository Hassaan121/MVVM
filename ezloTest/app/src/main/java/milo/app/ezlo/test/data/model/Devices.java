package milo.app.ezlo.test.data.model;

import android.bluetooth.BluetoothClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Devices {
    @SerializedName("PK_Device")
    @Expose
    private Integer pKDevice;
    @SerializedName("MacAddress")
    @Expose
    private String macAddress;
    @SerializedName("PK_DeviceType")
    @Expose
    private Integer pKDeviceType;
    @SerializedName("PK_DeviceSubType")
    @Expose
    private Integer pKDeviceSubType;
    @SerializedName("Firmware")
    @Expose
    private String firmware;
    @SerializedName("Server_Device")
    @Expose
    private String serverDevice;
    @SerializedName("Server_Event")
    @Expose
    private String serverEvent;
    @SerializedName("Server_Account")
    @Expose
    private String serverAccount;
    @SerializedName("InternalIP")
    @Expose
    private String internalIP;
    @SerializedName("LastAliveReported")
    @Expose
    private String lastAliveReported;
    @SerializedName("Platform")
    @Expose
    private String platform;
    @SerializedName("PK_Account")
    @Expose
    private Integer pKAccount;
    public Integer getPKDevice() {
        return pKDevice;
    }

    public void setPKDevice(Integer pKDevice) {
        this.pKDevice = pKDevice;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getPKDeviceType() {
        return pKDeviceType;
    }

    public void setPKDeviceType(Integer pKDeviceType) {
        this.pKDeviceType = pKDeviceType;
    }

    public Integer getPKDeviceSubType() {
        return pKDeviceSubType;
    }

    public void setPKDeviceSubType(Integer pKDeviceSubType) {
        this.pKDeviceSubType = pKDeviceSubType;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getServerDevice() {
        return serverDevice;
    }

    public void setServerDevice(String serverDevice) {
        this.serverDevice = serverDevice;
    }

    public String getServerEvent() {
        return serverEvent;
    }

    public void setServerEvent(String serverEvent) {
        this.serverEvent = serverEvent;
    }

    public String getServerAccount() {
        return serverAccount;
    }

    public void setServerAccount(String serverAccount) {
        this.serverAccount = serverAccount;
    }

    public String getInternalIP() {
        return internalIP;
    }

    public void setInternalIP(String internalIP) {
        this.internalIP = internalIP;
    }

    public String getLastAliveReported() {
        return lastAliveReported;
    }

    public void setLastAliveReported(String lastAliveReported) {
        this.lastAliveReported = lastAliveReported;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getPKAccount() {
        return pKAccount;
    }

    public void setPKAccount(Integer pKAccount) {
        this.pKAccount = pKAccount;
    }
/*
{
  "Devices": [

    {
      "PK_Device": 45013855,
      "MacAddress": "e0:60:66:02:e2:1b",
      "PK_DeviceType": 1,
      "PK_DeviceSubType": 2,
	  "Firmware" : "1.7.455",
      "Server_Device": "vera-us-oem-device12.mios.com",
      "Server_Event": "vera-us-oem-event12.mios.com",
      "Server_Account": "vera-us-oem-account12.mios.com",
      "InternalIP": "192.168.6.213",
      "LastAliveReported": "2018-02-20 04:17:43",
      "Platform": "Sercomm NA301"
    },
    {
      "PK_Device": 50100057,
      "MacAddress": "94:4a:0c:25:63:c4",
      "PK_DeviceType": 1,
      "PK_DeviceSubType": 2,
      "Server_Device": "vera-us-oem-device12.mios.com",
      "Server_Event": "vera-us-oem-event12.mios.com",
      "PK_Account": 817541,
	  "Firmware" : "1.7.6255",
      "Server_Account": "vera-us-oem-account12.mios.com",
      "InternalIP": "192.168.10.6",
      "LastAliveReported": "2018-02-17 20:21:49",
      "Platform": "Sercomm G450"
    },
    {
      "PK_Device": 50100070,
      "MacAddress": "94:4a:0c:25:64:05",
      "PK_DeviceType": 1,
      "PK_DeviceSubType": 2,
      "Server_Device": "vera-us-oem-device11.mios.com",
      "Server_Event": "vera-us-oem-event11.mios.com",
      "PK_Account": 328401,
	  "Firmware" : "1.7.6255",
      "Server_Account": "vera-us-oem-account11.mios.com",
      "InternalIP": "172.17.22.101",
      "LastAliveReported": "2018-02-20 13:02:43",
      "Platform": "Sercomm G450"
    },
    {
      "PK_Device": 50100072,
      "MacAddress": "94:4a:0c:25:64:0f",
      "PK_DeviceType": 1,
      "PK_DeviceSubType": 2,
      "Server_Device": "vera-us-oem-device11.mios.com",
      "Server_Event": "vera-us-oem-event12.mios.com",
      "PK_Account": 643371,
	  "Firmware" : "1.7.6255",
      "Server_Account": "vera-us-oem-account12.mios.com",
      "InternalIP": "192.168.100.102",
      "LastAliveReported": "2018-02-20 10:41:48",
      "Platform": "Sercomm G450"
    },
    {
      "PK_Device": 50100200,
      "MacAddress": "94:4a:0c:25:64:1e",
      "PK_DeviceType": 1,
      "PK_DeviceSubType": 2,
	  "Firmware" : "1.7.6255",
      "Server_Device": "vera-us-oem-device11.mios.com",
      "Server_Event": "vera-us-oem-event11.mios.com",
      "Server_Account": "vera-us-oem-account11.mios.com",
      "InternalIP": "172.17.15.10",
      "LastAliveReported": "2018-02-12 10:17:41",
      "Platform": "Sercomm G450"
    }
  ]
  }
*/
}

