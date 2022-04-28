package com.kitchen_anywhere.kitchen_anywhere.foodie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kitchen_anywhere.kitchen_anywhere.R;

public class FoodiecustomDistListAdapter extends ArrayAdapter {
    private String[] dishNames;
    private String[] dishSubtitle;
    private String[] dishimageid;
    private Activity context;

    public FoodiecustomDistListAdapter(Activity context, String[] dishNames, String[] dishSubtitle, String[] dishimageid) {
        super(context, R.layout.foodie_home_listview, dishNames);
        this.context = context;
        this.dishNames = dishNames;
        this.dishSubtitle = dishSubtitle;
        this.dishimageid = dishimageid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.foodie_home_listview, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.food_item_text_title);
        TextView textViewCapital = (TextView) row.findViewById(R.id.food_item_text_description);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.food_item_image);

        textViewCountry.setText(dishNames[position]);
        textViewCapital.setText(dishSubtitle[position]);
//
//        Bitmap mIcon_val;
//        try {
//
//
//            URL url = new URL(dishimageid[position]);
//            InputStream content = (InputStream)url.getContent();
//            Drawable d = Drawable.createFromStream(content , "src");
//            imageFlag.setImageDrawable(d);
//        } catch (IOException e) {
//            imageFlag.setImageResource(R.drawable.splash_logo);
//            e.printStackTrace();
//        }

        imageFlag.setImageResource(R.drawable.splash_logo);



        row.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Toast.makeText(context,dishNames[position],Toast.LENGTH_SHORT);
                System.out.println(dishNames[position]);
            }
        });
//        Thread thread = new Thread(new Runnable(){
//            public void run() {
//                try {
//
//                    URL url = new URL(dishimageid[position]);
//                    InputStream content = (InputStream)url.getContent();
//                    Drawable d = Drawable.createFromStream(content , "src");
//                    imageFlag.setImageDrawable(d);
//                } catch (Exception e) {
//                    imageFlag.setImageResource(R.drawable.splash_logo);
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();

        return  row;
    }
}
