package com.ajaxjs.data.entity.tree;

/**
 * 一个树节点
 */
public interface TreeNode {
    /**
     * 设置实体的ID。
     * <p>
     * 该方法为默认方法，旨在提供一种统一的方式来设置实体对象的ID属性。
     * ID通常是实体的唯一标识，用于在数据库中进行查询或更新操作。
     *
     * @param id 实体的唯一标识符。如果为 null，则表示不进行设置或依赖其他机制来分配ID。
     */
    default void setId(Long id) {
        // 方法体为空，具体的实现可能依赖于继承或实现该接口的类的具体逻辑。
    }

    /**
     * 获取实体的唯一标识符。
     *
     * @return 实体的唯一标识符。如果没有设置或不适用，返回 null
     */
    default Long getId() {
        return null;
    }

    /**
     * 获取父级标识的方法。
     * <p>
     * 该方法默认实现抛出RuntimeException，表明在当前上下文中无法获取父级标识。
     * 子类应根据具体需求重写此方法，以提供有效的父级标识获取逻辑。
     *
     * @return 父级标识的Long类型值。由于默认实现抛出异常，故在正常情况下不返回任何值
     * @throws RuntimeException 默认实现中抛出此异常，表示无法获取父级标识
     */
    default Long getParentId() {
        throw new RuntimeException();
    }

    /**
     * 设置是否叶子
     *
     * @param isLeaf 是否叶子
     */
    default void setIsLeaf(Boolean isLeaf) {
        throw new RuntimeException();
    }

    /**
     * 判断当前节点是否为叶子节点。
     * <p>
     * 该默认方法抛出RuntimeException，表明该操作在当前上下文中不支持或未实现。
     * 子类应重写此方法以提供具体的叶子节点判断逻辑。
     *
     * @return Boolean 返回true表示当前节点是叶子节点，返回false表示不是
     * @throws RuntimeException 表明该操作不支持或未实现
     */
    default Boolean isLeaf() {
        throw new RuntimeException();
    }

}