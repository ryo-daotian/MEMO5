package com.daotian.memo5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import static com.daotian.memo5.MainActivity.childImages;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groups;
    private List<List<String>> colors;

    public ExpandableAdapter(Context context,List<String> groups,List<List<String>> colors ) {
        this.context = context;
        this.groups = groups;
        this.colors = colors;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return colors.get(groupPosition).get(childPosition);
    }

    public Bitmap getChildImage(int groupPosition, int childPosition)
    {
        return childImages[groupPosition][childPosition];
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.child_imageview);

        imageView.setImageBitmap(getChildImage(groupPosition,childPosition));
        txtListChild.setTextColor(Color.parseColor("#E6D8B3"));
        txtListChild.setTypeface(null, Typeface.ITALIC);
        txtListChild.setText((CharSequence)getChild(groupPosition,childPosition));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return colors.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView groupText = convertView.findViewById(R.id.lblListItem);
        groupText.setTextColor(Color.parseColor("#C09933"));
        groupText.setTypeface(null, Typeface.BOLD);
        groupText.setTextSize(20);
        groupText.setText((CharSequence)getGroup(groupPosition));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
