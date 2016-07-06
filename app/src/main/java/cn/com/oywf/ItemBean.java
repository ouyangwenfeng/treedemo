package cn.com.oywf;

import java.io.Serializable;

/**
 * @author oywf on 2016/6/28.
 */
public class ItemBean implements Serializable {

    private String name;
    private int id;

    /**
     * 用户是否已存在，0 是存在。1是不存在
     */
    private int exist = 1;


//    /**
//     * 是否能被选中
//     */
//    private boolean isCanSelect;
//
//    /**
//     * 是否已选中
//     */
//    private boolean isSelect;


//    /**
//     * 存在就不可被点击
//     *
//     * @return
//     */
//    public boolean isCanSelect() {
//        return exist == 0 ? false : true;
//    }
//
//    public void setCanSelect(boolean canSelect) {
//        isCanSelect = canSelect;
//    }
//
//    public boolean isSelect() {
//        return isSelect;
//    }
//
//    public void setSelect(boolean select) {
//        isSelect = select;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }


    @Override
    public String toString() {
        return "ItemBean{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", exist=" + exist +
                '}';
    }
}
