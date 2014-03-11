package com.java.app.mustardseed.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by danscott on 27/02/2014.
 *
 * Class which provides the List View Adapter and also the onItemClickListener
 * to activities containing ListViews.
 *
 */
public class ListAdapterProvider {


    private Cursor cursor;
    private ArrayList<HashMap<String, String>> adapterList;
    private HashMap<String, String> adapterValues;
    private Uri contentUri;
    private String[] projection;
    private ContentResolver contentResolver;
    private SimpleAdapter listViewAdapter;
    private Context context;

    /**
     *
     * @param uri
     * @param projection
     * @param contentResolver
     * @param context
     */
    public ListAdapterProvider(Uri uri, String[] projection,
                               ContentResolver contentResolver, Context context) {

        adapterList = new ArrayList<HashMap<String, String>>();
        contentUri = uri;
        this.projection = projection;
        this.contentResolver = contentResolver;
        this.context = context;
    }

    /**
     *
     * @param text1 The title/first TextView to be visible in the ListItem of the ListView
     * @param text2 The subTitle/second TextView to be visible in the ListItem of the ListView
     *
     * e.g. <b>Desert Soul</b>
     *      <i>Rend Collective Experiment</i>
     *
     * @return Ready to use SimpleAdapter
     */
    public SimpleAdapter getAdapter(String text1, String text2) {

        cursor = contentResolver.query(contentUri, projection, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {

                do {
                    adapterValues = new HashMap<String, String>();

                    String title =
                            cursor.getString(cursor.getColumnIndex(
                               text1
                            ));

                    String subTitle =
                            cursor.getString(cursor.getColumnIndex(
                                    text2
                            ));

                    adapterValues.put("Title", title);
                    adapterValues.put("Duration", subTitle);
                    adapterList.add(adapterValues);


                } while (cursor.moveToNext());

            }
        }



        listViewAdapter = new SimpleAdapter(context, adapterList,
                android.R.layout.simple_list_item_2, new String[] {"Title", "Duration"},
                new int[] {android.R.id.text1, android.R.id.text2});

        return listViewAdapter;
    }

    public AdapterView.OnItemClickListener getOnItemClickListener(final Intent nextActivity,
                                                                  final String paramData,
                                                                  final Activity currentActivity) {

        AdapterView.OnItemClickListener onItemClickListener = new
                AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> adapterView, View view, int position, long id) {

                        cursor.moveToPosition(position);

                        int index = cursor.getColumnIndexOrThrow(paramData);
                        String file = cursor.getString(index);

                        try {

                            HashMap<String, String> fileInfo =
                                    adapterList.get(position);
                            String title = fileInfo.get("Title");
                            nextActivity.putExtra("TITLE", title);

                        } catch (NullPointerException ne) {
                            ne.printStackTrace();
                        }

                        nextActivity.putExtra("FILE", file);
                        currentActivity.startActivity(nextActivity);


                    }
                };

        return onItemClickListener;
    }

}
