package com.example.ssd2149.ilovezappos;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ssd2149.ilovezappos.data.model.Product;
import com.example.ssd2149.ilovezappos.databinding.ActivitySearchBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchActivity extends AppCompatActivity implements FetchDataCallBackInterface {

    public String data;
    ImageView image;
    public Product product2;
    Product product;
    Context context;
    FloatingActionButton fb;
    boolean isFabopen= false;
    Animation rotate_forward, rotate_backward;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        new SendGetRequest(this).execute();
        ActivitySearchBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setProduct(product2);
        fb= (FloatingActionButton) findViewById(R.id.fab);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                animateFAB();
            }
        });
    }

    public void FetchDataCallBack(Product product) {
        product2.setProductName(product.getProductName());
        product2.setBrandname(product.getBrandname());
        product2.setProductUrl(product.getProductUrl());
        product2.setColorId(product.getColorId());
        product2.setStyleId(product.getStyleId());
        product2.setOriginalPrice(product.getOriginalPrice());
        product2.setPercentOff(product.getPercentOff());
        product2.setPrice(product.getPrice());
        product2.setProductId(product.getProductId());
        product2.setThumbnailImageUrl(product.getThumbnailImageUrl());
        System.out.println("product:"+product2.getBrandname());
    }


    class SendGetRequest extends AsyncTask<String, Void, String> {
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn;
        FetchDataCallBackInterface callbackInterface;

        public SendGetRequest(FetchDataCallBackInterface callbackInterface) {
            this.callbackInterface = callbackInterface;
        }

        protected void onPreExecute() {
            product2 = new Product(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
            product = new Product(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
        }

        protected String doInBackground(String... arg0) {
            try {

                URL url = new URL("https://api.zappos.com/Search?term=&key=b743e26728e16b81da139182bb2094357c31d331");
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           finally {
                           conn.disconnect();
                     }
            return result.toString();
        }

        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.i("Info", response);
            Intent intent = getIntent();
            data=response;
            String name;
            String key= "Hello";
            int i;
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                String query = intent.getStringExtra(SearchManager.QUERY);
                try {
                    JSONObject jobj = new JSONObject(data);
                    JSONArray result = jobj.getJSONArray("results");
                    for (i = 0; i < result.length(); i++) {
                        JSONObject rec = result.getJSONObject(i);
                        name = rec.getString("brandName");
                        System.out.println("name"+name);
                        System.out.println("query"+query);
                        if (query.equals(name)) {
                            product2.setBrandname(rec.getString("brandName"));
                            product2.setThumbnailImageUrl(rec.getString("thumbnailImageUrl"));
                            product2.setProductId(rec.getString("productId"));
                            product2.setOriginalPrice(rec.getString("originalPrice"));
                            product2.setStyleId(rec.getString("styleId"));
                            product2.setColorId(rec.getString("colorId"));
                            product2.setPrice(rec.getString("price"));
                            product2.setPercentOff(rec.getString("percentOff"));
                            product2.setProductUrl(rec.getString("productUrl"));
                            product2.setProductName(rec.getString("productName"));
                            break;
                        }
                        else
                        {
                            System.out.println("Didn't work");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.callbackInterface.FetchDataCallBack(product2);
            }
        }
    }
    public void animateFAB(){

        if(isFabopen){

            fb.startAnimation(rotate_forward);
            isFabopen = false;
            System.out.println(isFabopen);
            Toast.makeText(SearchActivity.this, "Added into the cart", Toast.LENGTH_SHORT).show();

        } else {

            fb.startAnimation(rotate_backward);
            isFabopen = true;
            System.out.println(isFabopen);
            Toast.makeText(SearchActivity.this, "Removed from the cart", Toast.LENGTH_SHORT).show();

        }
    }

}