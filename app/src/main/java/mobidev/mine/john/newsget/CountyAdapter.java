package mobidev.mine.john.newsget;

/**
 * Created by JOHN on 26/06/2016.
 */

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CountyAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    private Context context;
    private ArrayList<CountyList> CustomListViewValuesArr = new ArrayList<CountyList>();



    public CountyAdapter(Context context, ArrayList<CountyList> CustomListViewValuesArr){
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
            view = LayoutInflater.from(context).inflate(R.layout.county_tab, parent, false);


            viewHolder = new ViewHolder();
            viewHolder.txtCountyId = (TextView) view.findViewById(R.id.textCountyId);
    //        viewHolder.txtCountyType = (TextView) view.findViewById(R.id.textCountyType);
            viewHolder.txtCountyTitle = (TextView) view.findViewById(R.id.textCountyTiTle);
    //        viewHolder.txtCountySlug = (TextView) view.findViewById(R.id.textCountySlug);
            viewHolder.txtCountyContent = (TextView) view.findViewById(R.id.textCountyContent);
            viewHolder.imageCountyImage = (ImageView) view.findViewById(R.id.imageViewCountyImage);
            viewHolder.txtCountyDate = (TextView) view.findViewById(R.id.textCountyDate);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CountyList county = CustomListViewValuesArr.get(position);
        System.err.println("de " + county.getNewsId());

        viewHolder.txtCountyId.setText(Integer.toString(county.getNewsId()));
    //    viewHolder.txtCountyType.setText(county.getCountyType());
        viewHolder.txtCountyTitle.setText(county.getCountyTitle());
    //    viewHolder.txtCountySlug.setText(county.getCountySlug());
        Picasso.with(context)
                .load(county.getCountyImage())
                //.resize(50, 50)
                //.centerCrop()
                .into(viewHolder.imageCountyImage);
        //  ImageLoader.DisplayImage(image_url, loader, viewHolder.imageNewsImage);
        viewHolder.txtCountyContent.setText(Html.fromHtml(county.getCountyContent(), null, new HtmlTagHandler()));
        viewHolder.txtCountyDate.setText(county.getCountyDate());
        return view;
    }

    private static class ViewHolder {
        TextView txtCountyId;
        //TextView txtCountyType;
        TextView txtCountyTitle;
        //TextView txtCountySlug;
        TextView txtCountyContent;
        ImageView imageCountyImage;
        TextView txtCountyDate;
    }
}
