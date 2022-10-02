package com.robiultech.pdfgridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;


import java.io.File;

public class PdfActivity extends AppCompatActivity {
    PDFView pdfView;
   // ScrollBar scrollBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfView=findViewById(R.id.pdfId);
        //scrollBar=findViewById(R.id.scrollbar);
        // enable scrolling
       // pdfView.setScrollBar(scrollBar);
        // vertical scrolling
        //scrollBar.setHorizontal(false);
        // sacrifice memory for quality
       // pdfView.useBestQuality(true);

        //Intent i= this.getIntent();
        //String path = i.getExtras().getString("path");
        String path= getIntent().getStringExtra("path");

        // get the pdf file
        File file = new File(path);
        if(file.canRead()){
            // load it
            pdfView.fromFile(file).enableSwipe(true).enableAnnotationRendering(true).defaultPage(1).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(PdfActivity.this, String.valueOf(nbPages), Toast.LENGTH_SHORT).show();
                }
            }).scrollHandle(new DefaultScrollHandle(this)).load();

        }



    }
}