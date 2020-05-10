package com.example.spstore;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {
    public abstract int getCoordinatorLayoutId();

    public void showSnackBarMessage(String message) {
        Snackbar.make(findViewById(getCoordinatorLayoutId()), message, Snackbar.LENGTH_SHORT).show();
    }

}
