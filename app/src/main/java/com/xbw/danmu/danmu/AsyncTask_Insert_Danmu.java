package com.xbw.danmu.danmu;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xubowen on 16/7/27.
 */
public class AsyncTask_Insert_Danmu extends AsyncTask<String, String, String> {
    // mysql
    JSONParser jsonParser = new JSONParser();
    private static String url_up = Config.mysql_url_insert_danmu;
    Context context;
    ProgressDialog dialog;

    public AsyncTask_Insert_Danmu(Context context) {
        this.context = context;
    }

    // ///////////////////////////////////////////////////////////////////////////////////
    public interface MysqlListeners { // /
        public void Success(); // /

        public void Fail(); // /
    } // /

    private MysqlListeners mysqlListener = null; // /

    public void setMysqlListeners(MysqlListeners mysqlListener) { // /
        this.mysqlListener = mysqlListener; // /
    } // /
    // /

    // ///////////////////////////////////////////////////////////////////////////////////
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = Progress_Dialog.CreateProgressDialog(context);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... p) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String message = null;
        params.add(new BasicNameValuePair("user", p[0]));
        params.add(new BasicNameValuePair("danmu_content", p[1]));
        try {
            JSONObject json = jsonParser
                    .makeHttpRequest(url_up, "POST", params);
            message = json.getString("message");
        } catch (Exception e) {
            e.printStackTrace();
            message = "NONET";
        }
        return message;
    }

    protected void onPostExecute(String message) {
        dialog.dismiss();
        if (mysqlListener != null) {
            if (message.equals("YES")) {
                mysqlListener.Success();
            } else {
                mysqlListener.Fail();
            }
        }
        if (message.equals("NONET")) {
            Toast.makeText(context, "网络连接失败", 8000).show();
        } else if (message.equals("YES")) {
            Toast.makeText(context, "发送成功", 8000).show();
        } else {
            Toast.makeText(context, "发送失败", 8000).show();
        }
    }
}
