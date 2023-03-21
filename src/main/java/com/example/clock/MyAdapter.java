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

public class MyAdapter extends BaseAdapter {
    private List<Clock> listC;
    private List<User> listU;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, List<Clock> listC ,List<User> listU)
    {
        this.listC = listC;
        this.listU = listU;
        layoutInflater  = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listC.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    public Object getItemC(int position) {
        return listC.get(position);
    }
    public Object getItemU(int position) {
        return listU.get(position);
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
        Clock clock = (Clock) getItemC(position);
//        System.out.println(clock.getUsername());
        String most = String.valueOf(clock.getMost_day());
        viewHolder.t_username.setText(clock.getUsername());
        viewHolder.t_mostDay.setText(most);

        User user = (User) getItemU(position);

        String id = String.valueOf(user.getId());
        viewHolder.t_classroom.setText(user.getClassroom());
        viewHolder.t_id.setText(id);

        return convertView;
    }

     class ViewHolder{
        TextView t_username,t_mostDay,t_classroom,t_id;
        public ViewHolder(View view){
            t_username = view.findViewById(R.id.item_username);
            t_mostDay = view.findViewById(R.id.item_time);
            t_classroom = view.findViewById(R.id.item_classroom);
            t_id = view.findViewById(R.id.item_id);
        }
    }
}
