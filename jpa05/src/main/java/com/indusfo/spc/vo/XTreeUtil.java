package com.indusfo.spc.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: jpa05
 * @Package: com.indusfo.spc.vo
 * @ClassName: XTreeUtil
 * @Author: 熊冰
 * @Description: 树的封装
 * @Date: 2019/9/9 13:45
 * @Version: 1.0
 */
public class XTreeUtil {

    List<Object> ids = new ArrayList<>();
    List<Object> parents = new ArrayList<>();
    List<Object> childs = new ArrayList<>();



    public List<Integer> getIds() {
        List<Integer> rel=new ArrayList<>();
        for (Object obj:ids
             ) {
            rel.add((Integer) obj);
        }
        return rel;
    }

    public List<Integer> getParents() {
        List<Integer> rel=new ArrayList<>();
        for (Object obj:parents
        ) {
            rel.add((Integer) obj);
        }
        return rel;
    }

    public List<Integer> getChilds() {
        List<Integer> rel=new ArrayList<>();
        for (Object obj:childs
        ) {
            rel.add((Integer) obj);
        }
        return rel;
    }

    public XTreeUtil(Integer FINDID, List<XTreeNode> list){
        //获取当前节点数据 开始
          XTreeNode xTreeNode = new XTreeNode();
        //数据全查
        for (XTreeNode b : list) {
            if (b.getId().equals(FINDID)) {
                xTreeNode = b;
            }
        }
//        System.out.println("当前节点：" + xTreeNode);
        //获取当前节点数据 结束

        //获取父节点 开始
        //树list
//        System.out.println("没有父节点"+list);
//        System.out.println("没有父节点"+xTreeNode);
            if (!xTreeNode.getpId().equals(0)) {
//            parents = FindParent(parents, blyytype);
                parents = FindParent(parents, xTreeNode, list);
            } else {
                System.out.println("没有父节点");
            }

//        System.out.println("父节点:" + parents);

        //获取父节点 结束
        ids.add(FINDID);
        ids.addAll(parents);
//        System.out.println(ids);
        //获取子类树 开始
        List<XTreeNode> tree = new ArrayList<>();//顶级树
        List<XTreeNode> nodes = new ArrayList<>();//子集树

        //生成树

            for (XTreeNode _MouldType : list) {
                XTreeNode node = new XTreeNode();
                node.setId(_MouldType.getId());
                node.setpId(_MouldType.getpId().equals(0) ? null : _MouldType.getpId());
                node.setTitle(_MouldType.getTitle());
                nodes.add(node);
            }

        tree = XCommonUtil.getTree(nodes, null);
        //生成树 结束
//        System.out.println("完整树:" + tree);
        //获取子类树
        XTreeNode treeNode=new XTreeNode();
          treeNode = FindNode(ids, tree, ids.size() - 1, new XTreeNode());
//        System.out.println("子集树:" + treeNode);
        //获取子类树 结束


        //获取子类树 节点 开始
        if(treeNode.getId().equals(null)){
            System.out.println("没有子节点");
        }else{
            childs=FindChild(childs,treeNode);
        }

        Collections.reverse(ids);
//        parents.remove(FINDID);
        ids.remove(FINDID);
        ids.addAll(childs);
        System.out.println("父节点:" + parents);
        System.out.println("子节点:" + childs);
        System.out.println("全部节点:" + ids);
    }









    //获取树的父节点
    private List<Object> FindParent(List<Object> ids, XTreeNode type, List<XTreeNode> list) {
        XTreeNode blyytype = new XTreeNode();
        for (XTreeNode x : list) {
            if (x.getId().equals(type.getpId())) {
                blyytype = x;
            }
        }
//        System.out.println(blyytype);
        ids.add(blyytype.getId());
        if (!blyytype.getpId().equals(0)) {
            ids = FindParent(ids, blyytype, list);
        }
        return ids;
    }

    //获取子类树
    private XTreeNode FindNode(List<Object> ids, List<XTreeNode> t, Integer i, XTreeNode tree) {
        if (t.size() != 0) {
            for (XTreeNode xTreeNode : t) {

                if (xTreeNode.getId().equals(ids.get(i))) {
                    tree = xTreeNode;
                    if (i - 1 >= 0) {
                        tree = FindNode(ids, xTreeNode.getChildren(), i - 1, tree);
                    }
                }
            }
        }
        return tree;
    }

    //获取树的子节点
    private List<Object> FindChild(List<Object> ids, XTreeNode t) {
        //包含自身节点
        ids.add(t.getId());
        if (!t.getChildren().equals(null)) {
            List<XTreeNode> childs = t.getChildren();
            for (XTreeNode tt : childs
            ) {
                //不包含自身节点
//                ids.add(tt.getId());
                ids = FindChild(ids, tt);
            }
        }
        return ids;
    }


}
