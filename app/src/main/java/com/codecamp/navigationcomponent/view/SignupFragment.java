package com.codecamp.navigationcomponent.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.codecamp.navigationcomponent.R;
import com.codecamp.navigationcomponent.viewmodel.LoginSignupViewModel;
import com.google.firebase.auth.FirebaseUser;

public class SignupFragment extends Fragment {
    private EditText mEmailEditText,mPasswordEditText;
    private ConstraintLayout mSignupButton;
    private LoginSignupViewModel loginSignupViewModel;
    private NavController mNavigation;
    private Context context;
    private Application application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mNavigation = Navigation.findNavController((Activity) application.getApplicationContext(),R.id.action_signupFragment_to_loginFragment);
        loginSignupViewModel = ViewModelProviders.of(this).get(LoginSignupViewModel.class);
        loginSignupViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    Navigation.findNavController(getView()).navigate(R.id.action_signupFragment_to_loginFragment);
                }else{
                    Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        mEmailEditText = view.findViewById(R.id.email);
        mPasswordEditText = view.findViewById(R.id.password);
        mSignupButton = view.findViewById(R.id.signupButton);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSignupViewModel.signup(mEmailEditText.getText().toString(),mPasswordEditText.getText().toString());
            }
        });

        return view;
    }
}