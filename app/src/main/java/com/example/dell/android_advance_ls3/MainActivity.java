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
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_IMAGE = 1;
    private RecyclerView mRecyclerView;
    private List<Bitmap> mBitmapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermissions();
        initView();
    }

    /**
     * This interface is the contract for receiving the results for permission requests.
     * @param requestCode:The request code passed in requestPermissions(android.app.Activity, String[], int)
     * @param permissions
     * @param grantResults: The grant results for the corresponding permissions which is either PERMISSION_GRANTED
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_IMAGE)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        String permission[] = {Manifest.permission.READ_EXTERNAL_STORAGE};

        if (ContextCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED) {
            //Check version android is >= 6.0 (Marshmallow)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                ActivityCompat.requestPermissions(this, permission, REQUEST_IMAGE);
            }
        }
        readImage();

    }

    private void readImage(){
        //get a path
        String path = Environment.getExternalStorageDirectory().getPath() + "/Pictures";
        File file = new File(path);
        File [] arrFile = file.listFiles();
        //Check if file image is null
        if(arrFile.length == 0){
            Toast.makeText(this, "File is empty", Toast.LENGTH_LONG).show();
        }else {
            for(int i = 0; i<arrFile.length; i++){
                //Read images into Bitmap object & add to ArrayList
                Bitmap bitmap = BitmapFactory.decodeFile(arrFile[i].getPath());
                mBitmapList.add(bitmap);
            }
        }
    }



}
