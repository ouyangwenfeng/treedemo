package cn.com.oywf;


import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

/**
 * @author oywf on 2016/6/29.
 *         <p>
 *         TreeView item点击事件
 */
public class TreeViewItemClickListener implements OnItemClickListener {
    /**
     * adapter
     */
    private TreeViewAdapter treeViewAdapter;

    public TreeViewItemClickListener(TreeViewAdapter treeViewAdapter) {
        this.treeViewAdapter = treeViewAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        //点击的item节点
        TreeNode element = (TreeNode) treeViewAdapter.getItem(position);
        //树中的子元素
        ArrayList<TreeNode> elements = treeViewAdapter.getTreeNodes();
        //孩子节点
        ArrayList<TreeNode> elmentDatas = element.getChildrens();

        //点击没有子项的item直接返回
        if (elmentDatas == null || (elmentDatas != null && elmentDatas.size() == 0)) {
            return;
        }

        if (element.isExpanded()) {
            elements.removeAll(element.getExpandedChildNode());
            element.setExpanded(false);
            treeViewAdapter.notifyDataSetChanged();
        } else {
            elements.addAll(position + 1, element.getChildrens());
            element.setExpanded(true);
            treeViewAdapter.notifyDataSetChanged();
        }
    }

}
