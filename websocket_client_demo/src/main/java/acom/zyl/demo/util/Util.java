package acom.zyl.demo.util;

import android.content.Context;
import android.widget.Toast;

public class Util {
    public static final String ws = "ws://echo.websocket.org";//websocket测试地址
  //  public static final String ws = "ws://121.42.28.245:8381/ws";//websocket测试地址

    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
