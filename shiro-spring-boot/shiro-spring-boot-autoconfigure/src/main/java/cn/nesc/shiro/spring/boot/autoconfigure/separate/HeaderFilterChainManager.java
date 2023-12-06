package cn.nesc.shiro.spring.boot.autoconfigure.separate;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by summer on 2019/12/19.
 */
public class HeaderFilterChainManager extends DefaultFilterChainManager
{

    private static Logger log = LoggerFactory.getLogger(HeaderFilterChainManager.class);

    @Override
    protected void addDefaultFilters(boolean init)
    {
        for (CustomDefaultFilter defaultFilter : CustomDefaultFilter.values())
        {
            addFilter(defaultFilter.name(), defaultFilter.newInstance(), init, false);
        }
    }
}
