/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: SpringClientFactoryBean
 * Author:   summer
 * Date:     2022/5/10 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.spring;

import cn.nesc.toolkit.es.Credential;
import cn.nesc.toolkit.es.IESClientFactory;
import cn.nesc.toolkit.es.UsernamePasswordClientFactory;
import org.springframework.context.ApplicationEvent;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * @ClassName SpringClientFactoryBean
 * @Description
 * @Author summer
 * @Date 2022/5/10 14:25
 **/
public class SpringClientFactoryBean
{
    private IESClientFactory esClientFactory;

    private String nodes;

    private Credential credential;


    public void onApplicationEvent(ApplicationEvent applicationEvent)
    {

    }

    public IESClientFactory getObject() throws Exception
    {
        Optional<IESClientFactory> clientFactory = Optional.empty();
        ServiceLoader<IESClientFactory> clientFactories = ServiceLoader.load(IESClientFactory.class);
        if (null != clientFactories)
        {
            clientFactory = StreamSupport.stream(clientFactories.spliterator(), false).findFirst();
        }
        esClientFactory = clientFactory.orElse(null);
        if (esClientFactory instanceof UsernamePasswordClientFactory)
        {
            ((UsernamePasswordClientFactory) esClientFactory).setNodes(nodes);
            ((UsernamePasswordClientFactory) esClientFactory).setCredential(credential);
        }
        return esClientFactory;
    }

    public Class<?> getObjectType()
    {
        return this.esClientFactory == null ? IESClientFactory.class : this.esClientFactory.getClass();
    }

    public boolean isSingleton()
    {
        return true;
    }

    public void afterPropertiesSet() throws Exception
    {

    }

    public String getNodes()
    {
        return nodes;
    }

    public void setNodes(String nodes)
    {
        this.nodes = nodes;
    }

    public Credential getCredential()
    {
        return credential;
    }

    public void setCredential(Credential credential)
    {
        this.credential = credential;
    }
}
