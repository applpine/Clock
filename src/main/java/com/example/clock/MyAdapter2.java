package com.example.clock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.clock.ennty.Clock;
import com.example.clock.ennty.User;

import java.util.List;

public class MyAdapter2 extends BaseAdapter {
    private List<User> list;
    private LayoutInflater layoutInflater;

    public MyAdapter2(Context context, List<User> list )
    {
        this.list = list;
        layoutInflater  = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.itemlayout , null , false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //数据库
        User user = (User) getItem(position);

        String id = String.valueOf(user.getId());
        viewHolder.t_classroom.setText(user.getClassroom());
        viewHolder.t_id.setText(id);

        return convertView;
    }

    class ViewHolder{
        TextView t_classroom,t_id;
        public ViewHolder(View view){
            t_classroom = view.findViewById(R.id.item_classroom);
            t_id = view.findViewById(R.id.item_id);
        }
    }
}
