package com.codecamp.navigationcomponent;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecamp.navigationcomponent.viewmodel.LoginSignupViewModel;

public class StartFragment extends Fragment {
    private LoginSignupViewModel loginSignupViewModel;
    private ConstraintLayout mLogoutButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginSignupViewModel = ViewModelProviders.of(this).get(LoginSignupViewModel.class);
        loginSignupViewModel.getLoggedOutMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoggedOut) {
                if(isLoggedOut){
                    Navigation.findNavController(getView()).navigate(R.id.action_startFragment_to_signupFragment);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        mLogoutButton = view.findViewById(R.id.logoutButton);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSignupViewModel.logout();
            }
        });

        return view;
    }
}