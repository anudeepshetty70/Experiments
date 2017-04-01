package com.assignment.first.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.first.R;
import com.assignment.first.utils.AppConstants;

public class FirstFragment extends Fragment implements AppConstants {

    private View view;
    private TextView txtFragmentBody;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.inflater = inflater;

        initUI();

        setListeners();

        return view;
    }

    private void initUI() {

        view = inflater.inflate(R.layout.layout_viewpager_child, null);

        txtFragmentBody = (TextView) view.findViewById(R.id.txt_fragment_body);

        txtFragmentBody.setText(getActivity().getString(R.string.fragment1));
    }

    private void setListeners() {

        view.setOnClickListener(new FragmentOnClickListener());
    }

    private class FragmentOnClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            Intent intent = new Intent(FragmentClickBroadCast);
            getActivity().sendBroadcast(intent);

        }
    }
}
