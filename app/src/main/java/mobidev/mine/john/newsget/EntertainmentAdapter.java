package mobidev.mine.john.newsget;

/**
 * Created by JOHN on 27/06/2016.
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
public class EntertainmentAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    private Context context;
    private ArrayList<EntertainmentList> CustomListViewValuesArr = new ArrayList<EntertainmentList>();



    public EntertainmentAdapter(Context context, ArrayList<EntertainmentList> CustomListViewValuesArr){
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
            view = LayoutInflater.from(context).inflate(R.layout.entertainment_tab, parent, false);


            viewHolder = new ViewHolder();
            viewHolder.txtEntertainmentId = (TextView) view.findViewById(R.id.textEntertainmentId);
    //        viewHolder.txtEntertainmentType = (TextView) view.findViewById(R.id.textEntertainmentType);
            viewHolder.txtEntertainmentTitle = (TextView) view.findViewById(R.id.textEntertainmentTiTle);
    //        viewHolder.txtEntertainmentSlug = (TextView) view.findViewById(R.id.textEntertainmentSlug);
            viewHolder.txtEntertainmentContent = (TextView) view.findViewById(R.id.textEntertainmentContent);
            viewHolder.imageEntertainmentImage = (ImageView) view.findViewById(R.id.imageViewEntertainmentImage);
            viewHolder.txtEntertainmentDate = (TextView) view.findViewById(R.id.textEntertainmentDate);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        EntertainmentList entertainment = CustomListViewValuesArr.get(position);
        System.err.println("de " + entertainment.getNewsId());

        viewHolder.txtEntertainmentId.setText(Integer.toString(entertainment.getNewsId()));
    //    viewHolder.txtEntertainmentType.setText(entertainment.getNewsType());
        viewHolder.txtEntertainmentTitle.setText(entertainment.getNewsTitle());
    //    viewHolder.txtEntertainmentSlug.setText(entertainment.getNewsSlug());
        Picasso.with(context)
                .load(entertainment.getNewsImage())
                //.resize(50, 50)
                //.centerCrop()
                .into(viewHolder.imageEntertainmentImage);
        //  ImageLoader.DisplayImage(image_url, loader, viewHolder.imageNewsImage);
        viewHolder.txtEntertainmentContent.setText(Html.fromHtml(entertainment.getNewsContent(), null, new HtmlTagHandler()));
        viewHolder.txtEntertainmentDate.setText(entertainment.getNewsDate());
        return view;
    }

    private static class ViewHolder {
        TextView txtEntertainmentId;
    //    TextView txtEntertainmentType;
        TextView txtEntertainmentTitle;
    //    TextView txtEntertainmentSlug;
        TextView txtEntertainmentContent;
        ImageView imageEntertainmentImage;
        TextView txtEntertainmentDate;
    }
}
