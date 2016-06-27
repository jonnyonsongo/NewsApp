package mobidev.mine.john.newsget;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BusinessNews extends AppCompatActivity {

    private ProgressDialog mProgress;

    ListView list;
    BusinessAdapter adapter;
    public  BusinessNews CustomListView = null;
    public ArrayList<BusinessList> CustomListViewValuesArr = new ArrayList<BusinessList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button refresh;
        ImageButton Home;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_news);

        Home = (ImageButton)findViewById(R.id.imageButtonBusinessHome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BusinessNews.this, NewsCategories.class));
            }
        });

        refresh = (Button)findViewById(R.id.buttonBusinessRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    showProgress();
                    fetchDataFromRemoteServer();
                }
                else
                {
                    Toast.makeText(BusinessNews.this, "Check your internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (isNetworkAvailable()) {
            showProgress();
            fetchDataFromRemoteServer();
        }
        else
        {
            Toast.makeText(BusinessNews.this, "Check your internet connectivity", Toast.LENGTH_SHORT).show();
        }

        CustomListView = this;


        list= ( ListView )findViewById( R.id.listBusiness );  // List defined in XML ( See Below )


    }

    private void fetchDataFromRemoteServer() {
        new NetworkAsyncTask().execute("http://testing.mlab-training.devs.mobi/php_news_api/news.php?news_type_id=3");
    }

    /****** Function to set data in ArrayList *************/


    private class NetworkAsyncTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... myUrl)
        {
            try
            {
                URL url = new URL(myUrl[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.connect();

                InputStream in = conn.getInputStream();
                int status = conn.getResponseCode();
                StringBuilder stringBuilder = new StringBuilder();
                switch (status){
                    case 200:
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null){
                            stringBuilder.append(line);
                        }
                        reader.close();
                        return stringBuilder.toString();
                }

            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result){
            try {
                JSONObject response = new JSONObject(result);
                JSONArray newsArray = response.getJSONArray("news");
                for (int i = 0; i < newsArray.length(); i++){
                    JSONObject newsItem = newsArray.getJSONObject(i);
                    int news_id = newsItem.getInt("id");
                    String news_type = newsItem.getString("news_type");
                    String news_title = newsItem.getString("title");
                    String news_slug = newsItem.getString("slug");
                    String news_content = newsItem.getString("content");
                    String news_image = newsItem.getString("image_url");
                    String news_date = newsItem.getString("created_at");

                    BusinessList obj = new BusinessList(news_id,news_type,news_title,news_slug,news_content,news_image,news_date);

                    CustomListViewValuesArr.add(obj);
                    adapter = new BusinessAdapter(getApplicationContext(), CustomListViewValuesArr);

                    addToAdapter();
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            stopProgress();
        }
    }

    private void addToAdapter() {
        adapter = new BusinessAdapter(getApplicationContext(), CustomListViewValuesArr);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

    }

    private void stopProgress() {
        mProgress.cancel();
    }

    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        BusinessList tempValues = ( BusinessList ) CustomListViewValuesArr.get(mPosition);


        // SHOW ALERT

        Toast.makeText(CustomListView, ""+tempValues.getCompanyName() +" Image:"+tempValues.getImage() +" Url:"+tempValues.getUrl(),
                Toast.LENGTH_LONG).show();
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void showProgress() {
        mProgress = ProgressDialog.show(BusinessNews.this, "Please Wait", "Accessing server...");
    }
}
