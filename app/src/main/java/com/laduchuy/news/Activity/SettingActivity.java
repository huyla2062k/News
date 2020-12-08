package com.laduchuy.news.Activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.laduchuy.news.R;
import com.laduchuy.news.Utils.Utils;
import com.laduchuy.news.databinding.SettingActivityBinding;


public class SettingActivity extends AppCompatActivity {

    SettingActivityBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.setting_activity);
        binding.toggleNightMode.setChecked(Utils.darkmode);



        binding.toggleNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(SettingActivity.this, "Changed!", Toast.LENGTH_SHORT).show();
                Utils.darkmode = binding.toggleNightMode.isChecked();
            }
        });


        if (binding.rdbSmall.isChecked()) {
            Utils.size = 50;
        }
        else if (binding.rdbMedium.isChecked()) {
            Utils.size = 100;

        }
        else if (binding.rdbBig.isChecked()) {
            Utils.size = 160;
        }
    }
}
