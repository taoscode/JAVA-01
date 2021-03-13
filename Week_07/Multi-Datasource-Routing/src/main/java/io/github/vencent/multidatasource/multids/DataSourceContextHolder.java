package io.github.vencent.multidatasource.multids;

/**
 *
 * @author vencent
 * @create 2021-03-07 22:31
 **/
public class DataSourceContextHolder {
    private static final ThreadLocal<DsEnum> contextHolder = new ThreadLocal<DsEnum>();
    /**
     * @param dataSourceType 数据库类型
     * @Description: 设置数据源类型
     */
    public static void setDataSourceType(DsEnum dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * @Description: 获取数据源类型
     */
    public static DsEnum getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * @Description: 清除数据源类型
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
