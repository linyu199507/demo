package com.chinaweal.sdcs.crcs.util;

import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.TreeModel;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseUtil {

    private boolean flag = false;

    // 1.根据节点codeid从缓存中查找某节点及其所有子节点
    // 2.将查找的所有节点的codeid保存到basecodeFormCodeids中
    public List<String> findBasecodeFormCodeids(String codeid, List<Basecodeform> basecodeForms) {
        // 用于保存某节点及其所有子节点的codeid
        List<String> basecodeFormCodeids = new ArrayList<>();
        if (basecodeForms != null && !basecodeForms.isEmpty() && basecodeForms.size() > 0) {
            for (Basecodeform basecodeform : basecodeForms) {
                // 如果匹配到相等的codeid，则该节点即是要找的父节点
                if (codeid.equals(basecodeform.getCodeid()) && !flag) {
                    flag = true;//通知后续的递归函数，你们递归到的节点都是属于此节点的子节点
                    basecodeFormCodeids.add(basecodeform.getCodeid());
                    basecodeFormCodeids.addAll(findBasecodeFormCodeids(codeid, basecodeform.getBasecodeforms()));
                    flag = false;//通知后续的递归函数，你们递归到的节点不是属于此节点的子节点
                    break;//遍历完所有子节点后，跳出与父节点同级的其他节点搜索
                } else if (!codeid.equals(basecodeform.getCodeid()) && !flag) {
                    basecodeFormCodeids.addAll(findBasecodeFormCodeids(codeid, basecodeform.getBasecodeforms()));
                } else if (flag) {
                    basecodeFormCodeids.add(basecodeform.getCodeid());
                    basecodeFormCodeids.addAll(findBasecodeFormCodeids(codeid, basecodeform.getBasecodeforms()));
                }
            }
        }
        return basecodeFormCodeids;
    }

    /**
     * easy ui tree
     *
     * @param list
     * @return
     */
    public static TreeModel formatBaseCodeTree(List<Basecodeform> list) {
        /**
         * 查询出来的数据是要有规律的, 必须以树根树干到树叶形式
         */
        TreeModel root = null;
        TreeModel node = null;
        List<TreeModel> parentNodes = new ArrayList<TreeModel>();   //存放所有的父节点
        if (list != null && list.size() > 0) {
            root = formatTreeModel(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                node = formatTreeModel(list.get(i));
                if (StringUtils.isBlank(node.getPid()) || root.getId().equals(node.getPid())) { //父ID为空说明是第一级
                    //给默认节点加上子节点
                    parentNodes.add(node);
                    root.getChildren().add(node);
                } else {
                    getChildrenNodes(parentNodes, node);
                    parentNodes.add(node);
                }
            }
        }
        return root;
    }

    public static TreeModel formatTreeModel(Basecodeform vo) {
        if (vo == null) return null;
        TreeModel treeModel = new TreeModel();
        treeModel.setId(vo.getId());
        treeModel.setText(vo.getComment());
        treeModel.setPid(vo.getParentid());
        treeModel.setLevel(vo.getOrglevel());
        treeModel.setCodeid(vo.getCodeid());
        return treeModel;
    }

    public static void getChildrenNodes(List<TreeModel> parentNodes, TreeModel node) {
        if (parentNodes != null && node != null) {
            //遍历所有parentNodes和node进行匹配
            for (int i = parentNodes.size() - 1; i >= 0; i--) {
                TreeModel pnode = parentNodes.get(i);
                //如果是父子关系, 为父节点添加子节点, 退出循环
                if (pnode.getId().equals(node.getPid())) {
                    pnode.setState("closed");
                    pnode.getChildren().add(node);
                    return;
                }
            }
        }
    }
}
