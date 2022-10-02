package com.robiultech.pdfgridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONCODE = 1;
    ArrayList<PDFDoc> pdfDocs = new ArrayList<>();
     GridView gridView;
    private boolean boolean_permission;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);
        gridView =findViewById(R.id.gridViewId);
        FloatingActionButton fab = findViewById(R.id.fabId);
        checkPermission1();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setAdapter(new CustomAdapter(MainActivity.this,getPDFs()));
            }
        });


    }

    private void checkPermission1() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else{

                ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSIONCODE);

            }


        } else {
            boolean_permission=true;
            getPDFs();
            customAdapter = new CustomAdapter(this,pdfDocs);
            gridView.setAdapter(customAdapter);


        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSIONCODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                boolean_permission=true;
                getPDFs();
                customAdapter = new CustomAdapter(getApplicationContext(),pdfDocs);
                gridView.setAdapter(customAdapter);

            }
            else{
                Toast.makeText(this, "Please allow the permission", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private ArrayList<PDFDoc> getPDFs() {
        // ArrayList<PDFDoc> pdfDocs = new ArrayList<>();
        // target files
        File downloadFolders= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        PDFDoc pdfDoc;
        if(downloadFolders.exists()){
            //get all files in download folder
            File [] files=downloadFolders.listFiles();
            // loop throw those files getting name & url
            if(files !=null && files.length > 0){
                for (int i=0;i< files.length;i++)
                {
                    File file= files[i];
                    if(file.getName().endsWith(".pdf")){
                        pdfDoc= new PDFDoc();
                        pdfDoc.setName(file.getName());
                        pdfDoc.setPath(file.getAbsolutePath());
                        pdfDocs.add(pdfDoc);
                    }
                }
            }


        }
        return pdfDocs;
    }
}