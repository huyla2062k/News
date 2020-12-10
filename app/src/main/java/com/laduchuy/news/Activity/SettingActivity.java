package com.laduchuy.news.Activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
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
        binding.wvTest.loadData("<html><head><style>img{display: inline; height: auto; max-width: 100%;} </style></head><body>" + "Abc" + "</body></html>", "text/html", "UTF-8");
        binding.wvTest.getSettings().setTextZoom(Utils.size);
        binding.sbSize.setProgress(Utils.size);

        binding.sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChangedValue = i;
                binding.wvTest.getSettings().setTextZoom(progressChangedValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Utils.size = progressChangedValue;

            }
        });
    }
}
