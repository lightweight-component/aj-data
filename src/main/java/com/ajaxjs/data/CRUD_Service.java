package com.ajaxjs.data;

import com.ajaxjs.data.jdbc_helper.JdbcReader;
import com.ajaxjs.data.jdbc_helper.JdbcWriter;
import com.ajaxjs.data.jdbc_helper.common.IdField;
import com.ajaxjs.data.jdbc_helper.common.TableName;
import com.ajaxjs.util.ListUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class CRUD_Service<T> {
    private JdbcReader reader;

    private JdbcWriter writer;

    /**
     *
     */
    private SmallMyBatis smallMyBatis;

    /**
     * SQL XML 里的 id
     */
    private String sqlId;

    /**
     * SQL 语句
     */
    private String sql;

    private Map<String, Object> mapParams;

    private Class<T> beanClz;

    private Object[] orderedParams;

    /**
     * 获取 SQL 语句，如果是 SQL Id 则获取并解析之
     *
     * @return 最终 SQL 语句
     */
    private String getRealSql() {
        if (StringUtils.hasText(sql))
            return sql;
        else if (StringUtils.hasText(sqlId))
            return smallMyBatis.handleSql(mapParams, sqlId);
        else throw new IllegalArgumentException("没输入的 SQL 参数");
    }

    /**
     * 获取单笔详情
     *
     * @return 可能是 Map 或者 Java Bean，根据 beanClz 是否有值来决定
     */
    public Object info() {
        String sql = getRealSql();

        return beanClz == null ? reader.queryAsMap(sql, orderedParams) : reader.queryAsBean(beanClz, sql, orderedParams);
    }

    @SuppressWarnings("unchecked")
    public T infoBean() {
        Object info = info();
        if (info == null)
            return null;

        return (T) info;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> infoMap() {
        Object info = info();
        if (info == null)
            return null;

        return (Map<String, Object>) info;
    }

    /**
     * 执行查询操作并返回查询结果。
     * <p>
     * 本方法根据 beanClz 是否为 null 来决定查询结果的返回类型。如果 beanClz 为 null，则查询结果将作为 Map 列表返回；
     * 否则，查询结果将作为指定 bean 类的实例列表返回。这提供了一种灵活的方式，根据调用方的需求返回不同类型的结果。
     *
     * @return 查询结果列表，可以是任何类型的列表，具体类型取决于 beanClz 的值。
     */
    public List<?> list() {
        // 获取实际的SQL语句，这可能涉及到动态拼接 SQL 或其他逻辑。
        String sql = getRealSql();

        // 根据 beanClz 是否为 null，选择不同的查询方法，并返回查询结果。
        // 如果 beanClz 为 null，调 用queryAsMapList 方法，返回 Map 列表；否则，调用 queryAsBeanList 方法，返回指定 bean 类的实例列表。
        return beanClz == null ? reader.queryAsMapList(sql, orderedParams) : reader.queryAsBeanList(beanClz, sql, orderedParams);
    }

    /**
     * 将查询结果转换为指定类型的Bean列表。
     * <p>
     * 本方法通过类型转换将查询到的数据转换为泛型列表。由于Java的类型擦除，这里需要使用 @SuppressWarnings 注解来抑制编译器警告。
     * 方法的核心在于将查询结果（假设为List类型）强制转换为泛型 List<T> 类型，以便返回给调用者一个类型安全的列表。
     * 如果查询结果为空或列表内元素类型不匹配，将会在运行时抛出异常。
     *
     * @return 返回一个泛型列表，包含指定类型的Bean对象。如果查询结果为空，则返回一个空列表，而不是null。
     */
    @SuppressWarnings("unchecked")
    public List<T> listBean() {
        // 通过类型转换将查询结果转换为泛型List<T>类型
        List<T> list = (List<T>) list();

        // 使用ListUtils的getList方法，确保返回的列表不为null，如果原始列表为null，则返回一个空列表
        return ListUtils.getList(list);
    }

    /**
     * 将查询结果转换为 List<Map<String, Object>> 类型。
     * 本方法假设调用了一个未在代码中显示的方法list()，该方法返回一个对象，
     * 此对象需要通过类型转换变为 List<Map<String, Object>> 类型。
     * 使用@SuppressWarnings注解来抑制编译器对类型转换的警告。
     *
     * @return 转换后的List<Map < String, Object>>对象，如果原始结果为空或转换不可行，则返回一个空列表。
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> listMap() {
        // 将list()方法返回的对象强制转换为List<Map<String, Object>>类型
        List<Map<String, Object>> list = (List<Map<String, Object>>) list();

        // 使用ListUtils的getList方法确保返回的列表不为空，如果原始列表为空，则返回一个空列表
        return ListUtils.getList(list);
    }

    /**
     * 获取实体类上的表名（通过注解）
     *
     * @param entity 实体类
     * @return 表名
     */
    public static String getTableName(Object entity) {
        TableName tableNameA = entity.getClass().getAnnotation(TableName.class);
        if (tableNameA == null) throw new RuntimeException("实体类未提供表名");

        return tableNameA.value();
    }

    /**
     * 获取实体类上的 Id 字段名称（通过注解）
     *
     * @param entity 实体类
     * @return 表名
     */
    public static String getIdField(Object entity) {
        IdField annotation = entity.getClass().getAnnotation(IdField.class);

        if (annotation == null) throw new DataAccessException("没设置 IdField 注解，不知哪个主键字段");

        return annotation.value();
    }
}
