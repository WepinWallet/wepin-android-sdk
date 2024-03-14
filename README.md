# wepin-android-sdk
Wepin Native SDK for Android

## :fast_forward: Getting Started

1. Copy wepin-widget-vX.X.X.aar file to libs folder in your android application project
   
2. Add the below line in your app-level build.gradle file:

```
dependencies {
    implementation files('libs/wepin-widget-vX.X.X.aar')
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.8.5'    
}

```

## :fast_forward: Add Permission
Add the below line in your app's AndroidManifest.xml file

```
<uses-permission android:name="android.permission.INTERNET" />

```

## :fast_forward: Configure Deep Link
Deep link scheme format: 'wepin.' + Your wepin app id


> ‼️ Notice of Change: Deep Link Scheme Value (From SDK Version 0.0.8-alpha) ‼️
> For developers using SDK version 0.0.8 and above, please note that the structure of the Deep Link Scheme value has been updated.
>
> * Before: `pakage name` + '.wepin'
> * After: 'wepin.' + `wepin appid`
>
> This change is essential for the proper functioning of the Wepin widget. Please ensure to apply the new scheme value for the correct operation of the Wepin widget.

Add the below line in your app's AndroidManifest.xml file

```
<activity
   android:name="io.wepin.widget.WepinMainActivity"
   android:exported="true">
   <intent-filter>
       <action android:name="android.intent.action.VIEW" />
       <category android:name="android.intent.category.DEFAULT" />
       <category android:name="android.intent.category.BROWSABLE" />
       <!--For Deep Link => Urlscheme Format : 'wepin.' + app id of your wepin app -->
       <data
           android:scheme="wepin.WEPIN_APP_ID"
           />
   </intent-filter>
</activity>
```

## :fast_forward: Import SDK

```
import io.wepin.widget.types.*;
```

## :fast_forward: Initialize

Create instance of wepin in your activity to use wepin and pass your activity as a parameter

```
wepin = Wepin.getInstance(MainActivity.this);
```

## :fast_forward: Set listener and implement Methods 

### Set listener of wepin

```
public class MainActivity extends AppCompatActivity implements WepinListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //...
        Wepin wepin = Wepin.getInstance(MainActivity.this);
        wepin.setListener(MainActivity.this);
    }
}

```

### Implement mehods

1. onWepinError

Called when error occured in wepin 

```
 @Override
 public void onWepinError(String errorMsg)
 {
     Log.d(sLogTag, "onWepinError : " + errorMsg);
     if( null != tvResult ){
         tvResult.setText("onWepinError : " + errorMsg);
     }
 }
```

2. onAccountSet

Called when wepin's accounts were created

If this called, you can get accouts of wepin

```
 @Override
 public void onAccountSet() {
     Log.d(sLogTag, "onAccountSet");
     List<Account> accountList = wepin.getAccounts();
     for( Account account : accountList ){
         Log.i(sLogTag, "network : " + account.getNetwork());
         Log.i(sLogTag, "address : " + account.getAddress());
     }
 }
```



