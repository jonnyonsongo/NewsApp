package mobidev.mine.john.newsget;

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

/**
 * Created by JOHN on 27/06/2016.
 */
public class SportsAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    private Context context;
    private ArrayList<SportsList> CustomListViewValuesArr = new ArrayList<SportsList>();



    public SportsAdapter(Context context, ArrayList<SportsList> CustomListViewValuesArr){
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
            view = LayoutInflater.from(context).inflate(R.layout.sports_tab, parent, false);


            viewHolder = new ViewHolder();
            viewHolder.txtSportsId = (TextView) view.findViewById(R.id.textSportsId);
    //        viewHolder.txtSportsType = (TextView) view.findViewById(R.id.textSportsType);
            viewHolder.txtSportsTitle = (TextView) view.findViewById(R.id.textSportsTiTle);
   //         viewHolder.txtSportsSlug = (TextView) view.findViewById(R.id.textSportsSlug);
            viewHolder.txtSportsContent = (TextView) view.findViewById(R.id.textSportsContent);
            viewHolder.imageSportsImage = (ImageView) view.findViewById(R.id.imageViewSportsImage);
            viewHolder.txtSportsDate = (TextView) view.findViewById(R.id.textSportsDate);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SportsList sports = CustomListViewValuesArr.get(position);
        System.err.println("de " + sports.getNewsId());

        viewHolder.txtSportsId.setText(Integer.toString(sports.getNewsId()));
    //    viewHolder.txtSportsType.setText(sports.getNewsType());
        viewHolder.txtSportsTitle.setText(sports.getNewsTitle());
    //    viewHolder.txtSportsSlug.setText(sports.getNewsSlug());
        Picasso.with(context)
                .load(sports.getNewsImage())
                //.resize(50, 50)
                //.centerCrop()
                .into(viewHolder.imageSportsImage);
        //  ImageLoader.DisplayImage(image_url, loader, viewHolder.imageNewsImage);
        viewHolder.txtSportsContent.setText(Html.fromHtml(sports.getNewsContent(), null, new HtmlTagHandler()));
        viewHolder.txtSportsDate.setText(sports.getNewsDate());
        return view;
    }

    private static class ViewHolder {
        TextView txtSportsId;
    //    TextView txtSportsType;
        TextView txtSportsTitle;
    //    TextView txtSportsSlug;
        TextView txtSportsContent;
        ImageView imageSportsImage;
        TextView txtSportsDate;
    }
}
