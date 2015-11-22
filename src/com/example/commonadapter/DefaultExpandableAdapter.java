package com.example.commonadapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public abstract class DefaultExpandableAdapter<G, C> extends BaseExpandableListAdapter {

	private Context context;
	private List<G> group;
	private List<List<C>> children;

	public DefaultExpandableAdapter(Context context, List<G> group, List<List<C>> children) {
		super();
		this.context = context;
		this.group = group;
		this.children = children;
	}

	@Override
	public int getGroupCount() {

		return group.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return children.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return group.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return children.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.getViewHolder(context, convertView,
				parent, getGroupViewId());
		convertGroupview(viewHolder, group.get(groupPosition));
		return viewHolder.getConvertView();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.getViewHolder(context, convertView,
				parent, getChildrenViewId());
		convertChildrenView(viewHolder,
				children.get(groupPosition).get(childPosition));
		return viewHolder.getConvertView();
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public abstract int getGroupViewId();

	public abstract int getChildrenViewId();

	public abstract void convertGroupview(ViewHolder viewHolder, G t);

	public abstract void convertChildrenView(ViewHolder viewHolder, C m);
}
