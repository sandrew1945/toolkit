package cn.nesc.toolkit.es;

import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;

public class EncapIndexSearchTest
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

            boolean isExist = client.isIndexExist("idx_5");
            log.debug("索引idx_1是否存在:{}", isExist);
            GetIndexResponse response = client.getIndex("idx_2");
            log.debug("Alias:" + response.getAliases());
            log.debug("Indices[]:" + Arrays.asList(response.getIndices()));
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
