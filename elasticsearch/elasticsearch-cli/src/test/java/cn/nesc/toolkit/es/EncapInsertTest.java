package cn.nesc.toolkit.es;

import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncapInsertTest
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

            /********* json字符串创建 **********/
            String jsonStr = "{" + "\"uid\":\"12666\","+ "\"phone\":\"12345678966\","+ "\"msgcode\":\"5\"," + "\"sendtime\":\"2019-06-14 01:57:04\","
                            + "\"message\":\"666xeniqwneennn qwen\"" + "}";
//            String jsonStr = "oqmweqwjqweqwe";
//            client.addDocWithId("test1", "7", jsonStr);
//            client.addDoc("test1", "_doc", jsonStr);

            /********* java对象创建 **********/
            PlainObject user = new PlainObject();
            user.setId(103);
            user.setCode("103");
            user.setName("孙六");
            user.setBirthday("1988-01-30 19:30:20");
            IndexResponse response = client.addDoc("idx_1", user);
            log.debug(" ===> " + response);
            /********* map对象创建 **********/
//            Map<String, Object> data = new HashMap<>();
//            data.put("id", 103);
//            data.put("code", "103");
//            data.put("name", "戴维");
//            data.put("birthday", "20001001");
//            client.addDoc("test2", "user", data);

        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

    }

    class PlainObject
    {
        private Integer id;
        private String code;
        private String name;
        private String birthday;

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

        public String getBirthday()
        {
            return birthday;
        }

        public void setBirthday(String birthday)
        {
            this.birthday = birthday;
        }
    }

}
