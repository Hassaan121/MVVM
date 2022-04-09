package milo.app.ezlo.test.ui.activity;


import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import milo.app.ezlo.test.R;
import milo.app.ezlo.test.data.model.Devices;
import milo.app.ezlo.test.data.model.ResponseDevice;
import milo.app.ezlo.test.databinding.DeviceBinding;
import milo.app.ezlo.test.ui.devices.DeviceViewModelFactory;
import milo.app.ezlo.test.ui.devices.DevicesViewModel;

//https://uniqueandrocode.com/mvvm-databinding-recyclerview-using-retrofit/
public class LoginActivity extends AppCompatActivity {

    private DevicesViewModel devicesViewModel;
    private DeviceBinding binding;
    ProgressBar loadingProgressBar;
    int index;
    Devices original;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setContentView(binding.getRoot());
        original = Constants.response.getDevices().get(index);
        binding.setDevice(original);

        devicesViewModel = new ViewModelProvider(this, new DeviceViewModelFactory()).get(DevicesViewModel.class);

        loadingProgressBar = binding.loading;
        if (binding.getDevice().getPlatform() == null) {
            binding.image.setImageDrawable(getDrawable(R.drawable.vera_edge_big));
        } else if (binding.getDevice().getPlatform().equals("Sercomm G450")) {
            binding.image.setImageDrawable(getDrawable(R.drawable.vera_plus_big));
        } else if (binding.getDevice().getPlatform().equals("Sercomm G550")) {
            binding.image.setImageDrawable(getDrawable(R.drawable.vera_secure_big));
        } else {
            binding.image.setImageDrawable(getDrawable(R.drawable.vera_edge_big));
        }



/*        devicesViewModel.getDevicesFormState().observe(this, new Observer<DevicesFormState>() {
            @Override
            public void onChanged(@Nullable DevicesFormState devicesFormState) {
                if (devicesFormState == null) {
                    return;
                }
                loginButton.setEnabled(devicesFormState.isDataValid());
                if (devicesFormState.getUsernameError() != null) {
                    binding.sn.setError(getString(devicesFormState.getUsernameError()));
                }
                if (devicesFormState.getPasswordError() != null) {
                    binding.mac.setError(getString(devicesFormState.getPasswordError()));
                }
            }
        });
*/


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (original.getMacAddress().equals(binding.mac.getText().toString())) {
                    binding.save.setEnabled(false);
                } else if (original.getMacAddress().equals(binding.sn.getText().toString())) {
                    binding.save.setEnabled(false);
                } else if (original.getMacAddress().equals(binding.firm.getText().toString())) {
                    binding.save.setEnabled(false);
                } else if (original.getMacAddress().equals(binding.model.getText().toString())) {
                    binding.save.setEnabled(false);
                } else {
                    binding.save.setEnabled(true);
                }
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
//                devicesViewModel.DevicesDataChanged(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        };
        binding.save.setEnabled(false);
        binding.sn.addTextChangedListener(afterTextChangedListener);
        binding.mac.addTextChangedListener(afterTextChangedListener);
        binding.firm.addTextChangedListener(afterTextChangedListener);
        binding.model.addTextChangedListener(afterTextChangedListener);
        binding.firm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                }
                return false;
            }
        });
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    private void Save() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setIcon(binding.image.getDrawable());
        alertDialog.setMessage("Do You Want To " +
                "Save New Values " +
                "\n" + binding.getDevice().getPlatform());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Saving();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void Saving() {
        binding.getDevice().setMacAddress(binding.mac.getText().toString());
        binding.getDevice().setServerDevice(binding.model.getText().toString());
        binding.getDevice().setServerAccount(binding.sn.getText().toString());
        binding.getDevice().setFirmware(binding.firm.getText().toString());
        binding.setDevice(binding.getDevice());
        Log.d("responsedevice2", binding.getDevice().getMacAddress() + "");
        Constants.response.getDevices().set(index, binding.getDevice());
        Log.d("responsedevice2", Constants.response.getDevices().get(index).getMacAddress() + "");
        devicesViewModel.FetchDevices();
        finish();

    }

    private void updateUiWithUser(ResponseDevice model) {
        String welcome = getString(R.string.welcome) + model.getDevices().get(0).getFirmware() +
                "\n" + model.toString();
        // TODO : initiate successful logged in experience
        loadingProgressBar.setVisibility(View.GONE);
        Log.d("responsedevice", model.toString());
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed() {
        loadingProgressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "error String", Toast.LENGTH_SHORT).show();
    }
}