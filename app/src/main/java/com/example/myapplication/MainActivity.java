package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private static final int PHOTO_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = findViewById(R.id.btnMap);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //geo:0:0 - Abrir aplicativo de localização centralizado na tela.
                Uri mapUri = Uri.parse("geo:0,0?q=COLTEC UFMG");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);

                startActivity(mapIntent);

            }
        });

        Button btnCall = findViewById(R.id.btnCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri callUri = Uri.parse("tel:31992855944");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, callUri);

                startActivity(callIntent);

            }
        });

        Button btnPhoto = findViewById(R.id.btnPhoto);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //capturar foto
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //depreciado, ver depois como fazer atualmente.
                startActivityForResult(photoIntent, PHOTO_CODE);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Se for igual ao enviado, essa é a ação da captura da foto
        if(requestCode == PHOTO_CODE && resultCode == Activity.RESULT_OK){

            //A foto é sempre retornada através do "data" através do Bundle getExtras que vem da Intent Data
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            ImageView ivPhoto = this.findViewById(R.id.iv_photo);
            ivPhoto.setImageBitmap(photo);


        }

    }
}