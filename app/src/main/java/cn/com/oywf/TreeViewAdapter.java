package cn.com.oywf;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * TreeViewAdapter
 */
public class TreeViewAdapter extends BaseAdapter {

    private Context context;

    /**
     * 子节点数据
     */
    private ArrayList<TreeNode> treeNodes;


    public TreeViewAdapter(Context context, ArrayList<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
        this.context = context;
    }


    public ArrayList<TreeNode> getTreeNodes() {
        return treeNodes;
    }

    @Override
    public int getCount() {
        return treeNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return treeNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.treeview_item, null);
            holder.item_checkbox = (CheckBox) convertView.findViewById(R.id.item_checkbox);
            holder.item_id = (TextView) convertView.findViewById(R.id.item_id);
            holder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.item_num = (TextView) convertView.findViewById(R.id.item_num);
            holder.item_arrow = (ImageView) convertView.findViewById(R.id.item_arrow);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TreeNode treeNode = treeNodes.get(position);
        final ItemBean bean = treeNode.getData();

        //边距设置
        if (treeNode.getParent() != null) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.item_checkbox.getLayoutParams();
            lp.setMargins(50, 0, 0, 0);
            holder.item_checkbox.setLayoutParams(lp);
        } else {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.item_checkbox.getLayoutParams();
            lp.setMargins(20, 0, 0, 0);
            holder.item_checkbox.setLayoutParams(lp);
        }

        //设置显示
        holder.item_id.setText(bean.getId() + "");
        holder.item_name.setText(bean.getName());

        //处理点击逻辑
        holder.item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (treeNode.isSelect()) {
                    treeNode.setSelect(false);
                    treeNode.setTreeNodeSelect(false);
                } else {
                    treeNode.setSelect(true);
                    treeNode.setTreeNodeSelect(true);
                }
                notifyDataSetChanged();
            }
        });

        //设置不能被点击的
        if (!treeNode.isTreeNodeCanSelect()) {
            holder.item_checkbox.setClickable(false);
        }
        holder.item_checkbox.setChecked(treeNode.isSelect());

        int selectNum = treeNode.getSelectNum();
        int totalNum = treeNode.getTotalNum();
        if (!treeNode.isLastLevel()) {
            holder.item_num.setVisibility(View.INVISIBLE);
        } else {
            holder.item_num.setVisibility(View.VISIBLE);
            //设置选中数字
            holder.item_num.setText(selectNum + "/" + totalNum);
        }


        if (treeNode.getChildrens() != null && treeNode.getChildrens().size() > 0 && !treeNode.isExpanded()) {
            holder.item_arrow.setImageResource(R.mipmap.app_common_arrow_up);
            holder.item_arrow.setVisibility(View.VISIBLE);
        } else if (treeNode.getChildrens() != null && treeNode.getChildrens().size() > 0 && treeNode.isExpanded()) {
            holder.item_arrow.setImageResource(R.mipmap.app_common_arrow_down);
            holder.item_arrow.setVisibility(View.VISIBLE);
        } else if (treeNode.getChildrens() == null || (treeNode.getChildrens() != null && treeNode.getChildrens().size() == 0)) {
            holder.item_arrow.setImageResource(R.mipmap.app_common_arrow_up);
            holder.item_arrow.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        CheckBox item_checkbox;
        TextView item_id;
        TextView item_name;
        TextView item_num;
        ImageView item_arrow;
    }


}
