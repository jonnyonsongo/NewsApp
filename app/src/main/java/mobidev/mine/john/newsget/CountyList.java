package mobidev.mine.john.newsget;

/**
 * Created by JOHN on 26/06/2016.
 */
public class CountyList {

    private int news_id;
    private String county_type;
    private String county_title;
    private String county_slug;
    private String county_content;
    private String county_image;
    private String county_date;

    public CountyList(int news_id, String county_type, String county_title, String county_slug, String county_content, String county_image, String county_date)
    {
        this.news_id = news_id;
        this.county_type = county_type;
        this.county_title = county_title;
        this.county_slug = county_slug;
        this.county_content = county_content;
        this.county_image = county_image;
        this.county_date = county_date;
    }

    /***********
     * Set Methods
     ******************/

    public void setCountyId(int news_id){
        this.news_id = news_id;
    }

    public void setCountyType(String county_type){
        this.county_type = county_type;
    }

    public void setCountyTitle(String county_title){
        this.county_title = county_title;
    }

    public void setCountySlug(String county_slug){
        this.county_slug = county_slug;
    }

    public void setCountyContent(String county_content){
        this.county_content = county_content;
    }

    public void setCountyImage(String county_image){
        this.county_image = county_image;
    }

    public void setCountyDate(String county_date){
        this.county_date = county_date;
    }

    /***********
     * Get Methods
     ****************/

    public int getNewsId(){
        return news_id;
    }

    public String getCountyType(){
        return county_type;
    }

    public String getCountyTitle(){
        return county_title;
    }

    public String getCountySlug(){
        return county_slug;
    }

    public String getCountyContent(){
        return county_content;
    }

    public String getCountyImage(){
        return county_image;
    }

    public String getCountyDate(){
        return county_date;
    }

}

