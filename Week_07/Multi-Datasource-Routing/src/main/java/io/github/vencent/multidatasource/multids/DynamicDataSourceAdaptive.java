package io.github.vencent.multidatasource.multids;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author vencent
 * @create 2021-03-07 22:31
 **/
public class DynamicDataSourceAdaptive extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
