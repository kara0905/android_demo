android jni 开发demo
第一步 创建 NdkJniUtils类 使用 javah com.zyl.ndk.NdkJniUtils 生成头文件com_zyl_ndk_NdkJniUtils.h
第二步在main中创建jni文件夹,在jni文件夹中创建Android.mk Application.mk,和使用上一步头文件com_zyl_ndk_NdkJniUtils.h 编写.c文件
第三步在gradle 中配置externalNativeBuild{},android{ndk{} ndkVersion ""},然后make project 生成.so
第四步,注释掉gradle 中 生成so相关的配置 中配置externalNativeBuild{} android{ndk{}},
在main中创建jniLibs文件夹,将生成的so文件复制进去,运行程序就ok了


//.c文件bug调试        注释最后一个参数为  00000000000114a0 崩溃的汇编指令地址
// E:\sdk\ndk-bundle\toolchains\aarch64-linux-android-4.9\prebuilt\windows-x86_64\bin\aarch64-linux-android-addr2line  
-e H:\code_space\android_project\ASndk\app\src\main\jniLibs\armeabi-v7a\libmyJniApp.so  000000000x00000efe
//adb logcat | E:\sdk\ndk\21.0.6113669\ndk-stack -sym H:\code_space\android_project\ASndk\app\src\main\jniLibs\armeabi-v7a\libmyJniApp.so
//推送文件 LicenseFile.v2c 到 sdcard 下
//adb push C:\Users\Administrator\Desktop\LicenseFile.v2c /sdcard/LicenseFile.v2c