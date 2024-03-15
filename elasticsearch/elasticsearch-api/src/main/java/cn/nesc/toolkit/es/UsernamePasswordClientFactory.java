package cn.nesc.toolkit.es;

/**
 * @ClassName UsernamePasswordClientFactory
 * @Description
 * @Author summer
 * @Date 2022/5/10 13:40
 **/
public abstract class UsernamePasswordClientFactory implements IESClientFactory
{
    /**
     * es服务地址,例如: 127.0.0.1:9200 多个请使用,分割，例如: 127.0.0.1:9200,127.0.0.1:9201
     **/
    protected String nodes;

    /**
     *  凭证
     **/
    protected Credential credential;

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
