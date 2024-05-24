package com.vishal.weblinktoapk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;







public class MainFragment extends Fragment {

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        EditText editTextUrl = view.findViewById(R.id.editTextUrl);
        Button buttonGenerate = view.findViewById(R.id.buttonGenerate);
        TextView textViewError = view.findViewById(R.id.textViewError); // Add this line

        buttonGenerate.setOnClickListener(v -> {
            String url = editTextUrl.getText().toString().trim();
            if (url.isEmpty()) {
                // If input is empty, show error message
                textViewError.setVisibility(View.VISIBLE);
                return;
            } else {
                // If input is not empty, hide error message
                textViewError.setVisibility(View.GONE);
            }

            ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
            PreviewFragment previewFragment = (PreviewFragment) ((BaseActivity.MyPagerAdapter) viewPager.getAdapter()).getItem(1);
            previewFragment.loadUrl(url);
            viewPager.setCurrentItem(1);


        });

             return view;
    }
}
