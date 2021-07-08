package com.gallopingtech.imageselecter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

public class MainActivity extends AppCompatActivity {
    private ImageView MainImage;
    EditText editText;
    Button button;
    TextView tx;

    CoordinatorLayout layout;

    private static  String SHARE_PREF = "shared prefs";
    private  static  String Appname  = "save";
    private  String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = findViewById(R.id.layout);
        setContentView(R.layout.activity_main);
        MainImage = findViewById(R.id.main_image);
        editText = findViewById(R.id.text);
        button = findViewById(R.id.button);
        tx = findViewById(R.id.test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.setText(editText.getText().toString());

                SaveInfo();


            }
        });
        loadDat();
        upload();
    }

    public void Dialog(View view) {
        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        //TODO: do what you have to...


                        MainImage.setImageBitmap(r.getBitmap());


//

                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                        //TODO: do what you have to if user clicked cancel
                    }
                }).show(getSupportFragmentManager());
    }

    private void SaveInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Appname,tx.getText().toString());
        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
    }
    private  void  loadDat(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREF, MODE_PRIVATE);
       text  = sharedPreferences.getString(Appname,"");

    }
    private  void  upload(){
        tx.setText(text);
        MainImage.setMaxWidth(100);
    }
}