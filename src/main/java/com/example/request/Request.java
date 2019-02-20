package com.example.request;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Button request = (Button) findViewById(R.id.Request_Request_button);

        request.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Todo 
            }
        });
    }
}
