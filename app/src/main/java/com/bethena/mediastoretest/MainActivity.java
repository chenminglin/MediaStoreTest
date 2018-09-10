package com.bethena.mediastoretest;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final Uri URI_FILE = MediaStore.Files.getContentUri("external");

    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

//    private static final String[] COLUMNS_FILE = new String[]{
//            MediaStore.Files.FileColumns._ID,
//            MediaStore.Files.FileColumns.DATA,
//            MediaStore.Files.FileColumns.DATE_ADDED,
//            MediaStore.Files.FileColumns.MEDIA_TYPE,
//            MediaStore.Files.FileColumns.MIME_TYPE,
//            MediaStore.Files.FileColumns.TITLE,
//            MediaStore.Files.FileColumns.SIZE
//    };

    protected void initPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(this, R.string.tip_reinstall, Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPermission();

        final ContentResolver contentResolver = getContentResolver();


//        MediaStore.Files.getContentUri()

        new HandlerThread("11111"){
            @Override
            protected void onLooperPrepared() {
                super.onLooperPrepared();

                Cursor cursor =
                        ContentResolverCompat.query(contentResolver, URI_FILE,
                                null,
                                MediaStore.Files.FileColumns.TITLE + " like ?",
                                new String[]{"%"+"765F75084088992905059C411AE3D516"+"%"},
                                null,
                                null);


                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE));
                    String id = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID));
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                    String mediaType = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE));
                    String mimeType = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE));
                    String parent = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.PARENT));


                    Log.d("Main","==============================================================");
                    Log.d("Main", "title = " + title);
                    Log.d("Main", "id = " + id);
                    Log.d("Main", "data = " + data);
                    Log.d("Main", "mediaType = " + mediaType);
                    Log.d("Main", "mimeType = " + mimeType);
                    Log.d("Main", "parent = " + parent);
                    Log.d("Main","==============================================================");
                }

            }
        }.start();




    }
}
