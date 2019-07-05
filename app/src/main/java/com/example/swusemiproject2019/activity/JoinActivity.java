package com.example.swusemiproject2019.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.swusemiproject2019.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinActivity extends Activity {

    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQ_TAKE_PHOTO = 2222;
    private static final int REQ_TAKE_ALBUM = 3333;
    private static final int REQ_TAKE_IMAGE_CROP = 4444;

    private ImageView imgview;
    Button btnCamera;


    private String mCurrentImageFilePath = null;
    private Uri mProviderUri = null;
    private Uri mPhotoUri = null;
    private Uri mAlbumUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();

        setContentView(R.layout.activity_join);


        imgview = (ImageView) findViewById(R.id.imgview);
        btnCamera = (Button) findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                captureCamera();

            }
        });

        //회원가입 버튼
        Button btnRegReg = findViewById(R.id.btnRegReg);
        btnRegReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });



    }

    //카메라 작업
    private void captureCamera(){
        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null){
            File photoFile = createFileName();
            if (photoFile != null) {
                Uri providerURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                mProviderUri = providerURI;
                intent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI);

                startActivityForResult(intent, REQ_TAKE_PHOTO);
            }
        }


    }

    //회원가입
    private void joinProcess(){

    }

    private File createFileName() {
        //현재 년월일 시분초를 기준으로 파일명 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = timeStamp + ".jpg";

        File myDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "item");
        if (!myDir.exists()) {
            myDir.mkdir();
        }

        File imageFile = new File(myDir, fileName);
        mCurrentImageFilePath = imageFile.getAbsolutePath();

        return imageFile;
    }

    private void checkPermission() {
        //self 권한 체크
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            //권한 동의 체크
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
            ) {

            } else {
                //권한동의 팝업 표시 요청
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, 1111);
            }
        }
    } // End checkPermission

    //사용자 권한동의 결과 출력
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1111:
                // 0:권한 혀용  -1:권한 거부
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] < 0) {
                        Toast.makeText(this, "해당 권한을 활성화하셔야합니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
        } // End switch
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQ_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {

                    imgview.setImageURI(mProviderUri); // 사진촬영한 이미지 설정

                } else {
                    Toast.makeText(this, "사진촬영을 취소하였습니다.", Toast.LENGTH_LONG).show();
                }
                break;
            case REQ_TAKE_ALBUM:
                if (resultCode == Activity.RESULT_OK) {
                    File albumFile = createFileName();
                    mPhotoUri = data.getData();
                    mAlbumUri = Uri.fromFile(albumFile);

                    imgview.setImageURI(mPhotoUri);   // 앨범에서 선택한 이미지 설정
                }
                break;
        }

    }


}