package cn.com.oywf;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author oywf on 2016/6/29.
 *         <p/>
 *         树形结构实体
 */
public class TreeNode implements Serializable {
    /**
     * 父节点对象
     */
    private TreeNode parent;
    /**
     * 当前节点数据
     */
    private ItemBean data;
    /**
     * 当前节点子节点
     */
    private ArrayList<TreeNode> childrens;
    /**
     * 是否能点击(默认能点击)
     */
    private boolean canSelect = true;
    /**
     * 是否已经选中
     */
    private boolean isSelect;
    /**
     * 是否已展开
     */
    private boolean isExpanded;

    private int selectNum;

    private int totalNum;


    private boolean isLastLevel;


    public TreeNode() {

    }


    public boolean isLastLevel() {

        return this.childrens != null;
    }

    public void setLastLevel(boolean lastLevel) {
        isLastLevel = lastLevel;
    }

    public int getSelectNum() {
        if (null == this.childrens || this.childrens.size() == 0) {
            if (this.isSelect()) {
                return 1;
            } else {
                return 0;
            }
        }
        int sumNum = 0;
        for (TreeNode item : this.getChildrens()) {
            sumNum += item.getSelectNum();
        }
        return sumNum;
    }


    public int getTotalNum() {
        if (null == this.childrens || this.childrens.size() == 0) {
            return 1;
        }
        int sumNum = 0;

        for (TreeNode item : this.getChildrens()) {
            sumNum += item.getTotalNum();
        }
        return sumNum;
    }


    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public ItemBean getData() {
        return data;
    }

    public void setData(ItemBean data) {
        this.data = data;
    }

    public ArrayList<TreeNode> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<TreeNode> childrens) {
        this.childrens = childrens;
    }


    /**
     * 判断当前节点是否可选
     *
     * @return
     */
    public boolean isTreeNodeCanSelect() {
        if (!this.isCanSelect()) {
            this.setSelect(true);
            setParentNodeisNeedSelect();
        }
        //当前节点是最后层级的节点
        if (null == this.childrens || this.childrens.size() == 0) {
            return isCanSelect();
        }
        //当前节点是父节点
        for (TreeNode item : this.getChildrens()) {
            if (item.isTreeNodeCanSelect()) {
                return true;
            }
        }
        return false;
    }

    public boolean isCanSelect() {
        return this.data.getExist() != 0;
    }


    public boolean isSelect() {
        if (null == this.childrens || this.childrens.size() == 0) {
            return isSelect;
        }
        for (TreeNode item : this.getChildrens()) {
            if (!item.isSelect()) {
                return false;
            }
        }
        return true;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    public void setTreeNodeSelect(boolean select) {
        //设置该节点下子节点的情况
        setAllChildNodeSelect(select);
        //设置该节点父节点的情况
        setParentNodeSelect();
    }

    /**
     * 递归设置子节点全选中或者全不选中
     * <p/>
     */
    public void setAllChildNodeSelect(boolean isSelect) {
        if (!this.isTreeNodeCanSelect()) {
            return;
        }
        this.isSelect = isSelect;
        //如果当前节点下没有子节点就返回
        if (null == this.getChildrens() || this.getChildrens().size() == 0) {
            return;
        }
        //有子节点，将子节点选中状态变成和父节点一样
        for (TreeNode item : this.getChildrens()) {
            item.setAllChildNodeSelect(isSelect);
        }

    }

    /**
     * 设置该节点父节点情况
     */
    public void setParentNodeSelect() {
        //如果当前节点没有父节点，说明是根节点 直接返回
        if (null == this.getParent()) {
            return;
        }
        if (!this.getParent().isTreeNodeCanSelect()) {
            return;
        }
        boolean isSelect = isParentChildNodeAllSelect();
        this.getParent().setSelect(isSelect);
        //节点被选中的时候设置里面的数据也是被选中
        this.getParent().setParentNodeSelect();
    }


    /**
     * 设置该节点父节点情况
     */
    public void setParentNodeisNeedSelect() {
        //如果当前节点没有父节点，说明是根节点 直接返回
        if (null == this.getParent()) {
            return;
        }
        boolean isSelect = isParentChildNodeAllSelect();
        this.getParent().setSelect(isSelect);
        this.getParent().setParentNodeisNeedSelect();
    }


    /**
     * 判断父节点下的所有子节点是否被选中
     *
     * @return
     */
    public boolean isParentChildNodeAllSelect() {
        for (TreeNode item : this.getParent().getChildrens()) {
            if (!item.isSelect()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 递归遍历所有孩子节点
     *
     * @return
     */
    public ArrayList<TreeNode> getExpandedChildNode() {
        if (!this.isExpanded) {
            return new ArrayList<>();
        }
        ArrayList<TreeNode> nodeArr = new ArrayList<>();
        nodeArr.addAll(this.getChildrens());
        for (TreeNode item : this.getChildrens()) {
            nodeArr.addAll(item.getExpandedChildNode());
        }
        return nodeArr;
    }

    /**
     * 获取该节点下最底层节点
     *
     * @return
     */
    public ArrayList<TreeNode> getLeafNode() {
        ArrayList<TreeNode> arrNode = new ArrayList<>();
        //当前节点是最后一层节点
        if (null == this.getChildrens() || this.getChildrens().size() == 0) {
            arrNode.add(this);
            return arrNode;
        }
        for (TreeNode item : this.childrens) {
            arrNode.addAll(item.getLeafNode());
        }
        return arrNode;

    }


}
