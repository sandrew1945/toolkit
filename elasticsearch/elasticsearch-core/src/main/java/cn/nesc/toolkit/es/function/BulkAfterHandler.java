package cn.nesc.toolkit.es.function;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;

/**
 * @ClassName BulkBeforeHandler
 * @Description
 * @Author summer
 * @Date 2022/1/10 17:14
 **/
@FunctionalInterface
public interface BulkAfterHandler
{
    void handle(long executionId, BulkRequest request, BulkResponse response);
}
