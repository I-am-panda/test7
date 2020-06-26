package com.example.test7.util;


import com.example.test7.entity.Dept;
import com.example.test7.entity.Tree;

import java.util.*;

public class DeptTree
{
    public static List<Tree> getChildPerms(List<Dept> list, int parentId)
    {
        List<Tree> returnList = new ArrayList<Tree>();
        for (Dept Dept : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if(Dept.getParentId()==parentId){
                Tree tree = fromDeptToTree(Dept);
                //开始递归，把所有菜单和当前菜单放入
                recursionFn(list, tree);
                returnList.add(tree);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<Dept> list, Tree t)
    {
        // 得到t的子节点列表
        List<Tree> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Tree tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<Tree> it = childList.iterator();
                while (it.hasNext())
                {
                    Tree n =  it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<Tree> getChildList(List<Dept> list, Tree t)
    {

        List<Tree> tlist = new ArrayList<Tree>();
        Iterator<Dept> it = list.iterator();
        while (it.hasNext())
        {
            Dept Dept = (Dept) it.next();
            if (Dept.getParentId() == t.getId())
            {
                Tree tree = fromDeptToTree(Dept);
                tlist.add(tree);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */

    private static boolean hasChild(List<Dept> list, Tree t)
    {
        int size = getChildList(list, t).size();
        if(size>0){
            return true;
        }else
        {
            return false;
        }
    }

    /**
     * 将Dept对象转换成tree对象
     * @param Dept
     * @return
     */
    private static Tree fromDeptToTree(Dept Dept){
        //构造tree对象
        Tree tree= new Tree();
        tree.setId(Dept.getDeptId());
        tree.setName(Dept.getDeptName());
        tree.setSpread(false);
        return tree;
    }
}