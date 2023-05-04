package cn.nesc.toolkit.es;

import cn.nesc.toolkit.es.common.DateFormat;
import cn.nesc.toolkit.es.common.ESField;
import cn.nesc.toolkit.es.common.properties.ESDate;
import cn.nesc.toolkit.es.common.properties.ESInteger;
import cn.nesc.toolkit.es.common.properties.ESKeyword;
import cn.nesc.toolkit.es.common.properties.ESText;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EncapCreateIndexTest
{
    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test()
    {
        try
        {
            UsernamePasswordClientFactory clientFactory = new HLClientFactory();
            clientFactory.setNodes("10.6.22.187:9200");
            IESClient client = clientFactory.getClient();

            List<ESField> fields = new ArrayList<>();
            fields.add(new ESField("id", new ESInteger()));
            fields.add(new ESField("code", new ESKeyword()));
            fields.add(new ESField("name", new ESText()));
            fields.add(new ESField("birthday", new ESDate(DateFormat.CLASSIC_DATETIME)));

            CreateIndexResponse response = client.createIndex("idx_3", 2, 1, fields);
            log.debug("isAcknowledged --->" + response.isAcknowledged());
            log.debug("isShardsAcknowledged --->" + response.isShardsAcknowledged());
            log.debug("" + response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    class PlainObject
    {
        private Integer id;
        private String code;
        private String name;
        private Date birthday;

        public Integer getId()
        {
            return id;
        }

        public void setId(Integer id)
        {
            this.id = id;
        }

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public Date getBirthday()
        {
            return birthday;
        }

        public void setBirthday(Date birthday)
        {
            this.birthday = birthday;
        }
    }

}
