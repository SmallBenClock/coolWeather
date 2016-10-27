package com.douwong.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zds .
 * on 2016/10/26  15:24
 * 描述:
 * 包名: com.douwong.coolweather.util
 */

public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listenr) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;

                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer buffer = new StringBuffer();
                    String response;
                    while ((response = reader.readLine()) != null) {
                        buffer.append(response);
                    }
                    if (listenr != null) {
                        listenr.onFinish(response);
                    }

                } catch (Exception e) {
                    if (listenr != null) {
                        listenr.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();


    }


    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }


}
