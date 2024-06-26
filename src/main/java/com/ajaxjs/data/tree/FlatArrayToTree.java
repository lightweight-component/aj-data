package com.ajaxjs.data.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一维列表转换为 Tree 结构
 * 扁平化的列表转换为 Tree 结构
 */
public class FlatArrayToTree extends BaseTreeStrut {
    /**
     * 将列表 Map 数据转换为树状结构的列表。
     * 此方法适用于具有父子关系的数据结构，通过指定ID类型和节点列表，将扁平化的数据转换为树形结构。
     *
     * <p>
     * 方法内部通过构建一个映射来加速节点查找，然后遍历节点列表，根据每个节点的父ID将其链接到相应的父节点上。
     * 如果节点的父ID指向根节点（即最顶级的节点），则将其直接添加到结果列表中。
     *
     * @param idType 节点ID的类型，用于泛型参数，确保类型安全
     * @param nodes  节点列表，包含所有需要转换的数据
     * @return 转换后的树状结构列表。
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> List<Map<String, Object>> mapAsTree(Class<T> idType, List<Map<String, Object>> nodes) {
        // 使用映射存储所有节点，以ID为键，节点对象为值，加快后续节点查找速度
        // 扁平化的列表转换为 tree 结构
        Map<T, Map<String, Object>> parents = new HashMap<>();

        // 遍历节点列表，将每个节点按ID存储到 Map 中
        for (Map<String, Object> node : nodes)
            parents.put((T) node.get(getIdField()), node);

        // 初始化结果列表，用于存储转换后的树状结构
        List<Map<String, Object>> tree = new ArrayList<>();

        // 再次遍历节点列表，根据每个节点的父ID将其链接到树中
        for (Map<String, Object> node : nodes) {
            T parentId = (T) node.get(getParentIdField());

            // 判断节点的父ID是否为根节点，如果是，则直接将其添加到结果列表
            if (!parentId.equals(getTopNodeValue())) {
                Map<String, Object> parent = parents.get(parentId);
                Object _children = parent.get(getChildrenField());
                List<Map<String, Object>> children;

                // 如果父节点的“子节点”字段为空，则初始化为新的列表，并添加到父节点
                if (_children == null) {
                    children = new ArrayList<>();
                    parent.put(getChildrenField(), children);
                } else
                    // 如果父节点的“子节点”字段已初始化，则直接将其转换为列表
                    children = (List<Map<String, Object>>) _children;

                children.add(node);// 将当前节点添加到父节点的“子节点”列表中
            } else
                tree.add(node);// 如果节点的父ID为根节点，则直接将其添加到结果列表的根节点位置
        }
        // 返回转换后的树状结构列表
        return tree;
    }
}