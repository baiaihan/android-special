package com.baijunyu.testjsbridge;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * webview
     */
    private BridgeWebView bridgeWebView;
    /**
     * logTag
     */
    private final String TAG = "JsBridge";
    /**
     *
     */
    private int CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        bridgeWebView = (BridgeWebView) findViewById(R.id.webView);

        bridgeWebView.setDefaultHandler(new myHadlerCallBack());
        bridgeWebView.setWebChromeClient(new WebChromeClient() {
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {

            }
        });
       // bridgeWebView.loadUrl("file:///android_asset/demo.html");
        bridgeWebView.loadUrl("http://10.1.2.23:9001/newsdetail");
        initData();
    }

    private void initData() {
        //注册submitFromWeb方法,JS调用java方法
        bridgeWebView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "得到JS传过来的数据 data =" + data);
                show(data);
                function.onCallBack("传递数据给JS");
            }
        });
        //注册functionOpen方法，JS调用java
        bridgeWebView.registerHandler("functionOpen", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Toast.makeText(MainActivity.this, data + "网页在打开你的文件预览", Toast.LENGTH_SHORT).show();
                pickFile();
            }

        });
        //注册submitFromWeb方法,JS调用java方法
        bridgeWebView.registerHandler("JUMP", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "得到JS传过来的数据 data =" + data);
                show(data);
                function.onCallBack("传递数据给JS");
            }
        });

    }

    /**
     * Java 调JS的functionJs方法并得到返回值
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
//                //参数：js function名称，要传给js的参数，js回调callback
//                bridgeWebView.callHandler("functionJs", "阿拉蕾", new CallBackFunction() {
//                    @Override
//                    public void onCallBack(String data) {
//                        //JS返回的数据
//                        show(data);
//                    }
//
//                });
                //参数：js function名称，要传给js的参数，js回调callback
                TestJsBridge testJsBridge = new TestJsBridge();
                testJsBridge.setApp_Key("android");
                String s = new Gson().toJson(testJsBridge);
                bridgeWebView.callHandler("registerAction", s, new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        //JS返回的数据
                        show(data);
                    }

                });
                break;
            case R.id.button1:
                //bridgeWebView.send("不接受返回值");
                bridgeWebView.send("接受返回值", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        show(data);
                    }
                });
                break;
            default:
                break;
        }

    }


    private void show(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义回调
     */
    class myHadlerCallBack extends DefaultHandler {

        @Override
        public void handler(String data, CallBackFunction function) {
            if (function != null) {
                Toast.makeText(MainActivity.this, "自定义类继承DefaultHandler：" + data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void pickFile() {
        Intent picture = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CODE) {

            //文件操作
            Uri selectedImage = intent.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Log.i(TAG, picturePath);
            Toast.makeText(MainActivity.this, picturePath, Toast.LENGTH_LONG).show();
        }
    }
}
