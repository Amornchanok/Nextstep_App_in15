package com.amornchanok.nextstep_app.studioProfile.tab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.modelProfileStudio.Convenient;
import com.amornchanok.nextstep_app.modelProfileStudio.Studios;

public class InfoStudioFragment extends Fragment {

    private Studios studio;

    TextView tvStdTime,tvStdConvenient;

    public InfoStudioFragment(Studios studio) {
        this.studio = studio;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewData();
    }

    private void initViewData() {
        tvStdTime = getView().findViewById(R.id.tvStdTime);
        tvStdConvenient = getView().findViewById(R.id.tvStdConvenient);

        tvStdTime.setText(studio.getInfo().getTimeService().getOn() + " - " + studio.getInfo().getTimeService().getClose());

        String conven = "";
        for (Convenient item : studio.getInfo().getConvenient()){
            conven += item.getName() + " ";
        }
        tvStdConvenient.setText(conven);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_studio, container, false);
    }
}