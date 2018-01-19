package com.example.dell.android_advance_ls3;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.MainThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 19-Jan-18.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, ArrayList<Bitmap>>  {
    private final int REQUEST_IMAGE = 1;
    private String mDirector = "/Pictures/Zalo";
    private List<Bitmap> mBitmapList = new ArrayList<>();
    private Activity  mActivity;


    public MyAsyncTask(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected void onPreExecute() {
        //Before excute
        //Find a file and take these photos
        readImage();
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(Void... voids) {
        initView();
        return (ArrayList<Bitmap>) mBitmapList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
        super.onPostExecute(bitmaps);
    }



    private void readImage() {
        //get a path
        String path = Environment.getExternalStorageDirectory() + mDirector;
        File file = new File(path);
        File[] arrFile = file.listFiles();
        for (int i = 0; i < arrFile.length; i++) {
            //Read images into Bitmap object & add to ArrayList
            arrFile[i].listFiles();
            Bitmap bitmap = BitmapFactory.decodeFile(arrFile[i].getPath());
            mBitmapList.add(bitmap);
        }
    }
    private void initView(){
        RecyclerView mRecyclerView = mActivity.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        //StaggeredGridLayoutManager(int spanCount, int orientation)
        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        RecylerAdapter recyclerAdapter = new RecylerAdapter(mActivity, mBitmapList);
        mRecyclerView.setAdapter(recyclerAdapter);
    }


}

