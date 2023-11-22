package com.example.contactdatabase;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    final List<UserModel> listUser;

    CustomAdapter(List<UserModel> listUser) {
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listUser.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewUser;
        if (convertView == null) {
            viewUser = View.inflate(parent.getContext(), R.layout.list_item, null);
        } else viewUser = convertView;

        UserModel user = (UserModel) getItem(position);
        ((TextView) viewUser.findViewById(R.id.idUser)).setText(String.format("ID = %d", user.id));
        ((TextView) viewUser.findViewById(R.id.nameUser)).setText(String.format("Hike Name : %s", user.name));
        ((TextView) viewUser.findViewById(R.id.email)).setText(String.format("date %s", user.email));
        ((TextView) viewUser.findViewById(R.id.dateOfBirth)).setText(String.format("date %s", user.dateOfBirth));

        ((ImageView) viewUser.findViewById(R.id.avatar)).setImageResource(R.drawable.istockphoto_1337144146_612x612);

        return viewUser;
    }
}
