package cn.nesc.toolkit.es;

import org.elasticsearch.action.update.UpdateResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EncapUpdateTest
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
            /** 通过po更新 **/
//            PlainObject po = new PlainObject();
//            po.setId(1000);
//            po.setCode("1000");
//            po.setName("里斯3");
//            po.setBirthday("1990-12-31 20:00:00");
//            UpdateResponse response = client.updateDoc("idx_1", "xU-CD34BxhGHxNBM1_O3", po);
//            log.debug("" + response);

            /** 通过map更新 **/
//            Map<String, Object> data = new HashMap<>();
//            data.put("name", "里斯4");
//            UpdateResponse response = client.updateDoc("idx_1", "xU-CD34BxhGHxNBM1_O3", data);
//            log.debug("" + response);


            /** 通过po更新或创建 **/
//            PlainObject po = new PlainObject();
//            po.setId(1002);
//            po.setCode("1002");
//            po.setName("里斯7");
//            po.setBirthday("1990-12-31 21:00:00");
//            UpdateResponse response = client.updateOrInsertDoc("idx_1", "1002", po);
//            log.debug("" + response);


            /** 通过map更新 **/
            Map<String, Object> data = new HashMap<>();
            data.put("id", 1003);
            data.put("code", "1003");
            data.put("name", "里斯10");
            data.put("birthday", "1992-12-31 21:00:00");
            UpdateResponse response = client.updateOrInsertDoc("idx_1", "1003", data);
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
