package cn.nesc.toolkit.es.function;

import org.elasticsearch.action.bulk.BulkRequest;

/**
 * @ClassName BulkBeforeHandler
 * @Description
 * @Author summer
 * @Date 2022/1/10 17:14
 **/
@FunctionalInterface
public interface BulkExceptionHandler
{
    void handle(long executionId, BulkRequest request, Throwable failure);
}
