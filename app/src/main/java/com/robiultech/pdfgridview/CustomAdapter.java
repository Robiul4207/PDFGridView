package com.robiultech.pdfgridview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<PDFDoc> pdfDocs;

    public CustomAdapter(Context context, ArrayList<PDFDoc> pdfDocs) {
        this.context = context;
        this.pdfDocs = pdfDocs;
    }

    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int position) {
        return pdfDocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.model,parent,false);

        }
        //final PDFDoc pdfDoc = new PDFDoc();
        final PDFDoc pdfDoc= (PDFDoc) this.getItem(position);
        TextView textView= convertView.findViewById(R.id.nameTextId);
        ImageView imageView= convertView.findViewById(R.id.pdfImage);
        // data bind
        textView.setText(pdfDoc.getName());
        imageView.setImageResource(R.drawable.pdf);
        // view item click listener
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPdfView(pdfDoc.getPath());

            }
        });

        return convertView;
    }

    private void openPdfView(String path) {
        Intent intent= new Intent(context,PdfActivity.class);
        intent.putExtra("path",path);
        context.startActivity(intent);
    }
}
