package com.example.myapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplicationtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'myapplicationtest' library on application startup.
    static {
        System.loadLibrary("myapplicationtest");
        //System.loadLibrary("native-test");
    }

    private ActivityMainBinding binding;
    private TextView tv;
    private EditText et;
    private String precision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tv = binding.sampleText;
        Button btn = findViewById(R.id.submit);
        et = findViewById(R.id.precison_input);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precision = et.getText().toString();
                Log.e("CHIDLe",precision);
                String result = stringFromGetPi(precision);
                tv.setText(result);
            }
        });

    }
    public native String stringFromGetPi(String precision);
    /**
     * A native method that is implemented by the 'myapplicationtest' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


}