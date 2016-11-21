package com.example.ludovic.eatnow;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ludovic Zipsin on 20/11/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */

public class JsonRequestHelper extends JsonObjectRequest {

    private Priority priority;

    public JsonRequestHelper(int method,
                             String url,
                             JSONObject jsonObject,
                             Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener
    ) {
        super(method, url, jsonObject, listener, errorListener);
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public Priority getPriority() {
        return priority == null ? Priority.NORMAL : priority;
    }

    @Override
    public Map getHeaders() throws AuthFailureError {
        Map headers = new HashMap();

        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            if (response.data.length == 0) {
                byte[] responseData = "{}".getBytes("UTF8");
                response = new NetworkResponse(response.statusCode, responseData, response.headers, response.notModified);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return super.parseNetworkResponse(response);
    }
}