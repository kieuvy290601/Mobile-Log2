package com.example.mobilelb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    List<String> imgList = new ArrayList<>();
    Button prevButton, nextButton, addButton;
    EditText urlTxt;


    int i = 0;

    private static final String CHECK_IMG_URL = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpeg|jpg|gif|png)";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        nextButton = findViewById(R.id.nextBtn);
        prevButton = findViewById(R.id.prevBtn);
        addButton = findViewById(R.id.addBtn);
        urlTxt = findViewById(R.id.urlText);

        //imgList = Arrays.asList(getResources().getStringArray(R.array.listImg));

        imgList.add("https://i.pinimg.com/564x/9b/10/a1/9b10a13cbb2bab33837967b12db18e84.jpg");
        imgList.add("https://i.pinimg.com/564x/2d/1d/d3/2d1dd31da4e60f00a5bcd8864ff7a1e3.jpg");
        imgList.add("https://i.pinimg.com/564x/08/b2/5a/08b25aef3806d9ea4c7c16a343c4f7f7.jpg");

        picassoShowImage(i);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i += 1;
                if(i == imgList.size()){
                    i = 0;
                    picassoShowImage(i);

                }else{
                    picassoShowImage(i);
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i -=1;
                if(i < 0){
                    i = imgList.size() - 1;
                    picassoShowImage(i);
                }else{
                    picassoShowImage(i);
                }

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = urlTxt.getText().toString();
                if (url.matches(CHECK_IMG_URL)){
                    if(!url.equals("")){
                        imgList.add(url);
                        i = imgList.size() - 1;
                        picassoShowImage(i);
                    }else{
                        Toast.makeText(MainActivity.this, "Please insert URl to add", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid link", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void picassoShowImage(int i){
        Picasso.get().load(imgList.get(i)).into(imageView);
    }

}