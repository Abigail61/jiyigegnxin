package com.example.jiyi;

import android.Manifest;
        import android.annotation.TargetApi;
        import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Bundle;
        import android.provider.DocumentsContract;
        import android.provider.MediaStore;
import android.view.MotionEvent;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.jiyi.db.DBOpenHelper;

import java.io.File;
import java.io.FileNotFoundException;

public class NewNote extends AppCompatActivity {
    private ImageButton ib3;
    private ImageView picture;
    private ImageView back;
    private Button fabu;
    private Button chooseLocationBtn;
    private TextView dingweiinfo;
    private TextView tag1,tag2,tag3;
    private Button chooseTag;
    private String Imagepath=null;
    private String sdate=null;
    private String username = null;
    EditText stickerEdit;
    SQLiteDatabase db;

    public static final int TAKE_CAMERA = 101;
    public static final int PICK_PHOTO = 102;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        ib3 = (ImageButton) findViewById(R.id.imageButton3);
        picture = (ImageView) findViewById(R.id.picture);
        back=(ImageView) findViewById(R.id.back) ;
        fabu=(Button) findViewById(R.id.publishBtn);
        stickerEdit = (EditText) findViewById(R.id.stickerEdit);
        chooseLocationBtn = (Button) findViewById(R.id.chooseLocationBtn);
        dingweiinfo = (TextView)findViewById(R.id.dingweiinfo);
        chooseTag = (Button) findViewById(R.id.addLableBtn);
        tag1 = (TextView)findViewById(R.id.tag1);
        tag2 = (TextView)findViewById(R.id.tag2);
        tag3 = (TextView)findViewById(R.id.tag3);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("trueusername");
        //SetData();

        //依靠DatabaseHelper的构造函数创建数据库
        DBOpenHelper dbHelper = new DBOpenHelper(NewNote.this);
        db = dbHelper.getWritableDatabase();

        //从相册选择图片
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态申请获取访问 读写磁盘的权限
                if (ContextCompat.checkSelfPermission(NewNote.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NewNote.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                } else {
                    //打开相册
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_PHOTO); // 打开相册
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stickerText = stickerEdit.getText().toString();
                String place = dingweiinfo.getText().toString();
                String tag1info = tag1.getText().toString();
                String tag2info = tag2.getText().toString();
                String tag3info = tag3.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                sdate=simpleDateFormat.format(date);
                //+Imagepath
                ContentValues values = new ContentValues();
                values.put("username",username);
                values.put("sdate", sdate);
                values.put("stickerText", stickerText);
                values.put("place",place);
                values.put("imagepath",Imagepath);
                values.put("tag1",tag1info);
                values.put("tag2",tag2info);
                values.put("tag3",tag3info);
                // 数据库执行插入命令
                db.insert("Sticker",null,values);

                Toast.makeText(NewNote.this, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        chooseLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(NewNote.this, Dingwei.class);
                startActivityForResult(it1,1001);
            }
        });
        chooseTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(NewNote.this, ChooseTag.class);
                startActivityForResult(it1,1002);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        switch (requestCode) {
            case TAKE_CAMERA:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PICK_PHOTO:
                if (resultCode == RESULT_OK) { // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            case 1001:
                if (bundle != null) {
                    dingweiinfo.setText(bundle.getString("title"));// 获取地址数据
                }
                break;
            case 1002:
                if (bundle != null) {
                    if(tag1.getText().toString().isEmpty()) {
                        tag1.setText(bundle.getString("tag1"));// 获取标签1数据
                    }
                    else{
                        if(tag2.getText().toString().isEmpty()) {
                            tag2.setText(bundle.getString("tag1"));// 获取标签2数据
                        }
                        else{
                            tag3.setText(bundle.getString("tag1"));// 获取标签3数据
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content: //downloads/public_downloads"), Long.parseLong(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        // 根据图片路径显示图片
        displayImage(imagePath);
        Imagepath=imagePath;
    }

    /**
     * android 4.4以前的处理方式
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
        Imagepath = imagePath;
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {//点击editText控件外部
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
                    KeyBoardUtil.closeKeyboard(v);//软键盘工具类
                    if (editText != null) {
                        editText.clearFocus();
                    }
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    EditText editText = null;

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            editText = (EditText) v;
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }
    private void SetData() {
        Bundle bundle = this.getIntent().getExtras();
        dingweiinfo.setText(bundle.getString("title"));
        tag1.setText(bundle.getString("tag1"));
    }
}