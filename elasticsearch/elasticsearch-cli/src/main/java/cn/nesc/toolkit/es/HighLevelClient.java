/**
 * Copyright (C), 2015-2021, 东北证券股份有限公司
 * FileName: HighLevelClient
 * Author:   summer
 * Date:     2021/12/29 10:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es;

import cn.nesc.toolkit.common.json.JacksonValidator;
import cn.nesc.toolkit.common.json.JsonUtil;
import cn.nesc.toolkit.common.json.JsonValidator;
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
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName HighLevelClient
 * @Description
 * @Author summer
 * @Date 2021/12/29 10:52
 **/
public class HighLevelClient implements IESClient
{
    private static Logger log = LoggerFactory.getLogger(HighLevelClient.class);

    private RestClientBuilder httpClientBuilder;

    private RestHighLevelClient client;

    private Boolean doValidate = false;

    private JsonValidator jsonValidator;

    public HighLevelClient(RestClientBuilder httpClientBuilder)
    {
        this.httpClientBuilder = httpClientBuilder;
        this.jsonValidator = new JacksonValidator();
        this.client = new RestHighLevelClient(httpClientBuilder);
    }

    public HighLevelClient(RestClientBuilder httpClientBuilder, Boolean doValidate, JsonValidator jsonValidator)
    {
        this.httpClientBuilder = httpClientBuilder;
        this.doValidate = doValidate;
        this.jsonValidator = jsonValidator;
        this.client = new RestHighLevelClient(httpClientBuilder);
    }

    @Override
    public void close() throws IOException
    {
        this.client.close();
    }

    /**
     * @return org.elasticsearch.action.index.IndexResponse
     * @Author summer
     * @Description 使用指定ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param type      type名称
     * @Param id        id
     * @Param jsonStr   保存的json
     **/
    @Override
    public IndexResponse addDocWithId(String index, String id, String jsonStr) throws ESException
    {
        try
        {
            if (null == jsonStr || "".equals(jsonStr))
            {
                throw new ESException("Document is empty. please send JSON data.");
            }
            if (doValidate && null != jsonValidator)
            {
                if (!jsonValidator.isJson(jsonStr))
                {
                    throw new ESException("Document isn't a json type.");
                }
            }
            IndexRequest request = new IndexRequest(index);
            request.id(id);
            request.source(jsonStr, XContentType.JSON);
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            log.debug("insert success, return content:{}", indexResponse);
            return indexResponse;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.index.IndexResponse
     * @Author summer
     * @Description 使用随机ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param type      type名称
     * @Param jsonStr   保存的json
     **/
    @Override
    public IndexResponse addDoc(String index, String jsonStr) throws ESException
    {
        return addDocWithId(index, null, jsonStr);
    }

    /**
     * @return org.elasticsearch.action.index.IndexResponse
     * @Author summer
     * @Description 使用指定ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param type      type名称
     * @Param id        id
     * @Param t         文档对象
     **/
    @Override
    public <T> IndexResponse addDocWithId(String index, String id, T t) throws ESException
    {
        try
        {
            if (null == t)
            {
                throw new ESException("Document is empty. please send object data.");
            }
            IndexRequest request = new IndexRequest(index);
            request.id(id);
            request.source(JsonUtil.javaObject2String(t), XContentType.JSON);
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            log.debug("insert success, return content:{}", indexResponse);
            return indexResponse;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }


    /**
     * @return org.elasticsearch.action.index.IndexResponse
     * @Author summer
     * @Description 使用随机ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param type      type名称
     * @Param t         文档对象
     **/
    @Override
    public <T> IndexResponse addDoc(String index, T t) throws ESException
    {
        return addDocWithId(index, null, t);
    }

    /**
     * @return org.elasticsearch.action.index.IndexResponse
     * @Author summer
     * @Description 使用指定ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param type      type名称
     * @Param id        id
     * @Param data      保存的map
     **/
    @Override
    public IndexResponse addDocWithId(String index, String id, Map<String, Object> data) throws ESException
    {
        try
        {
            if (null == data)
            {
                throw new ESException("Document is empty. please send map data.");
            }
            IndexRequest request = new IndexRequest(index);
            request.id(id);
            request.source(data);
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            log.debug("insert success, return content:{}", indexResponse);
            return indexResponse;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.index.IndexResponse
     * @Author summer
     * @Description 使用随机ID新建文档
     * @Date 10:56 2021/12/29
     * @Param index     index名称
     * @Param type      type名称
     * @Param data      保存的map
     **/
    @Override
    public IndexResponse addDoc(String index, Map<String, Object> data) throws ESException
    {
        return addDocWithId(index, null, data);
    }

    /**
     * @return org.elasticsearch.action.bulk.BulkResponse
     * @Author summer
     * @Description 使用指定ID批量新建文档
     * @Date 13:14 2022/1/10
     * @Param index     index名称
     * @Param docList   保存的文档集合
     * @Param timeout   超时时间(毫秒)
     **/
    @Override
    public <T> BulkResponse addDocWithId(String index, List<ESDoc<T>> docList, long timeout) throws ESException
    {
        try
        {
            BulkRequest request = new BulkRequest();
            // Timeout to wait for the bulk request to be performed
            request.timeout(TimeValue.timeValueMillis(timeout));
            // Sets the number of shard copies that must be active before proceeding with the index/update/delete operations.
            request.waitForActiveShards(ActiveShardCount.DEFAULT);
            docList.stream().forEach(esDoc -> {
                IndexRequest indexRequest = new IndexRequest(index);
                indexRequest.id(esDoc.getId());
                indexRequest.source(JsonUtil.javaObject2String(esDoc.getData()), XContentType.JSON);
                request.add(indexRequest);
            });
            BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
            return bulkResponse;
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }

    }

    @Override
    public <T> boolean addDocWithId(String index, List<ESDoc<T>> docList, long timeout, BulkBeforeHandler beforeHandler, BulkAfterHandler afterHandler, BulkExceptionHandler exceptionHandler) throws ESException
    {
        try
        {
            BulkProcessor.Listener listener = new BulkProcessor.Listener()
            {
                @Override
                public void beforeBulk(long executionId, BulkRequest request)
                {
                    beforeHandler.handle(executionId, request);
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, BulkResponse response)
                {
                    afterHandler.handle(executionId, request, response);
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, Throwable failure)
                {
                    exceptionHandler.handle(executionId, request, failure);
                }
            };
            BulkProcessor bulkProcessor = BulkProcessor.builder((request, bulkListener) -> client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener), listener).setBulkActions(5)      // Set when to flush a new bulk request based on the number of actions currently added (defaults to 1000, use -1 to disable it)
                    .setConcurrentRequests(0)   // Set the number of concurrent requests allowed to be executed (default to 1, use 0 to only allow the execution of a single request)
                    .setFlushInterval(TimeValue.timeValueSeconds(10L)) // Set a flush interval flushing any BulkRequest pending if the interval passes (defaults to not set)
                    .setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3)) // Set a constant back off policy that initially waits for 1 second and retries up to 3 times.
                    .build();
            docList.stream().forEach(esDoc -> {
                IndexRequest indexRequest = new IndexRequest(index);
                indexRequest.id(esDoc.getId());
                indexRequest.source(JsonUtil.javaObject2String(esDoc.getData()), XContentType.JSON);
                bulkProcessor.add(indexRequest);
            });
            boolean terminated = bulkProcessor.awaitClose(timeout, TimeUnit.MILLISECONDS);
            return terminated;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.bulk.BulkResponse
     * @Author summer
     * @Description 使用指定ID批量新建文档
     * @Date 13:14 2022/1/10
     * @Param index     index名称
     * @Param list      保存的文档集合
     * @Param timeout   超时时间(毫秒)
     **/
    @Override
    public <T> BulkResponse addDoc(String index, List<T> list, long timeout) throws ESException
    {
        try
        {
            BulkRequest request = new BulkRequest();
            // Timeout to wait for the bulk request to be performed
            request.timeout(TimeValue.timeValueMillis(timeout));
            // Sets the number of shard copies that must be active before proceeding with the index/update/delete operations.
            request.waitForActiveShards(ActiveShardCount.DEFAULT);
            list.stream().forEach(el -> {
                IndexRequest indexRequest = new IndexRequest(index);
                indexRequest.source(JsonUtil.javaObject2String(el), XContentType.JSON);
                request.add(indexRequest);
            });
            BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
            return bulkResponse;
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    @Override
    public <T> boolean addDoc(String index, List<T> list, long timeout, BulkBeforeHandler beforeHandler, BulkAfterHandler afterHandler, BulkExceptionHandler exceptionHandler) throws ESException
    {
        try
        {
            BulkProcessor.Listener listener = new BulkProcessor.Listener()
            {
                @Override
                public void beforeBulk(long executionId, BulkRequest request)
                {
                    beforeHandler.handle(executionId, request);
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, BulkResponse response)
                {
                    afterHandler.handle(executionId, request, response);
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, Throwable failure)
                {
                    exceptionHandler.handle(executionId, request, failure);
                }
            };
            BulkProcessor bulkProcessor = BulkProcessor.builder((request, bulkListener) -> client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener), listener).setBulkActions(500)      // Set when to flush a new bulk request based on the number of actions currently added (defaults to 1000, use -1 to disable it)
                    .setConcurrentRequests(0)   // Set the number of concurrent requests allowed to be executed (default to 1, use 0 to only allow the execution of a single request)
                    .setFlushInterval(TimeValue.timeValueSeconds(10L)) // Set a flush interval flushing any BulkRequest pending if the interval passes (defaults to not set)
                    .setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3)) // Set a constant back off policy that initially waits for 1 second and retries up to 3 times.
                    .build();
            list.stream().forEach(doc -> {
                IndexRequest indexRequest = new IndexRequest(index);
                indexRequest.source(JsonUtil.javaObject2String(doc), XContentType.JSON);
                bulkProcessor.add(indexRequest);
            });
            boolean terminated = bulkProcessor.awaitClose(timeout, TimeUnit.MILLISECONDS);
            return terminated;

        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.update.UpdateResponse
     * @Author summer
     * @Description 更新文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param jsonStr    保存的json
     **/
    @Override
    public UpdateResponse updateDoc(String index, String id, String jsonStr) throws ESException
    {
        try
        {
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(jsonStr, XContentType.JSON);
            return client.update(request, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.update.UpdateResponse
     * @Author summer
     * @Description 更新文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param t          保存的对象
     **/
    @Override
    public <T> UpdateResponse updateDoc(String index, String id, T t) throws ESException
    {
        try
        {
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(JsonUtil.javaObject2String(t), XContentType.JSON);
            return client.update(request, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.update.UpdateResponse
     * @Author summer
     * @Description 更新文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param data       保存的map
     **/
    @Override
    public UpdateResponse updateDoc(String index, String id, Map<String, Object> data) throws ESException
    {
        try
        {
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(data);
            return client.update(request, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.update.UpdateResponse
     * @Author summer
     * @Description 更新或新建文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param data       保存的json
     **/
    @Override
    public UpdateResponse updateOrInsertDoc(String index, String id, String jsonStr) throws ESException
    {
        try
        {
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(jsonStr, XContentType.JSON);
            request.upsert(jsonStr, XContentType.JSON);
            return client.update(request, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.update.UpdateResponse
     * @Author summer
     * @Description 更新或新建文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param t          保存的对象
     **/
    @Override
    public <T> UpdateResponse updateOrInsertDoc(String index, String id, T t) throws ESException
    {
        try
        {
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(JsonUtil.javaObject2String(t), XContentType.JSON);
            request.upsert(JsonUtil.javaObject2String(t), XContentType.JSON);
            return client.update(request, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.update.UpdateResponse
     * @Author summer
     * @Description 更新或新建文档
     * @Date 15:30 2022/1/4
     * @Param index      index名称
     * @Param id         文档id
     * @Param data       保存的map
     **/
    @Override
    public UpdateResponse updateOrInsertDoc(String index, String id, Map<String, Object> data) throws ESException
    {
        try
        {
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(data);
            request.upsert(data);
            return client.update(request, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.admin.indices.create.CreateIndexResponse
     * @Author summer
     * @Description 创建Index
     * @Date 15:43 2021/12/31
     * @Param index         index名称
     * @Param type          type名称
     * @Param shardsCount   shard数量
     * @Param replicasCoun  replica数量
     * @Param alias         别名
     * @Param fields        字段定义 [name, type]
     **/
    @Override
    public CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, String alias, ESField... fields) throws ESException
    {
        try
        {
            if (null == index)
            {
                throw new ESException("index can't be null.");
            }
            // create index
            CreateIndexRequest request = new CreateIndexRequest(index);
            Map<String, Object> typeMapping = new HashMap<>();
            Map<String, Object> fieldMapping = new HashMap<>();
            for (ESField field : fields)
            {
                fieldMapping.put(field.getName(), field.getEsType().toMap());
            }
            typeMapping.put("properties", fieldMapping);
            // 设置shards、replicas
            request.settings(Settings.builder().put("index.number_of_shards", shardsCount).put("index.number_of_replicas", replicasCount));
            //设置mapping参数
            request.mapping(typeMapping);
            //设置别名
            if (null == alias)
            {
                alias = index + "_alias";
            }
            request.alias(new Alias(alias));
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            return createIndexResponse;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    @Override
    public CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, ESField... fields) throws ESException
    {
        return createIndex(index, shardsCount, replicasCount, index + "_alias", fields);
    }

    /**
     * @return org.elasticsearch.action.admin.indices.create.CreateIndexResponse
     * @Author summer
     * @Description 创建Index
     * @Date 15:43 2021/12/31
     * @Param index         index名称
     * @Param type          type名称
     * @Param shardsCount   shard数量
     * @Param replicasCoun  replica数量
     * @Param alias         别名
     * @Param fields        字段定义 [name, type]
     **/
    @Override
    public CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, String alias, List<ESField> fields) throws ESException
    {
        try
        {
            if (null == index)
            {
                throw new ESException("index can't be null.");
            }
            // create index
            CreateIndexRequest request = new CreateIndexRequest(index);
            Map<String, Object> typeMapping = new HashMap<>();
            Map<String, Object> fieldMapping = new HashMap<>();
            fields.stream().forEach(field -> fieldMapping.put(field.getName(), field.getEsType().toMap()));
            typeMapping.put("properties", fieldMapping);
            // 设置shards、replicas
            request.settings(Settings.builder().put("index.number_of_shards", shardsCount).put("index.number_of_replicas", replicasCount));
            //设置mapping参数
            request.mapping(typeMapping);
            //设置别名
            if (null == alias)
            {
                alias = index + "_alias";
            }
            request.alias(new Alias(alias));
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            return createIndexResponse;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.admin.indices.create.CreateIndexResponse
     * @Author summer
     * @Description 创建Index
     * @Date 15:43 2021/12/31
     * @Param index         index名称
     * @Param type          type名称
     * @Param shardsCount   shard数量
     * @Param replicasCount  replica数量
     * @Param fields        字段定义 [name, type]
     **/
    @Override
    public CreateIndexResponse createIndex(String index, Integer shardsCount, Integer replicasCount, List<ESField> fields) throws ESException
    {
        return createIndex(index, shardsCount, replicasCount, index + "_alias", fields);
    }


    /**
     * @return org.elasticsearch.action.search.SearchResponse
     * @Author summer
     * @Description 查询Index
     * @Date 14:19 2022/1/18
     * @Param index     要查询的index
     * @Param maxSize   返回的最大结果数量
     **/
    @Override
    public SearchResponse query(String index, Integer maxSize, Sorter... sorters) throws ESException
    {
        try
        {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchSourceBuilder.size(null == maxSize ? 10 : maxSize);
            if (null != sorters)
            {
                for (Sorter sorter : sorters)
                {
                    searchSourceBuilder.sort(new FieldSortBuilder(sorter.getField()).order(sorter.getOrder()));
                }
            }
            searchRequest.source(searchSourceBuilder);
            return client.search(searchRequest, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return org.elasticsearch.action.search.SearchResponse
     * @Author summer
     * @Description 查询Index
     * @Date 14:19 2022/1/18
     * @Param index     要查询的index (默认返回10条)
     **/
    @Override
    public SearchResponse query(String index, Sorter... sorters) throws ESException
    {
        return query(index, null, sorters);
    }

    @Override
    public SearchResponse queryForCustom(String index, Integer maxSize, AbstractQueryBuilder queryBuilder, Sorter... sorters) throws ESException
    {
        try
        {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (null != queryBuilder)
            {
                searchSourceBuilder.query(queryBuilder);
            }
            searchSourceBuilder.size(null == maxSize ? 10 : maxSize);
            if (null != sorters)
            {
                for (Sorter sorter : sorters)
                {
                    searchSourceBuilder.sort(new FieldSortBuilder(sorter.getField()).order(sorter.getOrder()));
                }
            }
            searchRequest.source(searchSourceBuilder);
            return client.search(searchRequest, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

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
    @Override
    public SearchResponse pageQuery(String index, Integer page, Integer size, AbstractQueryBuilder queryBuilder, Sorter... sorters) throws ESException
    {
        try
        {
            if (null == page)
            {
                throw new ESException("page can't be null");
            }
            size = null == size ? 10 : size;    // 如果size为null，默认10
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (null != queryBuilder)
            {
                searchSourceBuilder.query(queryBuilder);
            }
            // 根据page和size计算from
            int from = page * size - size;
            searchSourceBuilder.from(from);
            searchSourceBuilder.size(null == size ? 10 : size);
            if (null != sorters)
            {
                for (Sorter sorter : sorters)
                {
                    searchSourceBuilder.sort(new FieldSortBuilder(sorter.getField()).order(sorter.getOrder()));
                }
            }
            searchRequest.source(searchSourceBuilder);
            return client.search(searchRequest, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

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
    @Override
    public <T> ESPageResult<T> pageQuery(String index, Integer page, Integer size, AbstractQueryBuilder queryBuilder, Class<T> clz, Sorter... sorters) throws ESException
    {
        ESPageResult pageResult = new ESPageResult();
        try
        {
            if (null == page)
            {
                throw new ESException("page can't be null");
            }
            size = null == size ? 10 : size;    // 如果size为null，默认10

            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (null != queryBuilder)
            {
                searchSourceBuilder.query(queryBuilder);
            }
            // 根据page和size计算from
            int from = page * size - size;
            searchSourceBuilder.from(from);
            searchSourceBuilder.size(null == size ? 10 : size);
            if (null != sorters)
            {
                for (Sorter sorter : sorters)
                {
                    searchSourceBuilder.sort(new FieldSortBuilder(sorter.getField()).order(sorter.getOrder()));
                }
            }
            searchRequest.source(searchSourceBuilder);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

            pageResult.setCurPage(page);
            pageResult.setPageSize(size);
            pageResult.setTotalRecords(response.getHits().getTotalHits().value);
            long pageCount = pageResult.getTotalRecords() / size + (pageResult.getTotalRecords() % size == 0 ? 0 : 1);
            pageResult.setTotalPages(pageCount);

            SearchHit[] searchHits = response.getHits().getHits();
            List<T> records = new ArrayList<>();
            for (SearchHit searchHit : searchHits)
            {
                log.debug("====>" + searchHit.getSourceAsString());
                records.add(JsonUtil.string2JavaObject(searchHit.getSourceAsString(), clz));
            }
            pageResult.setRecords(records);
            return pageResult;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

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
    @Override
    public <T> ESPageResult<T> pageQuery(String index, Integer page, Integer size, AbstractQueryBuilder queryBuilder, ESCallback<T> callback, Sorter... sorters) throws ESException
    {
        ESPageResult pageResult = new ESPageResult();
        try
        {
            if (null == page)
            {
                throw new ESException("page can't be null");
            }
            size = null == size ? 10 : size;    // 如果size为null，默认10

            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (null != queryBuilder)
            {
                searchSourceBuilder.query(queryBuilder);
            }
            // 根据page和size计算from
            int from = page * size - size;
            searchSourceBuilder.from(from);
            searchSourceBuilder.size(null == size ? 10 : size);
            if (null != sorters)
            {
                for (Sorter sorter : sorters)
                {
                    searchSourceBuilder.sort(new FieldSortBuilder(sorter.getField()).order(sorter.getOrder()));
                }
            }
            searchRequest.source(searchSourceBuilder);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

            pageResult.setCurPage(page);
            pageResult.setPageSize(size);
            pageResult.setTotalRecords(response.getHits().getTotalHits().value);
            long pageCount = pageResult.getTotalRecords() / size + (pageResult.getTotalRecords() % size == 0 ? 0 : 1);
            pageResult.setTotalPages(pageCount);

            SearchHit[] searchHits = response.getHits().getHits();
            List<T> records = new ArrayList<>();
            for (SearchHit searchHit : searchHits)
            {
                log.debug("====>" + searchHit.getSourceAsString());
                records.add(callback.wrapper(searchHit));
            }
            pageResult.setRecords(records);
            return pageResult;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 查询记录数量(同步)
     * @Date 16:48 2022/5/19
     * @Param [index, queryBuilder]
     * @return long
     **/
    @Override
    public long countForSync(String index, AbstractQueryBuilder queryBuilder) throws ESException
    {
        try
        {
            CountRequest countRequest = new CountRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (null != queryBuilder)
            {
                searchSourceBuilder.query(queryBuilder);
            }
            countRequest.source(searchSourceBuilder);
            CountResponse countResponse = client.count(countRequest, RequestOptions.DEFAULT);
            return countResponse.getCount();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 查询记录数量(异步)
     * @Date 16:48 2022/5/19
     * @Param [index, queryBuilder]
     * @return long
     **/
    @Override
    public void countForAsync(String index, AbstractQueryBuilder queryBuilder, ActionListener<CountResponse> listener) throws ESException
    {
        try
        {
            CountRequest countRequest = new CountRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (null != queryBuilder)
            {
                searchSourceBuilder.query(queryBuilder);
            }
            countRequest.source(searchSourceBuilder);
            client.countAsync(countRequest, RequestOptions.DEFAULT, listener);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return boolean
     * @Author summer
     * @Description 判断index是否存在
     * @Date 14:49 2022/1/4
     * @Param [index]
     **/
    @Override
    public boolean isIndexExist(String index) throws ESException
    {
        try
        {
            GetIndexRequest getRequest = new GetIndexRequest(index);
            getRequest.local(false);
            getRequest.humanReadable(true);
            boolean isExist = client.indices().exists(getRequest, RequestOptions.DEFAULT);
            return isExist;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    /**
     * @return boolean
     * @Author summer
     * @Description 查询index
     * @Date 14:14 2022/1/4
     * @Param [index]
     **/
    @Override
    public GetIndexResponse getIndex(String index) throws ESException
    {
        try
        {
            GetIndexRequest getRequest = new GetIndexRequest(index);
            getRequest.local(false);
            getRequest.humanReadable(true);
            GetIndexResponse response = client.indices().get(getRequest, RequestOptions.DEFAULT);
            return response;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ESException(e.getMessage(), e);
        }
    }

    public Boolean getDoValidate()
    {
        return doValidate;
    }

    public void setDoValidate(Boolean doValidate)
    {
        this.doValidate = doValidate;
    }

    public JsonValidator getJsonValidator()
    {
        return jsonValidator;
    }

    public void setJsonValidator(JsonValidator jsonValidator)
    {
        this.jsonValidator = jsonValidator;
    }
}
