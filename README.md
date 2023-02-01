# wepin-android-sdk
Wepin Native SDK for Android

## :fast_forward: Getting Started

1. Copy wepin-widget-vX.X.X.aar file to libs folder in your android application project
   
2. Add the below line to your build.gradle file:
```
dependencies {
    implementation files('libs/wepin-widget-vX.X.X.aar')
    implementation 'com.google.firebase:firebase-auth'    
    implementation 'com.google.android.gms:play-services-auth:20.3.0'    
    implementation platform('com.google.firebase:firebase-bom:31.0.2')    
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.8.5'    
    implementation group: 'com.squareup.retrofit2', name: 'retrofit', version: '2.8.1'    
    implementation group: 'com.squareup.retrofit2', name: 'converter-gson', version: '2.8.1'    
    implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9'        
}
```

## :fast_forward: Import SDK
```
import io.wepin.widget.types.Wepin;
import io.wepin.widget.types.Account;
import io.wepin.widget.types.WepinErrorListener;
import io.wepin.widget.types.WepinOptions;
import io.wepin.widget.types.WidgetAttributes;
```
