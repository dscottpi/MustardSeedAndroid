package com.java.app.mustardseed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danscott on 18/02/2014.
 */
public class PdfHome extends Activity {

    private ArrayList<HashMap<String, String>> pdfFiles;
    private HashMap<String, String> pdfFile;
    private ListView listView;
    private SimpleAdapter adapter;
    private List<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_home);

        listView = (ListView)findViewById(R.id.pdflistView);
        pdfFiles = new ArrayList<HashMap<String, String>>();
        adapter = new SimpleAdapter(this, pdfFiles,
                android.R.layout.simple_list_item_1, new String[] {"Title"},
                new int[] {android.R.id.text1});
        paths = new ArrayList<String>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        pdfFiles.clear();
        adapter.notifyDataSetChanged();

        File file = Environment.getExternalStorageDirectory();



        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                Intent i = new Intent(PdfHome.this, PdfView.class);
                String path = paths.get(position);
                i.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, path);
                startActivity(i);
            }
        });
        walkdir(file);

    }

    private void walkdir(File file) {
        File[] pdfist = file.listFiles();

        if (pdfist!= null) {
            for (int i = 0; i < pdfist.length; i++) {

                if (pdfist[i].isDirectory()) {
                    walkdir(pdfist[i]);
                } else {
                    if (pdfist[i].getName().endsWith(".pdf")) {
                        pdfFile = new HashMap<String, String>();
                        pdfFile.put("Title", pdfist[i].getName());
                        pdfFiles.add(pdfFile);
                        adapter.notifyDataSetChanged();
                        String path = pdfist[i].getAbsolutePath();
                        paths.add(path);
                        System.out.println(pdfist[i].getName());
                    }
                }
            }
        }
    }

}
