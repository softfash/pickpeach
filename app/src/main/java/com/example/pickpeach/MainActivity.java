package com.example.pickpeach;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button btn_peach;
    private TextView tv_count;
    private int totalCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btn_peach = findViewById(R.id.btn_peach);
        tv_count = findViewById(R.id.tv_count);
        ActivityResultLauncher launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        int count = data.getIntExtra("count", 0);
                        totalCount = totalCount + count;
                        tv_count.setText("摘到" + totalCount + "个");
                    }
                });
        btn_peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PeachActivity.class);
                launcher.launch(intent);
            }
        });
    }
}
