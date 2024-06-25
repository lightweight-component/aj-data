package com.ajaxjs.data.entity.tree;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 常见树结构的方法
 */
public class TreeCommonService {
    private static final String PID_FIELD = "pId";

    /**
     * Map 里面每个元素是树节点，都有 id、pid 字段，pid 是指向父亲节点，
     * 如何求得这个节点下面没有 child，即 isLeaf = true，并将这 isLeaf 设置到 map
     *
     * @param tree 树
     */
    public static void setLeafFlag(List<Map<String, Object>> tree) {
        setLeafFlag(tree, "isLeaf");
    }

    /**
     * Map 里面每个元素是树节点，都有 id、pid 字段，pid 是指向父亲节点，
     * 如何求得这个节点下面没有 child，即 isLeaf = true，并将这 isLeaf 设置到 map
     *
     * @param tree        树
     * @param isLeafField 是否叶子的字段名
     */
    public static void setLeafFlag(List<Map<String, Object>> tree, String isLeafField) {
        // 如果一个节点的 id 在 parentIds 集合中找不到对应的元素，则可以认为该节点是叶子节点，因为没有其他节点引用它
        Set<Object> parentIds = new HashSet<>();

        for (Map<String, Object> node : tree)
            parentIds.add(node.get(PID_FIELD));

        for (Map<String, Object> node : tree) {
            boolean contains = parentIds.contains(node.get("id"));

            node.put(isLeafField, !contains);
        }
    }

    /**
     * 设置树中叶子节点的标志。
     * 通过遍历树节点列表，判断每个节点是否为叶子节点。叶子节点定义为没有子节点的节点。
     * 该方法通过记录所有节点的父节点ID，然后检查每个节点是否在父节点ID集合中，来确定节点是否为叶子节点。
     * 如果一个节点的ID在父节点ID集合中，说明它有父节点，因此不是叶子节点；反之，则是叶子节点。
     *
     * @param tree 树节点列表，每个节点包含ID和父节点ID信息。
     */
    public static void setTreeLeafFlag(List<TreeNode> tree) {
        // 使用HashSet存储所有父节点的ID，以便高效地进行包含检查
        Set<Long> parentIds = new HashSet<>();

        // 遍历树节点列表，收集所有父节点的ID
        for (TreeNode node : tree)
            parentIds.add(node.getParentId());

        // 再次遍历树节点列表，判断每个节点是否为叶子节点
        for (TreeNode node : tree) {
            boolean contains = parentIds.contains(node.getId());// 检查当前节点的ID是否在父节点ID集合中
            node.setIsLeaf(!contains); // 设置节点的叶子状态。如果当前节点的ID在父节点ID集合中，说明它不是叶子节点；反之，则是叶子节点
        }
    }
}