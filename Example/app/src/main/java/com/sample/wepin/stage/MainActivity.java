package com.sample.wepin.stage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.wepin.widget.types.Account;
import io.wepin.widget.types.Wepin;
import io.wepin.widget.types.WepinListener;
import io.wepin.widget.types.WepinOptions;
import io.wepin.widget.types.WidgetAttributes;
import io.wepin.widget.utils.Trace;

public class MainActivity extends AppCompatActivity implements WepinListener {
    private final String sLogTag = "wepinExample";

    private String _appId = "test_appId";
    private String _appSdkKey = "test_appKey";

    private ListView itemListView;
    private TextView tvResult;
    private String[] testItem;
    private Wepin _wepin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Trace.d("Main_onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        initView();
        _wepin = Wepin.getInstance(MainActivity.this);
        _wepin.setListener(MainActivity.this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Trace.d("onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        Trace.i("onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Trace.i("onResume");
        super.onResume();
    }

    private void initView(){
        Log.i(sLogTag, "initView");

        try {
            itemListView = findViewById(R.id.lv_menu);
            tvResult = findViewById(R.id.tv_result);
            testItem = new String[] {
                    getResources().getString(R.string.item_initialize_show),
                    getResources().getString(R.string.item_initialize_hide),
                    getResources().getString(R.string.item_is_initialized),
                    getResources().getString(R.string.item_open_widget),
                    getResources().getString(R.string.item_close_widget),
                    getResources().getString(R.string.item_get_accounts),
                    getResources().getString(R.string.item_finalize)
            };
            ArrayAdapter<String> adapter = new
                    ArrayAdapter<String>
                    (MainActivity.this, android.R.layout.simple_list_item_1, testItem);

            itemListView.setAdapter(adapter);
            itemListView.setItemsCanFocus(true);
            itemListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            itemListView.setOnItemClickListener(new ListView.OnItemClickListener()
            {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
                {
                    String operationItem = arg0.getAdapter().getItem(arg2).toString();

                    if (operationItem.equals(getResources().getString(R.string.item_initialize_show)))
                    {
                        Log.d(sLogTag, operationItem);
                        if( _wepin.isInitialized() ){
                            tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, "Already initialized"));
                            return;
                        }else{
                            tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, "Successed"));
                        }

                        WidgetAttributes attributes = new WidgetAttributes("show", "ko", "krw");
                        WepinOptions wepinOptions = new WepinOptions(_appId, _appSdkKey, attributes);
                        _wepin.initialize(wepinOptions);
                    } else if( operationItem.equals(getResources().getString(R.string.item_initialize_hide)) ){
                        Log.d(sLogTag, operationItem);
                        if( _wepin.isInitialized() ){
                            tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, "Already initialized"));
                            return;
                        }else{
                            tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, "Successed"));
                        }
                        WidgetAttributes attributes = new WidgetAttributes("hide", "ko", "krw");
                        WepinOptions wepinOptions = new WepinOptions(_appId, _appSdkKey, attributes);
                        _wepin.initialize(wepinOptions);
                    } else if (operationItem.equals(getResources().getString(R.string.item_is_initialized)) )
                    {
                        Log.d(sLogTag, operationItem);
                        boolean isInit = _wepin.isInitialized();
                        tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, isInit));
                    } else if (operationItem.equals(getResources().getString(R.string.item_open_widget)) )
                    {
                        Log.d(sLogTag, operationItem);
                        boolean result = _wepin.openWidget();
                        tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, result));
                    } else if (operationItem.equals(getResources().getString(R.string.item_close_widget)) )
                    {
                        Log.d(sLogTag, operationItem);
                        boolean result = _wepin.closeWidget();
                        tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, result));

                    } else if (operationItem.equals(getResources().getString(R.string.item_get_accounts)) )
                    {
                        Log.d(sLogTag, operationItem);
                        List<Account> accountList = _wepin.getAccounts();
                        tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, accountList));

                    } else if (operationItem.equals(getResources().getString(R.string.item_finalize)) )
                    {
                        Log.d(sLogTag, operationItem);
                        _wepin.finalize();
                        tvResult.setText(String.format(" Item : %s\n Result : %s", operationItem, "Successed"));
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(sLogTag, "onBackPressed");
        super.onBackPressed();
        _wepin.finalize();
    }

    @Override
    public void onWepinError(String errorMsg)
    {
        Trace.d("onWepinError : " + errorMsg);
        if( null != tvResult ){
            tvResult.setText("onWepinError : " + errorMsg);
        }
    }

    @Override
    public void onAccountSet() {
        Trace.d("onAccountSet");
        List<Account> accountList = _wepin.getAccounts();
        for( Account account : accountList ){
            Trace.i("network : " + account.getNetwork());
            Trace.i("address : " + account.getAddress());
        }
    }
}