package mobidev.mine.john.newsget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JOHN on 23/06/2016.
 */

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    private Context context;
    private ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();



    public CustomAdapter(Context context, ArrayList<ListModel> CustomListViewValuesArr){
        this.context = context;
        this.CustomListViewValuesArr = CustomListViewValuesArr;
    }

    @Override
    public int getCount()
    {
        return CustomListViewValuesArr.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return CustomListViewValuesArr.indexOf(getItem(position));
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.tabitem, parent, false);


            viewHolder = new ViewHolder();
            viewHolder.txtNewsId = (TextView) view.findViewById(R.id.textNewsId);
    //        viewHolder.txtNewsType = (TextView) view.findViewById(R.id.textNewsType);
            viewHolder.txtNewsTitle = (TextView) view.findViewById(R.id.textNewsTiTle);
    //        viewHolder.txtNewsSlug = (TextView) view.findViewById(R.id.textNewsSlug);
            viewHolder.txtNewsContent = (TextView) view.findViewById(R.id.textNewsContent);
            viewHolder.imageNewsImage = (ImageView) view.findViewById(R.id.imageViewNewsImage);
            viewHolder.txtNewsDate = (TextView) view.findViewById(R.id.textNewsDate);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListModel news = CustomListViewValuesArr.get(position);
        System.err.println("de " + news.getNewsId());

        viewHolder.txtNewsId.setText(Integer.toString(news.getNewsId()));
    //    viewHolder.txtNewsType.setText(news.getNewsType());
        viewHolder.txtNewsTitle.setText(news.getNewsTitle());
    //    viewHolder.txtNewsSlug.setText(news.getNewsSlug());
        Picasso.with(context)
                .load(news.getNewsImage())
                //.resize(50, 50)
                //.centerCrop()
                .into(viewHolder.imageNewsImage);
        //  ImageLoader.DisplayImage(image_url, loader, viewHolder.imageNewsImage);
        viewHolder.txtNewsContent.setText(Html.fromHtml(news.getNewsContent(), null, new HtmlTagHandler()));
        viewHolder.txtNewsDate.setText(news.getNewsDate());
        return view;
    }

    private static class ViewHolder {
        TextView txtNewsId;
    //    TextView txtNewsType;
        TextView txtNewsTitle;
    //    TextView txtNewsSlug;
        TextView txtNewsContent;
        ImageView imageNewsImage;
        TextView txtNewsDate;
    }
}


