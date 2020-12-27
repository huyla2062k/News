package com.laduchuy.news.MyTask;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.laduchuy.news.R;
import com.laduchuy.news.Utils.Detail;
import com.laduchuy.news.databinding.DialogFragmentBinding;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    DialogFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment,container,false);

        Bundle bundle = getArguments();
        binding.tvTemp.setText(bundle.getString(Detail.TEMPC));
        binding.tvMain.setText(bundle.getString(Detail.MAIN));
        binding.tvTitle.setText(bundle.getString(Detail.DES));
        binding.tvWind.setText(bundle.getString(Detail.SPEED));
        binding.tvHumidity.setText(bundle.getString(Detail.HUMIDITY));
        binding.tvVisibility.setText(bundle.getString(Detail.VISIBILITY));
        binding.tvPressure.setText(bundle.getString(Detail.PRESSURE));


        return binding.getRoot();

    }

    public void doNegativeClick() {
        Toast.makeText(getContext(),"Chúc bạn mội ngày tốt lành!!",Toast.LENGTH_SHORT).show();
        Log.i("FragmentAlertDialog", "Negative click!");
    }
}
