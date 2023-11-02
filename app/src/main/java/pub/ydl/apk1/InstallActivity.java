package pub.ydl.apk1;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

public class InstallActivity extends Activity{

@Override
protected void onCreate (Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    install(getIntent());
    finish();
}


@Override
protected void onNewIntent (Intent intent){
    super.onNewIntent(intent);

    install(intent);
    finish();

}


protected void install (Intent in){
    if(in==null){
        return;
    }


    Uri dataUri=in.getData();

    Intent intent=new Intent(Intent.ACTION_VIEW);

    //新栈中打开
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
        //区别于 FLAG_GRANT_READ_URI_PERMISSION 跟 FLAG_GRANT_WRITE_URI_PERMISSION，
        //PERSISTABLE_URI_PERMISSION URI权限会持久存在即使重启，直到明确的用 revokeUriPermission(Uri, int) 撤销。
        // 这个flag只提供可能持久授权。但是接收的应用必须调用ContentResolver的takePersistableUriPermission(Uri, int)方法实现
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                /*   | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION*/);
    }

    intent.setDataAndType(dataUri, "application/vnd.android.package-archive");

    startActivity(intent);

}
}