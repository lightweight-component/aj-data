package com.ajaxjs.data;

import com.ajaxjs.data.jdbc_helper.JdbcWriter;
import com.ajaxjs.data.jdbc_helper.common.IdField;
import com.ajaxjs.data.jdbc_helper.common.TableName;
import com.ajaxjs.framework.PageResult;
import com.ajaxjs.util.reflect.Methods;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CRUD {
    /**
     * 查询单行单列的记录
     *
     * @param clz    返回的类型
     * @param sql    执行的 SQL
     * @param params SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @param <T>    返回的类型
     * @return 单行单列记录
     */
    public static <T> T queryOne(Class<T> clz, String sql, Object... params) {
        return new CRUD_Service<T>().getReader().queryOne(sql, clz, params);
    }

    /**
     * 查询单笔记录，以 Java Bean 格式返回
     *
     * @param beanClz 返回的 Bean 类型
     * @param sql     SQL 语句
     * @param params  SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @param <T>     返回的 Bean 类型
     * @return 查询单笔记录，以 Java Bea 格式返回
     */
    public static <T> T info(Class<T> beanClz, String sql, Object... params) {
        return new CRUD_Service<T>().setBeanClz(beanClz).setSql(sql).setOrderedParams(params).infoBean();
    }

    /**
     * 查询单笔记录，以 Java Bean 格式返回
     *
     * @param sqlId     SQL Id，于 XML 里的索引
     * @param beanClz   返回的 Bean 类型
     * @param mapParams Map 格式的参数（若没有可传 null）
     * @param params    SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @param <T>       返回的 Bean 类型
     * @return 查询单笔记录，以 Java Bea 格式返回
     */
    public static <T> T infoBySqlId(Class<T> beanClz, String sqlId, Map<String, Object> mapParams, Object... params) {
        return new CRUD_Service<T>().setBeanClz(beanClz).setSqlId(sqlId).setMapParams(mapParams).setOrderedParams(params).infoBean();
    }

    /**
     * 查询单笔记录，以 Map 格式返回
     *
     * @param sql    SQL 语句
     * @param params SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @return 查询结果，如果为 null 表示没数据
     */
    public static Map<String, Object> infoMap(String sql, Object... params) {
        return new CRUD_Service<>().setSql(sql).setOrderedParams(params).infoMap();
    }

    /**
     * 查询单笔记录，以 Map 格式返回
     *
     * @param sqlId     SQL Id，于 XML 里的索引
     * @param mapParams Map 格式的参数（若没有可传 null）
     * @param params    SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @return 查询结果，如果为 null 表示没数据
     */
    public static Map<String, Object> infoMapBySqlId(String sqlId, Map<String, Object> mapParams, Object... params) {
        return new CRUD_Service<>().setSqlId(sqlId).setMapParams(mapParams).setOrderedParams(params).infoMap();
    }

    /**
     * 查询列表记录，以 List Map 格式返回
     *
     * @param sql    SQL 语句
     * @param params SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @return 查询结果，如果没数据返回一个空 List
     */
    public static List<Map<String, Object>> listMap(String sql, Object... params) {
        return new CRUD_Service<>().setSql(sql).setOrderedParams(params).listMap();
    }

    /**
     * 查询列表记录，以 List Map 格式返回
     *
     * @param sqlId     SQL Id，于 XML 里的索引
     * @param paramsMap Map 格式的参数（若没有可传 null）
     * @param params    SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @return 查询结果，如果没数据返回一个空 List
     */
    public static List<Map<String, Object>> listMapBySqlId(String sqlId, Map<String, Object> paramsMap, Object... params) {
        return new CRUD_Service<>().setSqlId(sqlId).setMapParams(paramsMap).setOrderedParams(params).listMap();
    }

    /**
     * 查询列表记录，以 List Java Bean 格式返回
     *
     * @param beanClz 实体 Bean 类型
     * @param sql     SQL 语句
     * @param params  SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @return 查询结果，如果没数据返回一个空 List
     */
    public static <T> List<T> list(Class<T> beanClz, String sql, Object... params) {
        return new CRUD_Service<T>().setBeanClz(beanClz).setSql(sql).setOrderedParams(params).listBean();
    }

    /**
     * 查询列表记录，以 List Java Bean 格式返回
     *
     * @param beanClz   实体 Bean 类型
     * @param sqlId     SQL Id，于 XML 里的索引
     * @param paramsMap Map 格式的参数（若没有可传 null）
     * @param params    SQL 参数列表（选填项，能对应 SQL 里面的`?`的插值符）
     * @param <T>       实体 Bean 类型
     * @return 查询结果，如果没数据返回一个空 List
     */
    public static <T> List<T> listBySqlId(Class<T> beanClz, String sqlId, Map<String, Object> paramsMap, Object... params) {
        return new CRUD_Service<T>().setBeanClz(beanClz).setSqlId(sqlId).setMapParams(paramsMap).setOrderedParams(params).listBean();
    }

    /**
     * 分页查询列表记录，以 List Java Bean 格式返回
     *
     * @param beanClz   实体 Bean 类型
     * @param sql       SQL 语句
     * @param paramsMap Map 格式的参数（若没有可传 null）
     * @param <T>       实体 Bean 类型
     * @return 查询结果，如果没数据返回一个空 List
     */
    public static <T> PageResult<T> page(Class<T> beanClz, String sql, Map<String, Object> paramsMap) {
        sql = SmallMyBatis.handleSql(sql, paramsMap);

        return PageEnhancer.page(sql, beanClz);
    }

    /**
     * 分页查询列表记录，以 List Java Bean 格式返回
     *
     * @param beanClz   实体 Bean 类型
     * @param sqlId     SQL Id，于 XML 里的索引
     * @param paramsMap Map 格式的参数（若没有可传 null）
     * @param <T>       实体 Bean 类型
     * @return 查询结果，如果没数据返回一个空 List
     */
    public static <T> PageResult<T> pageBySqlId(Class<T> beanClz, String sqlId, Map<String, Object> paramsMap) {
        String sql = SmallMyBatis.handleSql(paramsMap, sqlId);

        return PageEnhancer.page(sql, beanClz);
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

    /**
     * 创建数据并返回创建的记录数量
     *
     * @param talebName 数据表名
     * @param entity    待创建的实体对象
     * @param idField   ID字段名
     * @return 创建的记录数量，类型为Long
     */
    public static Long create(String talebName, Object entity, String idField) {
        JdbcWriter jdbcWriter = jdbcWriterFactory();
        jdbcWriter.setTableName(talebName); // 设置数据表名

        if (StringUtils.hasText(idField)) jdbcWriter.setIdField(idField); // 如果ID字段名不为空，则设置ID字段名

        return (Long) jdbcWriter.create(entity);   // 调用JdbcWriter的create方法创建数据，并将结果转换为Long类型返回
    }

    public static Long createWithIdField(Object entity, String idField) {
        return create(getTableName(entity), entity, idField);
    }

    public static Long createWithIdField(Object entity) {
        return createWithIdField(entity, getIdField(entity));
    }

    public static Long create(Object entity) {
        return create(getTableName(entity), entity, null);
    }

    public static boolean update(String talebName, Object entity, String idField) {
        JdbcWriter jdbcWriter = jdbcWriterFactory();
        jdbcWriter.setTableName(talebName);

        if (StringUtils.hasText(idField)) jdbcWriter.setIdField(idField);
        else throw new DataAccessException("未指定 id，这将会是批量全体更新！");

        return jdbcWriter.update(entity) > 0;
    }

    public static boolean update(String talebName, Object entity) {
        return update(talebName, entity, null);
    }

    public static boolean update(Object entity) {
        return update(getTableName(entity), entity);
    }

    public static boolean updateWithIdField(Object entity) {
        return updateWithIdField(entity, getIdField(entity));
    }

    public static boolean updateWithIdField(Object entity, String idField) {
        return update(getTableName(entity), entity, idField);
    }

    public static boolean updateWithWhere(Object entity, String where) {
        String talebName = getTableName(entity);

        JdbcWriter jdbcWriter = jdbcWriterFactory();
        jdbcWriter.setTableName(talebName);
        jdbcWriter.setWhere(where);

        return jdbcWriter.updateWhere(entity, where) > 0;
    }

    /**
     * 删除指定实体的指定 id 对应的记录
     *
     * @param entity 实体对象
     * @param id     实体的 id
     * @return 如果删除成功则返回 true，否则返回 false
     */
    public static boolean delete(Object entity, Serializable id) {
        return delete(getTableName(entity), id);
    }

    /**
     * 根据给定的使用者姓名和 ID 删除数据
     *
     * @param talebName 数据表名
     * @param id        实体的 id
     * @return 如果删除成功则返回 true，否则返回 false
     */
    public static boolean delete(String talebName, Serializable id) {
        JdbcWriter jdbcWriter = jdbcWriterFactory();
        jdbcWriter.setTableName(talebName);

        return jdbcWriter.delete(id);
    }

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     * @return 如果删除成功则返回 true，否则返回 false
     */
    public static boolean delete(Object entity) {
        Object id = Methods.executeMethod(entity, "getId");

        if (id != null) {
            return delete(entity, (Serializable) id);
        } else {
            System.err.println("没有 getId()");
            return false;
        }
    }

}
