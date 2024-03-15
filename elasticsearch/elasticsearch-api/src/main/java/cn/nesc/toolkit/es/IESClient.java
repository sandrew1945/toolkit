package cn.nesc.toolkit.es;

import cn.nesc.toolkit.es.common.ESCallback;
import cn.nesc.toolkit.es.common.ESDoc;
import cn.nesc.toolkit.es.common.ESField;
import cn.nesc.toolkit.es.common.ESPageResult;
import cn.nesc.toolkit.es.common.sort.Sorter;
import cn.nesc.toolkit.es.exception.ESException;
import cn.nesc.toolkit.es.function.BulkAfterHandler;
import cn.nesc.toolkit.es.function.BulkBeforeHandler;
import cn.nesc.toolkit.es.function.BulkExceptionHandler;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.index.query.AbstractQueryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IESClient
 * @Description
 * @Author summer
 * @Date 2022/1/4 10:51
 **/
public interface IESClient
{
    void close() throws IOException;
    /**
     * @Author summer
     * @Description 使用指定ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param id        id
     * @Param jsonStr   保存的json
     * @return org.elasticsearch.action.index.IndexResponse
     **/
    IndexResponse addDocWithId(String index, String id, String jsonStr) throws ESException;

    /**
     * @Author summer
     * @Description 使用随机ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param jsonStr   保存的json
     * @return org.elasticsearch.action.index.IndexResponse
     **/
    IndexResponse addDoc(String index, String jsonStr) throws ESException;

    /**
     * @Author summer
     * @Description 使用指定ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param id        id
     * @Param t         文档对象
     * @return org.elasticsearch.action.index.IndexResponse
     **/
    <T> IndexResponse addDocWithId(String index, String id, T t) throws ESException;

    /**
     * @Author summer
     * @Description 使用随机ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param t         文档对象
     * @return org.elasticsearch.action.index.IndexResponse
     **/
    <T> IndexResponse addDoc(String index, T t) throws ESException;

    /**
     * @Author summer
     * @Description 使用指定ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param id        id
     * @Param data      保存的map
     * @return org.elasticsearch.action.index.IndexResponse
     **/
    IndexResponse addDocWithId(String index, String id, Map<String, Object> data) throws ESException;

    /**
     * @Author summer
     * @Description 使用随机ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param data      保存的map
     * @return org.elasticsearch.action.index.IndexResponse
     **/
    IndexResponse addDoc(String index, Map<String, Object> data) throws ESException;

    /**
     * @Author summer
     * @Description 使用指定ID批量新建文档
     * @Date 13:14 2022/1/10
     * @Param index     index名称
     * @Param docList   保存的文档集合
     * @Param timeout   超时时间(毫秒)
     * @return org.elasticsearch.action.bulk.BulkResponse
     **/
    <T> BulkResponse addDocWithId(String index, List<ESDoc<T>> docList, long timeout) throws ESException;

    /**
     * @Author summer
     * @Description 使用指定ID批量新建文档
     * @Date 13:14 2022/1/10
     * @Param index     index名称
     * @Param docList   保存的文档集合
     * @Param timeout   超时时间(毫秒)
     * @Param beforeHandler    前置处理
     * @Param afterHandler      后置处理
     * @Param exceptionHandler   异常处理
     * @return org.elasticsearch.action.bulk.BulkResponse
     **/
    <T> boolean addDocWithId(String index, List<ESDoc<T>> docList, long timeout, BulkBeforeHandler beforeHandler, BulkAfterHandler afterHandler, BulkExceptionHandler exceptionHandler) throws ESException;

    /**
     * @Author summer
     * @Description 使用指定ID批量新建文档
     * @Date 13:14 2022/1/10
     * @Param index     index名称
     * @Param list      保存的文档集合
     * @Param timeout   超时时间(毫秒)
     * @return org.elasticsearch.action.bulk.BulkResponseB
     **/
    <T> BulkResponse addDoc(String index, List<T> list, long timeout) throws ESException;

    /**
     * @Author summer
     * @Description 使用指定ID批量新建文档
     * @Date 13:14 2022/1/10
     * @Param index     index名称
     * @Param list      保存的文档集合
     * @Param timeout   超时时间(毫秒)
     * @Param beforeHandler    前置处理
     * @Param afterHandler      后置处理
     * @Param exceptionHandler   异常处理
     * @return org.elasticsearch.action.bulk.BulkResponseB
     **/
    <T> boolean addDoc(String index, List<T> list, long timeout, BulkBeforeHandler beforeHandler, BulkAfterHandler afterHandler, BulkExceptionHandler exceptionHandler) throws ESException;

    /**
     * @Author summer
     * @Description 更新文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param jsonStr    保存的json
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    UpdateResponse updateDoc(String index, String id, String jsonStr) throws ESException;

    /**
     * @Author summer
     * @Description 更新文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param t          保存的对象
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    <T> UpdateResponse updateDoc(String index, String id, T t) throws ESException;

    /**
     * @Author summer
     * @Description 更新文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param data       保存的map
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    UpdateResponse updateDoc(String index, String id, Map<String, Object> data) throws ESException;

    /**
     * @Author summer
     * @Description 更新或新建文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param data       保存的json
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    UpdateResponse updateOrInsertDoc(String index, String id, String jsonStr) throws ESException;

    /**
     * @Author summer
     * @Description 更新或新建文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param t          保存的对象
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    <T> UpdateResponse updateOrInsertDoc(String index, String id, T t) throws ESException;

    /**
     * @Author summer
     * @Description 更新或新建文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param data       保存的map
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    UpdateResponse updateOrInsertDoc(String index, String id, Map<String, Object> data) throws ESException;

    /**
     * @Author summer
     * @Description 创建Index
     * @Date 15:43 2021/12/31
     * @Param index         index名称
     * @Param type          type名称
     * @Param shardsCount   shard数量
     * @Param replicasCount  replica数量
     * @Param alias         别名
     * @Param fields        字段定义 [name, type]
     * @return org.elasticsearch.action.admin.indices.create.CreateIndexResponse
     **/
    CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, String alias, ESField... fields) throws ESException;

    /**
     * @Author summer
     * @Description 创建Index
     * @Date 15:43 2021/12/31
     * @Param index         index名称
     * @Param type          type名称
     * @Param shardsCount   shard数量
     * @Param replicasCount  replica数量
     * @Param fields        字段定义 [name, type]
     * @return org.elasticsearch.action.admin.indices.create.CreateIndexResponse
     **/
    CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, ESField... fields) throws ESException;

    /**
     * @Author summer
     * @Description 创建Index
     * @Date 15:43 2021/12/31
     * @Param index         index名称
     * @Param type          type名称
     * @Param shardsCount   shard数量
     * @Param replicasCount  replica数量
     * @Param alias         别名
     * @Param fields        字段定义 [name, type]
     * @return org.elasticsearch.action.admin.indices.create.CreateIndexResponse
     **/
    CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, String alias, List<ESField> fields) throws ESException;

    /**
     * @Author summer
     * @Description 创建Index
     * @Date 15:43 2021/12/31
     * @Param index         index名称
     * @Param type          type名称
     * @Param shardsCount   shard数量
     * @Param replicasCount  replica数量
     * @Param fields        字段定义 [name, type]
     * @return org.elasticsearch.action.admin.indices.create.CreateIndexResponse
     **/
    CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, List<ESField> fields) throws ESException;


    /**
     * @Author summer
     * @Description 查询Index
     * @Date 14:19 2022/1/18
     * @Param index     要查询的index
     * @Param maxSize   返回的最大结果数量
     * @return org.elasticsearch.action.search.SearchResponse
     **/
    SearchResponse query(String index, Integer maxSize, Sorter... sorters) throws ESException;

    /**
     * @Author summer
     * @Description 查询Index
     * @Date 14:19 2022/1/18
     * @Param index     要查询的index (默认返回10条)
     * @return org.elasticsearch.action.search.SearchResponse
     **/
    SearchResponse query(String index, Sorter... sorters) throws ESException;

    /**
     * @Author summer
     * @Description 查询Index
     * @Date 16:23 2022/5/10
     * @Param index     要查询的index
     * @Param maxSize   返回的最大结果数量
     * @Param queryBuilder 自定义的QueryBuilder
     * @Param sorters 排序
     * @return org.elasticsearch.action.search.SearchResponse
     **/
    SearchResponse queryForCustom(String index, Integer maxSize, AbstractQueryBuilder queryBuilder, Sorter... sorters) throws ESException;

    /**
     * @Author summer
     * @Description
     * @Date 09:56 2022/5/12
     * @Param index     要查询的index
     * @Param page   查询页数
     * @Param size   每页记录数量
     * @Param queryBuilder 自定义的QueryBuilder
     * @Param sorters 排序
     * @return org.elasticsearch.action.search.SearchResponse
     **/
    SearchResponse pageQuery(String index, Integer page, Integer size, AbstractQueryBuilder queryBuilder, Sorter... sorters) throws ESException;

    /**
     * @Author summer
     * @Description
     * @Date 10:23 2022/5/12
     * @Param index     要查询的index
     * @Param page   查询页数
     * @Param size   每页记录数量
     * @Param queryBuilder 自定义的QueryBuilder
     * @Param clz 封装的类型
     * @Param sorters 排序
     * @return cn.nesc.toolkit.es.common.ESPageResult<T>
     **/
    <T> ESPageResult<T> pageQuery(String index, Integer page, Integer size, AbstractQueryBuilder queryBuilder, Class<T> clz, Sorter... sorters) throws ESException;

    /**
     * @Author summer
     * @Description
     * @Date 10:23 2022/5/12
     * @Param index     要查询的index
     * @Param page   查询页数
     * @Param size   每页记录数量
     * @Param queryBuilder 自定义的QueryBuilder
     * @Param callback 返回对象包装callback
     * @Param sorters 排序
     * @return cn.nesc.toolkit.es.common.ESPageResult<T>
     **/
    <T> ESPageResult<T> pageQuery(String index, Integer page, Integer size, AbstractQueryBuilder queryBuilder, ESCallback<T> callback, Sorter... sorters) throws ESException;

    /**
     * @Author summer
     * @Description 查询记录数量(同步)
     * @Date 16:48 2022/5/19
     * @Param [index, queryBuilder]
     * @return long
     **/
    long countForSync(String index, AbstractQueryBuilder queryBuilder) throws ESException;

    /**
     * @Author summer
     * @Description 查询记录数量(异步)
     * @Date 16:48 2022/5/19
     * @Param [index, queryBuilder]
     * @return long
     **/
    void countForAsync(String index, AbstractQueryBuilder queryBuilder, ActionListener<CountResponse> listener) throws ESException;

    /**
     * @Author summer
     * @Description 判断index是否存在
     * @Date 14:49 2022/1/4
     * @Param [index]
     * @return boolean
     **/
    boolean isIndexExist(String index) throws ESException;

    /**
     * @Author summer
     * @Description 查询index
     * @Date 14:14 2022/1/4
     * @Param [index]
     * @return boolean
     **/
    GetIndexResponse getIndex(String index) throws ESException;
}
