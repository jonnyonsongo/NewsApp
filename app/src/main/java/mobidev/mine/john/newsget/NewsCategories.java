package mobidev.mine.john.newsget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewsCategories extends AppCompatActivity {

    Button national,county,sports,business,entertainment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_categories);

        national = (Button)findViewById(R.id.buttonNational);
        national.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsCategories.this, MainActivity.class));
            }
        });
        county = (Button)findViewById(R.id.buttonCounty);
        county.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsCategories.this, CountyNews.class));
            }
        });
        sports = (Button)findViewById(R.id.buttonSports);
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsCategories.this, SportsNews.class));
            }
        });
        business = (Button)findViewById(R.id.buttonBusiness);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsCategories.this, BusinessNews.class));
            }
        });
        entertainment = (Button)findViewById(R.id.buttonEntertainment);
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsCategories.this, EntertainmentNews.class));
            }
        });
    }
}
