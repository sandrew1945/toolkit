package cn.nesc.itruscloud.bean;

/**
 * @ClassName Contract
 * @Description 合同信息
 * @Author summer
 * @Date 2022/9/6 14:59
 **/
public class Contract
{
    private String contractId;

    private String title;

    private String signatureData;

    private String docData;

    private Integer status;

//    private Date dateCreated;

//    private Date lastUpdated;

    private String docHash;

    private String docNum;          // 合同创建者制定的文档编号

    private String docType;         // 合同文档格式，支持pdf

    private String docPath;         // 合同所在的目录

    private String docPathDesc;     // 用于对目录中多个合同的描述，便于分类与查找

    private String doc;             // 以BASE64编码的合同文档

    private Signature[] signatures; // 签章位置信息数组

    public String getContractId()
    {
        return contractId;
    }

    public void setContractId(String contractId)
    {
        this.contractId = contractId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSignatureData()
    {
        return signatureData;
    }

    public void setSignatureData(String signatureData)
    {
        this.signatureData = signatureData;
    }

    public String getDocData()
    {
        return docData;
    }

    public void setDocData(String docData)
    {
        this.docData = docData;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getDocHash()
    {
        return docHash;
    }

    public void setDocHash(String docHash)
    {
        this.docHash = docHash;
    }

    public String getDocNum()
    {
        return docNum;
    }

    public void setDocNum(String docNum)
    {
        this.docNum = docNum;
    }

    public String getDocType()
    {
        return docType;
    }

    public void setDocType(String docType)
    {
        this.docType = docType;
    }

    public String getDocPath()
    {
        return docPath;
    }

    public void setDocPath(String docPath)
    {
        this.docPath = docPath;
    }

    public String getDocPathDesc()
    {
        return docPathDesc;
    }

    public void setDocPathDesc(String docPathDesc)
    {
        this.docPathDesc = docPathDesc;
    }

    public String getDoc()
    {
        return doc;
    }

    public void setDoc(String doc)
    {
        this.doc = doc;
    }

    public Signature[] getSignatures()
    {
        return signatures;
    }

    public void setSignatures(Signature[] signatures)
    {
        this.signatures = signatures;
    }
}
