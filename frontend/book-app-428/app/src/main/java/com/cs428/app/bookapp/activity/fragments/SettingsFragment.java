package com.cs428.app.bookapp.activity.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs428.app.bookapp.R;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2018.
 */

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(Serializable presenter) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRESENTER", presenter);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);


        // Inflate the layout for this fragment
        return null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
