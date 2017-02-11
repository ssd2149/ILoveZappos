package com.example.ssd2149.ilovezappos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bsearch);
        bt.setOnClickListener(this);
    }
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bsearch:
                        onSearchRequested();
                        break;
            }

    }
}
