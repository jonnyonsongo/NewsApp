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
public class BusinessAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    private Context context;
    private ArrayList<BusinessList> CustomListViewValuesArr = new ArrayList<BusinessList>();



    public BusinessAdapter(Context context, ArrayList<BusinessList> CustomListViewValuesArr){
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
            view = LayoutInflater.from(context).inflate(R.layout.business_tab, parent, false);


            viewHolder = new ViewHolder();
            viewHolder.txtBusinessId = (TextView) view.findViewById(R.id.textBusinessId);
      //      viewHolder.txtBusinessType = (TextView) view.findViewById(R.id.textBusinessType);
            viewHolder.txtBusinessTitle = (TextView) view.findViewById(R.id.textBusinessTiTle);
      //      viewHolder.txtBusinessSlug = (TextView) view.findViewById(R.id.textBusinessSlug);
            viewHolder.txtBusinessContent = (TextView) view.findViewById(R.id.textBusinessContent);
            viewHolder.imageBusinessImage = (ImageView) view.findViewById(R.id.imageViewBusinessImage);
            viewHolder.txtBusinessDate = (TextView) view.findViewById(R.id.textBusinessDate);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BusinessList business = CustomListViewValuesArr.get(position);
        System.err.println("de " + business.getNewsId());

        viewHolder.txtBusinessId.setText(Integer.toString(business.getNewsId()));
    //    viewHolder.txtBusinessType.setText(business.getNewsType());
        viewHolder.txtBusinessTitle.setText(business.getNewsTitle());
    //    viewHolder.txtBusinessSlug.setText(business.getNewsSlug());
        Picasso.with(context)
                .load(business.getNewsImage())
                //.resize(50, 50)
                //.centerCrop()
                .into(viewHolder.imageBusinessImage);
        //  ImageLoader.DisplayImage(image_url, loader, viewHolder.imageNewsImage);
        viewHolder.txtBusinessContent.setText(Html.fromHtml(business.getNewsContent(), null, new HtmlTagHandler()));
        viewHolder.txtBusinessDate.setText(business.getNewsDate());
        return view;
    }

    private static class ViewHolder {
        TextView txtBusinessId;
    //    TextView txtBusinessType;
        TextView txtBusinessTitle;
    //    TextView txtBusinessSlug;
        TextView txtBusinessContent;
        ImageView imageBusinessImage;
        TextView txtBusinessDate;
    }
}
