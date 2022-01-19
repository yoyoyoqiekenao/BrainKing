package com.example.brainking.home.userinfo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.UpdateUserInfoModel;
import com.example.brainking.model.UploadModel;
import com.example.brainking.util.GlideCacheEngine;
import com.example.brainking.util.GlideEngine;
import com.example.brainking.util.SpUtils;
import com.example.brainking.views.BottomPopWinPhoto;
import com.example.brainking.views.BottomPotSelectLinstener;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.SdkVersionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserInfoActivity extends BrainActivity<UserInfoPresenter> implements UserInfoView, View.OnClickListener {

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.iv_submit)
    ImageView iv_submit;
    @BindView(R.id.ed_remark)
    EditText ed_remark;

    private String mImg;
    private String mName;
    private String mRemark;

    //拍照
    private int SelectPhoTypeFront1 = 3;
    //选择相册
    private int SelectPhoTypeFront2 = 4;

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();

        mImg = getIntent().getStringExtra("img");
        mName = getIntent().getStringExtra("name");
        mRemark = getIntent().getStringExtra("remark");
        Glide.with(this).load(mImg).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);

        if (!TextUtils.isEmpty(mName)) {
            ed_name.setText(mName);
        }
        if (!TextUtils.isEmpty(mRemark)) {
            ed_remark.setText(mRemark);
        }


        rl_back.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        iv_submit.setOnClickListener(this);
        iv_head.setOnClickListener(this);

        XXPermissions.with(this)
                .permission(Permission.CAMERA)
                .permission(Permission.READ_EXTERNAL_STORAGE)
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {

                        }
                    }
                });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(UserInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //没有权限则申请权限
                ActivityCompat.requestPermissions(UserInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                //有权限直接执行,docode()不用做处理


            }
        } else {
            //小于6.0，不用申请权限，直接执行

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:
                selectPho(SelectPhoTypeFront1, SelectPhoTypeFront2);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_close:
                ed_name.setText("");
                break;
            case R.id.iv_submit:
                if (TextUtils.isEmpty(ed_name.getText().toString())) {
                    Toast.makeText(UserInfoActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }
                createPresenter().updateUserInfo(ed_name.getText().toString(), ed_code.getText().toString(), mImg, ed_remark.getText().toString());
                break;
            default:
        }
    }

    private void selectPho(int selectPhoType1, int selectPhoType2) {
        BottomPopWinPhoto bottomPopWinPhoto = new BottomPopWinPhoto(this, new BottomPotSelectLinstener() {
            @Override
            public void getSelectStr(String selectStr) {
                if (selectStr.equals("拍照")) {
                    //ToastUtils.showLong("拍照功能");
                    selectCamera(selectPhoType1);
                } else if (selectStr.equals("相册")) {
                    //ToastUtils.showLong("相册功能");
                    selectPhoto(selectPhoType2);
                }
            }
        });
    }

    private void selectPhoto(int selectPhoType2) {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                //.maxSelectNum(10 - mList.size())
                .compress(true)
                .forResult(selectPhoType2);

    }

    private void selectCamera(int selectPhoType1) {
        // 单独拍照
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .compress(true)
                .forResult(selectPhoType1);

    }

    @Override
    public void updateSuccess(UpdateUserInfoModel model) {
        finish();
    }

    @Override
    public void fail(String err) {
        Log.d("xuwudi", "err===" + err);
    }

    @Override
    public void uploadSuccess(UploadModel model) {
        mImg = model.getUrl();
        Glide.with(this).load(model.getUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照或选择相册返回的数据 - 目前单选
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    getPath(data, requestCode);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 选择照片后回调监听
     */
    private void getPath(Intent data, int requestCode) {
        String TAG = "TAG";
        List<LocalMedia> selectListCamera = PictureSelector.obtainMultipleResult(data);
        for (LocalMedia media : selectListCamera) {
            Log.i(TAG, "是否压缩:" + media.isCompressed());
            Log.i(TAG, "压缩:" + media.getCompressPath());
            Log.i(TAG, "原图:" + media.getPath());
            Log.i(TAG, "是否裁剪:" + media.isCut());
            Log.i(TAG, "裁剪:" + media.getCutPath());
            Log.i(TAG, "是否开启原图:" + media.isOriginal());
            Log.i(TAG, "原图路径:" + media.getOriginalPath());
            Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
            Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
            Log.i(TAG, "Size: " + media.getSize());
            // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            System.out.println("打印监听输出---拍照或相册选择图片路径=" + media.getCompressPath());
            //createPresenter().getFile(new File(media.getCutPath()));
            uploadImg(media.getCompressPath());
        }
    }

    private void uploadImg(String str) {
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File(str);
        RequestBody image = RequestBody.create(MediaType.parse("image/jpeg"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", str, image)
                .build();
        Request request = new Request.Builder()
                .url("https://www.wdsd66.cn/api/common/upload")
                .header("Authorization", SpUtils.getInstance().getString("token"))
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                UploadModel bean = gson.fromJson(response.body().string(), UploadModel.class);
                mImg = bean.getUrl();


            }
        });
        Glide.with(UserInfoActivity.this).load(mImg).
                error(R.mipmap.iv_head)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
    }

    public static File uriToFile(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }
}
