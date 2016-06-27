package mobidev.mine.john.newsget;

import android.widget.ImageView;

/**
 * Created by JOHN on 23/06/2016.
 */
public class ListModel {

    private int news_id;
    private String news_type;
    private String news_title;
    private String news_slug;
    private String news_content;
    private String news_image;
    private String news_date;

    private String CompanyName = "";
    private String Image = "";
    private String Url = "";

    public ListModel(int news_id, String news_type, String news_title, String news_slug, String news_content, String news_image, String news_date)
    {
        this.news_id = news_id;
        this.news_type = news_type;
        this.news_title = news_title;
        this.news_slug = news_slug;
        this.news_content = news_content;
        this.news_image = news_image;
        this.news_date = news_date;
    }

    /***********
     * Set Methods
     ******************/

    public void setNewsId(int news_id){
        this.news_id = news_id;
    }

    public void setNewsType(String news_type){
        this.news_type = news_type;
    }

    public void setNewsTitle(String news_title){
        this.news_title = news_title;
    }

    public void setNewsSlug(String news_slug){
        this.news_slug = news_slug;
    }

    public void setNewsContent(String news_content){
        this.news_content = news_content;
    }

    public void setNewsImage(String news_image){
        this.news_image = news_image;
    }

    public void setNewsDate(String news_date){
        this.news_date = news_date;
    }



    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    /***********
     * Get Methods
     ****************/

    public int getNewsId(){
        return news_id;
    }

    public String getNewsType(){
        return news_type;
    }

    public String getNewsTitle(){
        return news_title;
    }

    public String getNewsSlug(){
        return news_slug;
    }

    public String getNewsContent(){
        return news_content;
    }

    public String getNewsImage(){
        return news_image;
    }

    public String getNewsDate(){
        return news_date;
    }



    public String getCompanyName() {
        return this.CompanyName;
    }

    public String getImage() {
        return this.Image;
    }

    public String getUrl() {
        return this.Url;
    }
}
