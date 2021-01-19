package com.amornchanok.nextstep_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class Page_detail extends AppCompatActivity {

    TextView text_1;
    TextView text_2;
    public static String INTENT_STUDIO_ID = "studioId";


    ImageView imageView;
    TextView text_name,text_key,text_url;


    String s_key;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.page_detail);



        imageView = (ImageView) findViewById(R.id.imageView);

        text_name = (TextView) findViewById(R.id.text_name);
        text_key = (TextView) findViewById(R.id.text_key);
        text_url = (TextView) findViewById(R.id.text_url);


        Intent intent = getIntent();
        //String s_id = intent.getStringExtra("id");
        //String s_id = intent.getStringExtra(INTENT_STUDIO_ID);
        s_key = intent.getStringExtra("s_key");
        String s_name = intent.getStringExtra("s_name");
        String s_url = intent.getStringExtra("s_url");


        text_name.setText("" + s_name);
        text_key.setText("" + s_key);
        text_url.setText("" + s_url);




        Picasso.get().load("" + s_url).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);
                //.load(get(s_url).getI_url())


        Button b_next_1 = (Button) findViewById(R.id.b_next_1);
        b_next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Page_detail.this, Page_detail.class);
                i.putExtra("s_key",s_key);
                startActivity(i);
            }
        });



    }

}
