/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: BulkBeforeHandler
 * Author:   summer
 * Date:     2022/1/10 17:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.function;

import org.elasticsearch.action.bulk.BulkRequest;

/**
 * @ClassName BulkBeforeHandler
 * @Description
 * @Author summer
 * @Date 2022/1/10 17:14
 **/
@FunctionalInterface
public interface BulkBeforeHandler
{
    void handle(long executionId, BulkRequest request);
}
