package milo.app.ezlo.test.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import milo.app.ezlo.test.R;
import milo.app.ezlo.test.data.model.ResponseDevice;
import milo.app.ezlo.test.databinding.ActivityListBinding;
import milo.app.ezlo.test.ui.adapters.RecyclerViewAdapter;
import milo.app.ezlo.test.ui.devices.DeviceViewModelFactory;
import milo.app.ezlo.test.ui.devices.DevicesViewModel;

public class ListActivity extends AppCompatActivity {
    private DevicesViewModel devicesViewModel;
    ActivityListBinding binding;
    public RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.response = new Gson().fromJson(Constants.json, ResponseDevice.class);
        devicesViewModel = new ViewModelProvider(this, new DeviceViewModelFactory())
                .get(DevicesViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
//        setContentView(binding.getRoot());
        binding.setDevicesViewModel(devicesViewModel);
//        myListViewModel= ViewModelProviders.of(this).get(MyListViewModel.class);
        devicesViewModel.getDevicesResult().observe(this, new Observer<ResponseDevice>() {
            @Override
            public void onChanged(@Nullable ResponseDevice deviceResult) {
                if (deviceResult == null) {
                    return;
                }
                if (deviceResult.getDevices().size() > 0) {
                    updateUiWithUser(deviceResult);
                } else {
                    showLoginFailed();
                }
              //    devicesViewModel.getDevicesResult().removeObserver(this);
                //setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                //   finish();
            }
        });
        devicesViewModel.FetchDevices();


    }

    @Override
    protected void onResume() {
        super.onResume();
        devicesViewModel.FetchDevices();
    }

    private void updateUiWithUser(ResponseDevice model) {
        String welcome = getString(R.string.welcome) + model.getDevices().get(0).getFirmware() +
                "\n" + model.toString();
        // TODO : initiate successful logged in experience
        Log.d("responsedevice", model.getDevices().get(0).getFirmware()+"");
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        adapter = new RecyclerViewAdapter(model.getDevices(), ListActivity.this);
        binding.list.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        binding.list.setAdapter(adapter);

    }

    private void showLoginFailed() {
        Toast.makeText(getApplicationContext(), "error String", Toast.LENGTH_SHORT).show();
    }
}