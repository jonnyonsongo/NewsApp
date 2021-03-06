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

public class SportsNews extends AppCompatActivity {

    private ProgressDialog mProgress;

    ListView list;
    SportsAdapter adapter;
    public  SportsNews CustomListView = null;
    public ArrayList<SportsList> CustomListViewValues = new ArrayList<SportsList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button refresh;
        ImageButton Home;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_news);

        Home = (ImageButton)findViewById(R.id.imageButtonSportsHome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportsNews.this, NewsCategories.class));
            }
        });

        refresh = (Button)findViewById(R.id.buttonSportsRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    showProgress();
                    fetchDataFromRemoteServer();
                }
                else
                {
                    Toast.makeText(SportsNews.this, "Check your internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (isNetworkAvailable()) {
            showProgress();
            fetchDataFromRemoteServer();
        }
        else
        {
            Toast.makeText(SportsNews.this, "Check your internet connectivity", Toast.LENGTH_SHORT).show();
        }

        CustomListView = this;


        list= ( ListView )findViewById( R.id.listSports );  // List defined in XML ( See Below )


    }

    private void fetchDataFromRemoteServer() {
        new NetworkAsyncTask().execute("http://testing.mlab-training.devs.mobi/php_news_api/news.php?news_type_id=4");
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
                    String county_type = newsItem.getString("news_type");
                    String county_title = newsItem.getString("title");
                    String county_slug = newsItem.getString("slug");
                    String county_content = newsItem.getString("content");
                    String county_image = newsItem.getString("image_url");
                    String county_date = newsItem.getString("created_at");

                    SportsList obj = new SportsList(news_id,county_type,county_title,county_slug,county_content,county_image,county_date);

                    CustomListViewValues.add(obj);
                    adapter = new SportsAdapter(getApplicationContext(), CustomListViewValues);

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
        adapter = new SportsAdapter(getApplicationContext(), CustomListViewValues);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

    }

    private void stopProgress() {
        mProgress.cancel();
    }

    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        SportsList tempValues = ( SportsList ) CustomListViewValues.get(mPosition);


        // SHOW ALERT

        Toast.makeText(CustomListView, ""+tempValues.getNewsTitle() +" Image:"+tempValues.getNewsImage() +" Url:"+tempValues.getNewsSlug(),
                Toast.LENGTH_LONG).show();
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void showProgress() {
        mProgress = ProgressDialog.show(SportsNews.this, "Please Wait", "Accessing server...");
    }
}

