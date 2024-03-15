package cn.nesc.itruscloud.contract;

import cn.nesc.itruscloud.bean.Contract;
import cn.nesc.itruscloud.bean.Signature;
import cn.nesc.itruscloud.bean.User;
import cn.nesc.itruscloud.bean.UserType;
import cn.nesc.itruscloud.exception.ItrusException;
import cn.nesc.itruscloud.parameter.CreateContractParam;
import cn.nesc.itruscloud.parameter.SealParam;
import cn.nesc.itruscloud.parameter.SignContractParam;
import cn.nesc.itruscloud.parameter.UserListParam;
import cn.nesc.itruscloud.result.*;
import cn.nesc.toolkit.common.codec.Base64Util;
import cn.nesc.toolkit.common.httpclient.HttpClientUtil;
import cn.nesc.toolkit.common.httpclient.HttpResponse;
import cn.nesc.toolkit.common.httpclient.Payload;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @ClassName ContractHandler
 * @Description
 * @Author summer
 * @Date 2022/9/5 15:11
 **/
public class ContractHandler
{
    private static Logger log = LoggerFactory.getLogger(ContractHandler.class);

    private String itrusUrl;

    private String apiId;

    private String apiSecret;

    private Boolean useCache = false;

    private Map<String, User> cache = new HashMap<>();

    /**
     *  获取用户列表URL
     **/
    private static final String USER_LIST_URL = "esp/user/getList";

    /**
     *  获取用户所有印章URL
     **/
    private static final String SEAL_LIST_URL = "esp/seal/getListByUser";

    /**
     *  获取用户印章URL
     **/
    private static final String SEAL_URL = "esp/seal/getByUser";

    /**
     *  创建新合同URL
     **/
    private static final String CREATE_CONTRACT_URL = "esp/contract/create";

    /**
     *  签署合同URL
     **/
    private static final String SIGN_CONTRACT_URL = "esp/contract/sign";

    private HttpClientUtil poolingHttpClientUtil = new HttpClientUtil();

    /**
     * @Author summer
     * @Description 查询用户列表(通用)
     * @Date 09:17 2022/9/6
     * @Param [param]
     * @return cn.nesc.itruscloud.result.UserListResult
     **/
    public UserListResult getUserList(UserListParam param) throws ItrusException
    {
        try
        {
            UserListResult userListResult = new UserListResult();
            String userListUrl = itrusUrl.endsWith("/") ? itrusUrl + USER_LIST_URL : itrusUrl + "/" + USER_LIST_URL;
            Map<String, String> header = new HashMap<>();
            handleSignature(header, param);
            System.out.println(1);
            HttpResponse resp = poolingHttpClientUtil.sendHttpPost(userListUrl, new Payload<UserListParam>(param), header);
            if (isSuccessful(resp))
            {
                userListResult = JsonUtil.string2JavaObject(resp.getReturnContent(), UserListResult.class);
            }
            return userListResult;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("用户列表查询失败", e);
        }
    }

    /**
     * @Author summer
     * @Description 通过名称查询用户列表
     * @Date 09:26 2022/9/6
     * @Param userName      用户名
     * @Param userType      用户类型
     * @Param pageNum       查询页码
     * @Param pageSize      每页条数
     * @return cn.nesc.itruscloud.result.UserListResult
     **/
    public UserListResult getUserListByName(String userName, UserType userType, int pageNum, int pageSize) throws ItrusException
    {
        UserListResult userListResult = null;
        if (this.useCache && null != this.cache.get(userName))
        {
            // 如果缓存中存在用户，直接返回
            userListResult = new UserListResult();
            ListResult<User> users = new ListResult<>();
            users.setTotal(1);
            List<User> list = Collections.nCopies(1, cache.get(userName));
            users.setList(list);
            userListResult.setUsers(users);
            log.debug("命中缓存, user ------->" + cache.get(userName));
            return userListResult;
        }

        UserListParam userListParam = new UserListParam();
        userListParam.setApiId(this.getApiId());
        userListParam.setPageNum(pageNum);
        userListParam.setPageSize(pageSize);
        switch (userType)
        {
            case ORG:
                userListParam.setOrgName(userName);
                userListParam.setUserType(1);
                break;
            case PERSONAL:
                userListParam.setFullname(userName);
                userListParam.setUserType(0);
                break;
            default:
                userListParam.setFullname(userName);
                userListParam.setUserType(0);
        }
        userListResult = getUserList(userListParam);
        if (userListResult.getUsers().getTotal() == 1 && this.useCache)
        {
            // 如果该用户名只存在一个，并且缓存开启，那么存入缓存
            this.cache.put(userName, userListResult.getUsers().getList().get(0));
        }
        return userListResult;
    }

    /**
     * @Author summer
     * @Description 查询印章列表
     * @Date 09:49 2022/9/6
     * @Param [userId, pageNum, pageSize]
     * @return cn.nesc.itruscloud.result.SealListResult
     **/
    public SealListResult getSealListByUser(String userId, int pageNum, int pageSize) throws ItrusException
    {
        try
        {
            SealListResult sealListResult = null;
            String sealListUrl = itrusUrl.endsWith("/") ? itrusUrl + SEAL_LIST_URL : itrusUrl + "/" + SEAL_LIST_URL;

            SealParam sealParam = new SealParam();
            sealParam.setApiId(this.apiId);
            sealParam.setUserId(userId);
            sealParam.setPageNum(pageNum);
            sealParam.setPageSize(pageSize);
            Map<String, String> header = new HashMap<>();
            handleSignature(header, sealParam);
            HttpResponse resp = poolingHttpClientUtil.sendHttpPost(sealListUrl, new Payload<SealParam>(sealParam), header);
            if (isSuccessful(resp))
            {
                sealListResult = JsonUtil.string2JavaObject(resp.getReturnContent(), SealListResult.class);
            }
            return sealListResult;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("印章列表查询失败", e);
        }
    }

    /**
     * @Author summer
     * @Description 查询印章
     * @Date 09:56 2022/9/6
     * @Param [userId, sealName]
     * @return cn.nesc.itruscloud.result.SealResult
     **/
    public SealResult getSealByUser(String userId, String sealName) throws ItrusException
    {
        try
        {
            SealResult sealResult = null;
            String sealListUrl = itrusUrl.endsWith("/") ? itrusUrl : itrusUrl + "/" + SEAL_URL;

            SealParam sealParam = new SealParam();
            sealParam.setApiId(this.apiId);
            sealParam.setUserId(userId);
            sealParam.setSealName(sealName);
            Map<String, String> header = new HashMap<>();
            handleSignature(header, sealParam);
            HttpResponse resp = poolingHttpClientUtil.sendHttpPost(sealListUrl, new Payload<>(sealParam), header);
            if (isSuccessful(resp))
            {
                sealResult = JsonUtil.string2JavaObject(resp.getReturnContent(), SealResult.class);
            }
            return sealResult;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("印章查询失败", e);
        }
    }

    /**
     * @Author summer
     * @Description 新建合同
     * @Date 16:53 2022/9/6
     * @Param [createContractParam]
     * @return cn.nesc.itruscloud.result.CreateContractResult
     **/
    public CreateContractResult createNewContract(CreateContractParam createContractParam) throws ItrusException
    {
        try
        {
            CreateContractResult contractResult = null;
            String sealListUrl = itrusUrl.endsWith("/") ? itrusUrl : itrusUrl + "/" + CREATE_CONTRACT_URL;

            Map<String, String> header = new HashMap<>();
            handleSignature(header, createContractParam);
            HttpResponse resp = poolingHttpClientUtil.sendHttpPost(sealListUrl, new Payload<>(createContractParam), header);
            if (isSuccessful(resp))
            {
                contractResult = JsonUtil.string2JavaObject(resp.getReturnContent(), CreateContractResult.class);
            }
            else
            {
                JsonNode jsonNode = JsonUtil.string2JsonObject(resp.getReturnContent());
                JsonNode message = jsonNode.get("message");
                throw new ItrusException(message.textValue());
            }
            return contractResult;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("创建新合同失败", e);
        }
    }

    /**
     * @Author summer
     * @Description 新建合同
     * @Date 16:31 2022/9/6
     * @Param [userId, title, docNum, contractFile, signKeyword]
     * @return cn.nesc.itruscloud.result.CreateContractResult
     **/
    public CreateContractResult createNewContract(String userId, String title, String docNum, File contractFile, String signKeyword) throws ItrusException
    {
        try
        {

            CreateContractParam createContractParam = new CreateContractParam();

            // 合同信息
            Contract contract = new Contract();

            String fixedTitle = title;
            if (StringUtils.isEmpty(title) || title.length() > 64)
            {
                fixedTitle = title.substring(0, 64);
            }
            contract.setTitle(fixedTitle);
            contract.setDocNum(docNum);
            contract.setDocType("pdf");
            contract.setDoc(Base64Util.encode(contractFile));
            // 印章位置信息
            Signature[] signatures = new Signature[1];
            Signature signature = new Signature();
            signature.setUserId(userId);
            signature.setStampStyle("none");
            signature.setPositionKeyword(signKeyword);
            signatures[0] = signature;
            contract.setSignatures(signatures);

            createContractParam.setContract(contract);
            createContractParam.setApiId(this.apiId);

            return createNewContract(createContractParam);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("创建新合同失败", e);
        }
    }

    /**
     * @Author summer
     * @Description 签署合同
     * @Date 16:45 2022/9/6
     * @Param [signContractParam]
     * @return cn.nesc.itruscloud.result.SignContractResult
     **/
    public SignContractResult signContract(SignContractParam signContractParam) throws ItrusException
    {
        try
        {
            SignContractResult signContractResult = null;
            String sealListUrl = itrusUrl.endsWith("/") ? itrusUrl : itrusUrl + "/" + SIGN_CONTRACT_URL;

            Map<String, String> header = new HashMap<>();
            handleSignature(header, signContractParam);
            HttpResponse resp = poolingHttpClientUtil.sendHttpPost(sealListUrl, new Payload<>(signContractParam), header);
            if (isSuccessful(resp))
            {
                signContractResult = JsonUtil.string2JavaObject(resp.getReturnContent(), SignContractResult.class);
            }
            else
            {
                JsonNode jsonNode = JsonUtil.string2JsonObject(resp.getReturnContent());
                JsonNode message = jsonNode.get("message");
                throw new ItrusException(message.textValue());
            }
            return signContractResult;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("签署合同失败", e);
        }
    }

    /**
     * @Author summer
     * @Description
     * @Date 16:47 2022/9/6
     * @Param contractId    合同ID
     * @Param userId        用户ID
     * @Param sealName      印章名
     * @return cn.nesc.itruscloud.result.SignContractResult
     **/
    public SignContractResult signContract(String contractId, String userId, String sealName) throws ItrusException
    {
        SignContractParam signContractParam = new SignContractParam();
        signContractParam.setContractId(contractId);
        signContractParam.setUserId(userId);
        signContractParam.setSealName(sealName);
        signContractParam.setDocRequired(true);
        signContractParam.setApiId(this.apiId);
        return signContract(signContractParam);
    }

    /**
     * @Author summer
     * @Description
     * @Date 10:25 2022/9/7
     * @Param userName  用户名
     * @Param userType  用户类型 UserType.PERSONAL | UserType.ORG
     * @Param sealName  签章名称
     * @Param title     合同标题
     * @Param docNum    合同档案号
     * @Param keyword   盖章处关键字
     * @Param contractFile  合同文件
     * @return java.lang.String
     **/
    public String signContractReadily(String userName, UserType userType, String sealName, String title, String docNum, String keyword, File contractFile) throws ItrusException
    {
        try
        {
            String userId, sealId, contractId, encodedFile = null;
            // 获取userId
            UserListResult userListResult = getUserListByName(userName, userType, 1, 10);
            if (userListResult.getUsers().getTotal() > 1)
            {
                throw new ItrusException("该用户存在多个,无法使用便捷方式进行签章");
            }
            userId = userListResult.getUsers().getList().get(0).getUserId();
            // 获取sealId
            // SealResult sealResult = getSealByUser(userId, sealName);
            // sealId = sealResult.getSeal().getSealId();
            // 创建新合同
            CreateContractResult contractResult = createNewContract(userId, title, docNum, contractFile, keyword);
            contractId = contractResult.getContract().getContractId();
            // 签署合同
            SignContractResult signContractResult = signContract(contractId, userId, sealName);
            encodedFile = signContractResult.getContract().getDoc();
            return encodedFile;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("签署合同失败", e);
        }
    }

    /**
     * @Author summer
     * @Description
     * @Date 10:25 2022/9/7
     * @Param userName
     * @Param userType
     * @Param sealName
     * @Param title
     * @Param docNum
     * @Param keyword
     * @Param contractFile
     * @Param callback      回调处理base64编码的文件
     *
     **/
    public void signContractReadily(String userName, UserType userType, String sealName, String title, String docNum, String keyword, File contractFile, Consumer<String> callback) throws ItrusException
    {
        try
        {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                String encodedDoc = null;
                try
                {
                    encodedDoc = signContractReadily(userName, userType, sealName, title, docNum, keyword, contractFile);
                }
                catch (ItrusException e)
                {
                    log.error(e.getMessage(), e);
                }
                return encodedDoc;
            });
            future.thenAccept(unHandledDoc -> {
                log.debug("异步处理base64编码文件");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                callback.accept(unHandledDoc);
            });
            log.debug("异步签署合同");
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("签署合同失败", e);
        }
    }

    /**
     * @Author summer
     * @Description
     * @Date 10:25 2022/9/7
     * @Param userName
     * @Param userType
     * @Param sealName
     * @Param title
     * @Param docNum
     * @Param keyword
     * @Param contractFile
     * @Param fos      写入文件的文件流
     *
     **/
    public void signContractReadily(String userName, UserType userType, String sealName, String title, String docNum, String keyword, File contractFile, FileOutputStream fos) throws ItrusException
    {
        try
        {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                String encodedDoc = null;
                try
                {
                    encodedDoc = signContractReadily(userName, userType, sealName, title, docNum, keyword, contractFile);
                }
                catch (ItrusException e)
                {
                    log.error(e.getMessage(), e);
                }
                return encodedDoc;
            });
            future.thenAccept(unHandledDoc -> {
                log.debug("异步处理base64编码文件");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Base64Util.decode(unHandledDoc, fos);
            });
            log.debug("异步签署合同");
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ItrusException("签署合同失败", e);
        }
    }

    private void handleSignature(Map<String, String> header, Object payload)
    {
        try
        {
            String hmac_sha1_algorithm = "HmacSHA1";
            byte[] rawData = JsonUtil.javaObject2String(payload).getBytes(StandardCharsets.UTF_8);
            Key signingKey = new SecretKeySpec(apiSecret.getBytes(), hmac_sha1_algorithm);
            Mac mac = Mac.getInstance(hmac_sha1_algorithm);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(rawData);
            String hmac = "HMAC-SHA1 ";
            header.put("Content-Signature", hmac + Base64.getEncoder().encodeToString(rawHmac));

        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvalidKeyException e)
        {
            throw new RuntimeException(e);
        }
    }


    private boolean isSuccessful(HttpResponse response)
    {
        // 判断返回状态码
        if (200 == response.getReturnCode())
        {
            // 判断返回code
            JsonNode jsonNode = JsonUtil.string2JsonObject(response.getReturnContent());
            JsonNode code = jsonNode.get("code");
            if (null == code)
            {
                return false;
            }
            return code.asInt() == 0 ? true : false;
        }
        return false;
    }

    public String getItrusUrl()
    {
        return itrusUrl;
    }

    public void setItrusUrl(String itrusUrl)
    {
        this.itrusUrl = itrusUrl;
    }

    public String getApiId()
    {
        return apiId;
    }

    public void setApiId(String apiId)
    {
        this.apiId = apiId;
    }

    public String getApiSecret()
    {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;
    }

    public Boolean getUseCache()
    {
        return useCache;
    }

    public void setUseCache(Boolean useCache)
    {
        this.useCache = useCache;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException
    {
        ContractHandler handler = new ContractHandler();
        handler.setItrusUrl("http://localhost:8090/apigate");
        handler.setApiId("xxxxxx");
        handler.setApiSecret("xxxxxxxxxxxxxxxx");
        handler.setUseCache(true);
        try
        {
            // 获取用户列表
            UserListParam userListParam = new UserListParam();
            userListParam.setApiId(handler.getApiId());
            userListParam.setPageNum(1);
            userListParam.setPageSize(10);
//            userListParam.setFullname("乐*");
            userListParam.setUserType(1);
            userListParam.setOrgName("有限公司");
            //        userListParam.setIdCardNum();
            //        userListParam.setMobile();

            UserListResult userListResult = handler.getUserList(userListParam);
            System.out.println(userListResult);
//
//            SealListResult sealListResult = handler.getSealListByUser("L02172JY1LX64YA", 1, 10);
//            System.out.println(sealListResult);
//
//            SealResult sealResult = handler.getSealByUser("a377a523-3407-472f-9f67-a1ac9421d780", "法人章");
//            System.out.println(sealResult);

//            CreateContractResult contractResult = handler.createNewContract("a377a523-3407-472f-9f67-a1ac9421d780", "测试合同2", "HT-1235", new File(""), "中国标准时间");
//            System.out.println(contractResult.getContract().getContractId());

//            SignContractResult signContractResult = handler.signContract("4d298eab-565f-49e5-bd4a-1962003423c1", "a377a523-3407-472f-9f67-a1ac9421d780", "法人章");
//            System.out.println("合同 ---->" + signContractResult.getContract().getDoc());
//            Base64Util.decode(signContractResult.getContract().getDoc(), new File(""));


            handler.signContractReadily("有限公司", UserType.ORG, "sealName", "title", "HT-555", "keyword", new File("/filepath/unsealFile.pdf"), new FileOutputStream(new File("/filepath/sealedFile.pdf")));
            System.in.read();
        }
        catch (ItrusException e)
        {
            e.printStackTrace();
        }
    }
}
