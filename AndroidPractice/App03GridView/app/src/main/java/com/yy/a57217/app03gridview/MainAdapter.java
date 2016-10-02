package com.yy.a57217.app03gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 57217 on 2016/7/26.
 */
public class MainAdapter extends BaseAdapter {
      private Context context;
      private String []names;
      private int []icons;


      public MainAdapter(Context context, String []names, int []icons){
          super();
          this.names=names;
          this.icons=icons;
          this.context=context;
      }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            view =View.inflate(context,R.layout.item_main,null);
        }
        ImageView imageView=(ImageView)view.findViewById(R.id.iv_item_icon);
        TextView textView=(TextView)view.findViewById(R.id.tv_item_name);
        imageView.setImageResource(icons[i]);
        textView.setText(names[i]);
        return view;
    }
}
