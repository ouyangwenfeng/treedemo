package cn.com.oywf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button show;
    private ListView treeview;

    /**
     * 数据源
     */
    private ArrayList<TreeNode> root_elements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        treeview = (ListView) findViewById(R.id.treeview);
        show = (Button) findViewById(R.id.show);
        initTestData();

        final TreeViewAdapter treeViewAdapter = new TreeViewAdapter(MainActivity.this, root_elements);
        TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter);

        treeview.setAdapter(treeViewAdapter);
        treeview.setOnItemClickListener(treeViewItemClickListener);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("oywf", "选中的个数-->" + getSelectDatas().size() + "数据源-->" + getSelectDatas().toString());
            }
        });

    }


    private void initTestData() {
        root_elements = new ArrayList<>();

        //班级1
        //--老师
        //----老师名称1
        //----老师名称2
        //----老师名称3
        //--家长
        //----家长名称1
        //----家长名称2
        //----家长名称3
        //--学生
        //----学生名称1
        //----学生名称2
        //----学生名称3

        //班级2
        //--老师
        //----老师名称1
        //----老师名称2
        //----老师名称3
        //--家长
        //----家长名称1
        //----家长名称2
        //----家长名称3
        //--学生
        //----学生名称1
        //----学生名称2
        //----学生名称3

        for (int i = 0; i < 3; i++) {
            //第一层数据
            ItemBean class1 = new ItemBean();
            class1.setId(i);
            class1.setName("班级" + i);
            TreeNode classNode1 = new TreeNode();
            classNode1.setParent(null);
            classNode1.setData(class1);

            ArrayList<TreeNode> classMembers = new ArrayList<>();

            //第二层数据
            ItemBean teachers = new ItemBean();
            teachers.setId(3);
            teachers.setName("老师");
            TreeNode teachersNode = new TreeNode();
            teachersNode.setData(teachers);
            teachersNode.setParent(classNode1);

            ItemBean parents = new ItemBean();
            parents.setId(4);
            parents.setName("家长");
            TreeNode parentsNode = new TreeNode();
            parentsNode.setData(parents);
            parentsNode.setParent(classNode1);

            ItemBean childrens = new ItemBean();
            childrens.setId(5);
            childrens.setName("学生");
            TreeNode childrensNode = new TreeNode();
            childrensNode.setData(childrens);
            childrensNode.setParent(classNode1);

            classMembers.add(teachersNode);
            classMembers.add(parentsNode);
            classMembers.add(childrensNode);

            classNode1.setChildrens(classMembers);

            //第三层数据
            ArrayList<TreeNode> membersNode1 = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ItemBean member = new ItemBean();
                member.setId(10 + j);
                member.setName("测试数据" + j);
                member.setExist(1);
                TreeNode memberNode = new TreeNode();
                memberNode.setData(member);
                memberNode.setParent(teachersNode);
                memberNode.setChildrens(null);
                membersNode1.add(memberNode);
            }

            ArrayList<TreeNode> membersNode2 = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                ItemBean member = new ItemBean();
                member.setId(10 + i);
                member.setName("测试数据" + i);
                TreeNode memberNode = new TreeNode();
                memberNode.setData(member);
                memberNode.setParent(parentsNode);
                memberNode.setChildrens(null);
                membersNode2.add(memberNode);
            }

            ArrayList<TreeNode> membersNode3 = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ItemBean member = new ItemBean();
                member.setId(i + j);
                member.setName("测试数据" + i);
                TreeNode memberNode = new TreeNode();
                memberNode.setData(member);
                memberNode.setParent(childrensNode);
                memberNode.setChildrens(null);
                membersNode3.add(memberNode);
            }
            //组织树形结构关系
            teachersNode.setChildrens(membersNode1);
            parentsNode.setChildrens(membersNode2);
            childrensNode.setChildrens(membersNode3);
            root_elements.add(classNode1);
        }


    }

    private ArrayList<ItemBean> getSelectDatas() {
        ArrayList<ItemBean> itemBeens = new ArrayList<>();
        Log.i("oywf", "最后一级节点个数-->" + getLastLeafNode().size());
        for (TreeNode item : getLastLeafNode()) {
            if (item.isCanSelect() && item.isSelect()) {
                itemBeens.add(item.getData());
            }
        }
        return itemBeens;
    }


    private ArrayList<TreeNode> getLastLeafNode() {
        ArrayList<TreeNode> arrNode = new ArrayList<>();
        for (TreeNode item : root_elements) {
            if (item.getParent() == null) {
                arrNode.addAll(item.getLeafNode());
            }
        }
        return arrNode;

    }


}
