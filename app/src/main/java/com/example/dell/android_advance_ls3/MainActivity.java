package com.example.dell.android_advance_ls3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.dell.android_advance_ls3.R.id.image_gallery;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_IMAGE = 1;
    private RecyclerView mRecyclerView;
    private String mDirector = "/Pictures/Zalo";
    private List<Bitmap> mBitmapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermissions();
        //initView();
        MyAsyncTask myAsyncTask= new MyAsyncTask(this);
        myAsyncTask.execute();



    }

    /**
     * This interface is the contract for receiving the results for permission requests.
     * @param requestCode:The request code passed in requestPermissions(android.app.Activity, String[], int)
     * @param permissions
     * @param grantResults: The grant results for the corresponding permissions which is either PERMISSION_GRANTED
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_IMAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initView();
            }
        }

    }

    public void initView(){
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        //StaggeredGridLayoutManager(int spanCount, int orientation)
        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        RecylerAdapter recyclerAdapter = new RecylerAdapter(this, mBitmapList);
        mRecyclerView.setAdapter(recyclerAdapter);
    }


    private void checkAndRequestPermissions() {
       // String permission[] = {Manifest.permission.READ_EXTERNAL_STORAGE};

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE);

            }
        return;
        }
        //readImage();
    }

    private void readImage(){
        //get a path
        String path = Environment.getExternalStorageDirectory() + mDirector;
        File file = new File(path);
        File [] arrFile = file.listFiles();

        //Check if file image is null
        if(arrFile.length == 0){
            Toast.makeText(this, "File is empty", Toast.LENGTH_LONG).show();
        }else {
            for(int i = 0; i<arrFile.length; i++){
                //Read images into Bitmap object & add to ArrayList
                // F8
                arrFile[i].listFiles();
                Bitmap bitmap = BitmapFactory.decodeFile(arrFile[i].getPath());
                mBitmapList.add(bitmap);
            }
        }
    }



}
