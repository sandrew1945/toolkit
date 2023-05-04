package cn.nesc.tookit.es.springboot.autoconfigure;

import cn.nesc.toolkit.es.Credential;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by summer on 2019/8/7.
 */
@ConfigurationProperties("spring.toolkit.es")
public class ESConfigurePerproties
{
    private String nodes;

    private Credential credential;


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
