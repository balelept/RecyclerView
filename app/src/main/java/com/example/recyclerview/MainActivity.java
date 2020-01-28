package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImagesUrls = new ArrayList<>();
    private ArrayList<String> mImageDesc = new ArrayList<>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started.");

        mQueue = Volley.newRequestQueue(this);

       jsonParse();


    }

    private void jsonParse(){
        String url = "http://192.168.1.68:3000/lendas";
        Log.d(TAG, "xx");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "aa");
                        try {
                            JSONArray jsonArray = response.getJSONArray("lendas");
                            Log.d(TAG, "bb");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Log.d(TAG, "cc");
                                JSONObject lenda = jsonArray.getJSONObject(i);

                                String nome = lenda.getString("nome");
                                String desc = lenda.getString("descricao");
                                String imagem = lenda.getString("imagem");

                                mImagesUrls.add("https://www.sulinformacao.pt/wp-content/uploads/2017/04/Faromarina4_anamadeira-1024x683.jpg");
                                mNames.add(nome);
                                mImageDesc.add(desc);
                            }
                                initRecyclerView();

                        } catch (JSONException e) {
                            Log.d(TAG, "dd");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private  void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init reciclerView");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImagesUrls, mImageDesc);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
