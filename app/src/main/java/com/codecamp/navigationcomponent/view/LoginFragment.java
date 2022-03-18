package com.codecamp.navigationcomponent.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.codecamp.navigationcomponent.R;
import com.codecamp.navigationcomponent.viewmodel.LoginSignupViewModel;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    private EditText mEmailEditText,mPasswordEditText;
    private ConstraintLayout mLoginButtton;
    private LoginSignupViewModel loginSignupViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginSignupViewModel = ViewModelProviders.of(this).get(LoginSignupViewModel.class);
        loginSignupViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_startFragment);
                }else{
                    Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mEmailEditText = view.findViewById(R.id.email);
        mPasswordEditText = view.findViewById(R.id.password);
        mLoginButtton = view.findViewById(R.id.loginButton);
        mLoginButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSignupViewModel.login(mEmailEditText.getText().toString(),mPasswordEditText.getText().toString());
            }
        });

        return view;
    }
}
