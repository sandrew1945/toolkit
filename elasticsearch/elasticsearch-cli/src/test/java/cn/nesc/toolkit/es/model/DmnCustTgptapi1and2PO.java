/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: DmnCustTgptapi1and2PO
 * Author:   summer
 * Date:     2022/1/10 15:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.model;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.bean.EqualPack;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.Pack;

import java.sql.Clob;
import java.util.Date;

/**
 * @ClassName DmnCustTgptapi1and2PO
 * @Description
 * @Author summer
 * @Date 2022/1/10 15:43
 **/
@TableName("DMN_CUST_TGPTAPI1AND2")
public class DmnCustTgptapi1and2PO extends PO
{

    public DmnCustTgptapi1and2PO()
    {
    }



    @ColumnName(value = "CUST_NO", isPK = false, autoIncrement = false)
    private Pack<String> custNo;

    @ColumnName(value = "CUST_NAME", isPK = false, autoIncrement = false)
    private Pack<String> custName;

    @ColumnName(value = "ORG_NO", isPK = false, autoIncrement = false)
    private Pack<String> orgNo;

    @ColumnName(value = "ORG_NAME", isPK = false, autoIncrement = false)
    private Pack<String> orgName;

    @ColumnName(value = "OPEN_DT", isPK = false, autoIncrement = false)
    private Pack<String> openDt;

    @ColumnName(value = "ALL_ZC", isPK = false, autoIncrement = false)
    private Pack<Double> allZc;

    @ColumnName(value = "ALL_ZC_AVG", isPK = false, autoIncrement = false)
    private Pack<Double> allZcAvg;

    @ColumnName(value = "ZC_XJ", isPK = false, autoIncrement = false)
    private Pack<Double> zcXj;

    @ColumnName(value = "ALL_ZC_MAX", isPK = false, autoIncrement = false)
    private Pack<Double> allZcMax;

    @ColumnName(value = "ALL_ZC_MAX_DT", isPK = false, autoIncrement = false)
    private Pack<Date> allZcMaxDt;

    @ColumnName(value = "SZ_OUT", isPK = false, autoIncrement = false)
    private Pack<Double> szOut;

    @ColumnName(value = "SZ_IN", isPK = false, autoIncrement = false)
    private Pack<Double> szIn;

    @ColumnName(value = "SZ_GJL", isPK = false, autoIncrement = false)
    private Pack<Double> szGjl;

    @ColumnName(value = "GJJYL_CN_Y", isPK = false, autoIncrement = false)
    private Pack<Double> gjjylCnY;

    @ColumnName(value = "JJJYL_CN_Y", isPK = false, autoIncrement = false)
    private Pack<Double> jjjylCnY;

    @ColumnName(value = "JJJYL_CW_Y", isPK = false, autoIncrement = false)
    private Pack<Double> jjjylCwY;

    @ColumnName(value = "PRD_Y", isPK = false, autoIncrement = false)
    private Pack<Double> prdY;

    @ColumnName(value = "GJJYPC_CN_M", isPK = false, autoIncrement = false)
    private Pack<String> gjjypcCnM;

    @ColumnName(value = "GJJYPC_CW_M", isPK = false, autoIncrement = false)
    private Pack<String> gjjypcCwM;

    @ColumnName(value = "GJJYJL", isPK = false, autoIncrement = false)
    private Pack<String> gjjyjl;

    @ColumnName(value = "CPBY_Y", isPK = false, autoIncrement = false)
    private Pack<Double> cpbyY;

    @ColumnName(value = "RET_DATE", isPK = false, autoIncrement = false)
    private Pack<String> retDate;

    @ColumnName(value = "SYL_M", isPK = false, autoIncrement = false)
    private Pack<String> sylM;

    @ColumnName(value = "SYL_GP_M", isPK = false, autoIncrement = false)
    private Pack<String> sylGpM;

    @ColumnName(value = "SYL_GMJJ_M", isPK = false, autoIncrement = false)
    private Pack<String> sylGmjjM;

    @ColumnName(value = "SYL_LC_M", isPK = false, autoIncrement = false)
    private Pack<String> sylLcM;

    @ColumnName(value = "SYL_HY", isPK = false, autoIncrement = false)
    private Pack<String> sylHy;

    @ColumnName(value = "SYL_GP_HY", isPK = false, autoIncrement = false)
    private Pack<String> sylGpHy;

    @ColumnName(value = "SYL_GMJJ_HY", isPK = false, autoIncrement = false)
    private Pack<String> sylGmjjHy;

    @ColumnName(value = "SYL_LC_HY", isPK = false, autoIncrement = false)
    private Pack<String> sylLcHy;

    @ColumnName(value = "SYL_Y", isPK = false, autoIncrement = false)
    private Pack<String> sylY;

    @ColumnName(value = "SYL_GP_Y", isPK = false, autoIncrement = false)
    private Pack<String> sylGpY;

    @ColumnName(value = "SYL_GMJJ_Y", isPK = false, autoIncrement = false)
    private Pack<String> sylGmjjY;

    @ColumnName(value = "SYL_LC_Y", isPK = false, autoIncrement = false)
    private Pack<String> sylLcY;

    @ColumnName(value = "KHCS_Y", isPK = false, autoIncrement = false)
    private Pack<Double> khcsY;

    @ColumnName(value = "DATA_BIZ_DATE", isPK = false, autoIncrement = false)
    private Pack<String> dataBizDate;

    @ColumnName(value = "SERVICE_RELATION", isPK = false, autoIncrement = false)
    private Pack<String> serviceRelation;

    @ColumnName(value = "ASSESS_RELATION", isPK = false, autoIncrement = false)
    private Pack<String> assessRelation;

    @ColumnName(value = "SALE_RELATION", isPK = false, autoIncrement = false)
    private Pack<String> saleRelation;

    @ColumnName(value = "NORM_DEV_RELATION", isPK = false, autoIncrement = false)
    private Pack<String> normDevRelation;

    @ColumnName(value = "CRED_DEV_RELATION", isPK = false, autoIncrement = false)
    private Pack<String> credDevRelation;

    @ColumnName(value = "OPT_DEV_RELATION", isPK = false, autoIncrement = false)
    private Pack<String> optDevRelation;

    @ColumnName(value = "INV_ADVR_SIGN", isPK = false, autoIncrement = false)
    private Pack<String> invAdvrSign;

    @ColumnName(value = "PD_ADVR_SIGN", isPK = false, autoIncrement = false)
    private Pack<String> pdAdvrSign;

    @ColumnName(value = "SERVICE_RELATION_NO", isPK = false, autoIncrement = false)
    private Pack<String> serviceRelationNo;

    @ColumnName(value = "AGFIN_PD_INC_SERV_INCM", isPK = false, autoIncrement = false)
    private Pack<Double> agfinPdIncServIncm;

    @ColumnName(value = "NET_CMS_INCM", isPK = false, autoIncrement = false)
    private Pack<Double> netCmsIncm;

    @ColumnName(value = "INT_INCM", isPK = false, autoIncrement = false)
    private Pack<Double> intIncm;

    @ColumnName(value = "FUND_CODE", isPK = false, autoIncrement = false)
    private Pack<String> fundCode;

    @ColumnName(value = "STOCK_CODE", isPK = false, autoIncrement = false)
    private Pack<String> stockCode;


    public void setCustNo(String custNo)
    {
        if (null == this.custNo)
        {
            this.custNo = new EqualPack<String>();
        }
        this.custNo.setValue(custNo);
    }

    public void setCustNo(Pack<String> custNo)
    {
        this.custNo = custNo;
    }

    public String getCustNo()
    {
        return this.custNo == null ? null : this.custNo.getValue();
    }

    public void setCustName(String custName)
    {
        if (null == this.custName)
        {
            this.custName = new EqualPack<String>();
        }
        this.custName.setValue(custName);
    }

    public void setCustName(Pack<String> custName)
    {
        this.custName = custName;
    }

    public String getCustName()
    {
        return this.custName == null ? null : this.custName.getValue();
    }

    public void setOrgNo(String orgNo)
    {
        if (null == this.orgNo)
        {
            this.orgNo = new EqualPack<String>();
        }
        this.orgNo.setValue(orgNo);
    }

    public void setOrgNo(Pack<String> orgNo)
    {
        this.orgNo = orgNo;
    }

    public String getOrgNo()
    {
        return this.orgNo == null ? null : this.orgNo.getValue();
    }

    public void setOrgName(String orgName)
    {
        if (null == this.orgName)
        {
            this.orgName = new EqualPack<String>();
        }
        this.orgName.setValue(orgName);
    }

    public void setOrgName(Pack<String> orgName)
    {
        this.orgName = orgName;
    }

    public String getOrgName()
    {
        return this.orgName == null ? null : this.orgName.getValue();
    }

    public void setOpenDt(String openDt)
    {
        if (null == this.openDt)
        {
            this.openDt = new EqualPack<String>();
        }
        this.openDt.setValue(openDt);
    }

    public void setOpenDt(Pack<String> openDt)
    {
        this.openDt = openDt;
    }

    public String getOpenDt()
    {
        return this.openDt == null ? null : this.openDt.getValue();
    }

    public void setAllZc(Double allZc)
    {
        if (null == this.allZc)
        {
            this.allZc = new EqualPack<Double>();
        }
        this.allZc.setValue(allZc);
    }

    public void setAllZc(Pack<Double> allZc)
    {
        this.allZc = allZc;
    }

    public Double getAllZc()
    {
        return this.allZc == null ? null : this.allZc.getValue();
    }

    public void setAllZcAvg(Double allZcAvg)
    {
        if (null == this.allZcAvg)
        {
            this.allZcAvg = new EqualPack<Double>();
        }
        this.allZcAvg.setValue(allZcAvg);
    }

    public void setAllZcAvg(Pack<Double> allZcAvg)
    {
        this.allZcAvg = allZcAvg;
    }

    public Double getAllZcAvg()
    {
        return this.allZcAvg == null ? null : this.allZcAvg.getValue();
    }

    public void setZcXj(Double zcXj)
    {
        if (null == this.zcXj)
        {
            this.zcXj = new EqualPack<Double>();
        }
        this.zcXj.setValue(zcXj);
    }

    public void setZcXj(Pack<Double> zcXj)
    {
        this.zcXj = zcXj;
    }

    public Double getZcXj()
    {
        return this.zcXj == null ? null : this.zcXj.getValue();
    }

    public void setAllZcMax(Double allZcMax)
    {
        if (null == this.allZcMax)
        {
            this.allZcMax = new EqualPack<Double>();
        }
        this.allZcMax.setValue(allZcMax);
    }

    public void setAllZcMax(Pack<Double> allZcMax)
    {
        this.allZcMax = allZcMax;
    }

    public Double getAllZcMax()
    {
        return this.allZcMax == null ? null : this.allZcMax.getValue();
    }

    public void setAllZcMaxDt(Date allZcMaxDt)
    {
        if (null == this.allZcMaxDt)
        {
            this.allZcMaxDt = new EqualPack<Date>();
        }
        this.allZcMaxDt.setValue(allZcMaxDt);
    }

    public void setAllZcMaxDt(Pack<Date> allZcMaxDt)
    {
        this.allZcMaxDt = allZcMaxDt;
    }

    public Date getAllZcMaxDt()
    {
        return this.allZcMaxDt == null ? null : this.allZcMaxDt.getValue();
    }

    public void setSzOut(Double szOut)
    {
        if (null == this.szOut)
        {
            this.szOut = new EqualPack<Double>();
        }
        this.szOut.setValue(szOut);
    }

    public void setSzOut(Pack<Double> szOut)
    {
        this.szOut = szOut;
    }

    public Double getSzOut()
    {
        return this.szOut == null ? null : this.szOut.getValue();
    }

    public void setSzIn(Double szIn)
    {
        if (null == this.szIn)
        {
            this.szIn = new EqualPack<Double>();
        }
        this.szIn.setValue(szIn);
    }

    public void setSzIn(Pack<Double> szIn)
    {
        this.szIn = szIn;
    }

    public Double getSzIn()
    {
        return this.szIn == null ? null : this.szIn.getValue();
    }

    public void setSzGjl(Double szGjl)
    {
        if (null == this.szGjl)
        {
            this.szGjl = new EqualPack<Double>();
        }
        this.szGjl.setValue(szGjl);
    }

    public void setSzGjl(Pack<Double> szGjl)
    {
        this.szGjl = szGjl;
    }

    public Double getSzGjl()
    {
        return this.szGjl == null ? null : this.szGjl.getValue();
    }

    public void setGjjylCnY(Double gjjylCnY)
    {
        if (null == this.gjjylCnY)
        {
            this.gjjylCnY = new EqualPack<Double>();
        }
        this.gjjylCnY.setValue(gjjylCnY);
    }

    public void setGjjylCnY(Pack<Double> gjjylCnY)
    {
        this.gjjylCnY = gjjylCnY;
    }

    public Double getGjjylCnY()
    {
        return this.gjjylCnY == null ? null : this.gjjylCnY.getValue();
    }

    public void setJjjylCnY(Double jjjylCnY)
    {
        if (null == this.jjjylCnY)
        {
            this.jjjylCnY = new EqualPack<Double>();
        }
        this.jjjylCnY.setValue(jjjylCnY);
    }

    public void setJjjylCnY(Pack<Double> jjjylCnY)
    {
        this.jjjylCnY = jjjylCnY;
    }

    public Double getJjjylCnY()
    {
        return this.jjjylCnY == null ? null : this.jjjylCnY.getValue();
    }

    public void setJjjylCwY(Double jjjylCwY)
    {
        if (null == this.jjjylCwY)
        {
            this.jjjylCwY = new EqualPack<Double>();
        }
        this.jjjylCwY.setValue(jjjylCwY);
    }

    public void setJjjylCwY(Pack<Double> jjjylCwY)
    {
        this.jjjylCwY = jjjylCwY;
    }

    public Double getJjjylCwY()
    {
        return this.jjjylCwY == null ? null : this.jjjylCwY.getValue();
    }

    public void setPrdY(Double prdY)
    {
        if (null == this.prdY)
        {
            this.prdY = new EqualPack<Double>();
        }
        this.prdY.setValue(prdY);
    }

    public void setPrdY(Pack<Double> prdY)
    {
        this.prdY = prdY;
    }

    public Double getPrdY()
    {
        return this.prdY == null ? null : this.prdY.getValue();
    }

    public void setGjjypcCnM(String gjjypcCnM)
    {
        if (null == this.gjjypcCnM)
        {
            this.gjjypcCnM = new EqualPack<String>();
        }
        this.gjjypcCnM.setValue(gjjypcCnM);
    }

    public void setGjjypcCnM(Pack<String> gjjypcCnM)
    {
        this.gjjypcCnM = gjjypcCnM;
    }

    public String getGjjypcCnM()
    {
        return this.gjjypcCnM == null ? null : this.gjjypcCnM.getValue();
    }

    public void setGjjypcCwM(String gjjypcCwM)
    {
        if (null == this.gjjypcCwM)
        {
            this.gjjypcCwM = new EqualPack<String>();
        }
        this.gjjypcCwM.setValue(gjjypcCwM);
    }

    public void setGjjypcCwM(Pack<String> gjjypcCwM)
    {
        this.gjjypcCwM = gjjypcCwM;
    }

    public String getGjjypcCwM()
    {
        return this.gjjypcCwM == null ? null : this.gjjypcCwM.getValue();
    }

    public void setGjjyjl(String gjjyjl)
    {
        if (null == this.gjjyjl)
        {
            this.gjjyjl = new EqualPack<String>();
        }
        this.gjjyjl.setValue(gjjyjl);
    }

    public void setGjjyjl(Pack<String> gjjyjl)
    {
        this.gjjyjl = gjjyjl;
    }

    public String getGjjyjl()
    {
        return this.gjjyjl == null ? null : this.gjjyjl.getValue();
    }

    public void setCpbyY(Double cpbyY)
    {
        if (null == this.cpbyY)
        {
            this.cpbyY = new EqualPack<Double>();
        }
        this.cpbyY.setValue(cpbyY);
    }

    public void setCpbyY(Pack<Double> cpbyY)
    {
        this.cpbyY = cpbyY;
    }

    public Double getCpbyY()
    {
        return this.cpbyY == null ? null : this.cpbyY.getValue();
    }

    public void setRetDate(String retDate)
    {
        if (null == this.retDate)
        {
            this.retDate = new EqualPack<String>();
        }
        this.retDate.setValue(retDate);
    }

    public void setRetDate(Pack<String> retDate)
    {
        this.retDate = retDate;
    }

    public String getRetDate()
    {
        return this.retDate == null ? null : this.retDate.getValue();
    }

    public void setSylM(String sylM)
    {
        if (null == this.sylM)
        {
            this.sylM = new EqualPack<String>();
        }
        this.sylM.setValue(sylM);
    }

    public void setSylM(Pack<String> sylM)
    {
        this.sylM = sylM;
    }

    public String getSylM()
    {
        return this.sylM == null ? null : this.sylM.getValue();
    }

    public void setSylGpM(String sylGpM)
    {
        if (null == this.sylGpM)
        {
            this.sylGpM = new EqualPack<String>();
        }
        this.sylGpM.setValue(sylGpM);
    }

    public void setSylGpM(Pack<String> sylGpM)
    {
        this.sylGpM = sylGpM;
    }

    public String getSylGpM()
    {
        return this.sylGpM == null ? null : this.sylGpM.getValue();
    }

    public void setSylGmjjM(String sylGmjjM)
    {
        if (null == this.sylGmjjM)
        {
            this.sylGmjjM = new EqualPack<String>();
        }
        this.sylGmjjM.setValue(sylGmjjM);
    }

    public void setSylGmjjM(Pack<String> sylGmjjM)
    {
        this.sylGmjjM = sylGmjjM;
    }

    public String getSylGmjjM()
    {
        return this.sylGmjjM == null ? null : this.sylGmjjM.getValue();
    }

    public void setSylLcM(String sylLcM)
    {
        if (null == this.sylLcM)
        {
            this.sylLcM = new EqualPack<String>();
        }
        this.sylLcM.setValue(sylLcM);
    }

    public void setSylLcM(Pack<String> sylLcM)
    {
        this.sylLcM = sylLcM;
    }

    public String getSylLcM()
    {
        return this.sylLcM == null ? null : this.sylLcM.getValue();
    }

    public void setSylHy(String sylHy)
    {
        if (null == this.sylHy)
        {
            this.sylHy = new EqualPack<String>();
        }
        this.sylHy.setValue(sylHy);
    }

    public void setSylHy(Pack<String> sylHy)
    {
        this.sylHy = sylHy;
    }

    public String getSylHy()
    {
        return this.sylHy == null ? null : this.sylHy.getValue();
    }

    public void setSylGpHy(String sylGpHy)
    {
        if (null == this.sylGpHy)
        {
            this.sylGpHy = new EqualPack<String>();
        }
        this.sylGpHy.setValue(sylGpHy);
    }

    public void setSylGpHy(Pack<String> sylGpHy)
    {
        this.sylGpHy = sylGpHy;
    }

    public String getSylGpHy()
    {
        return this.sylGpHy == null ? null : this.sylGpHy.getValue();
    }

    public void setSylGmjjHy(String sylGmjjHy)
    {
        if (null == this.sylGmjjHy)
        {
            this.sylGmjjHy = new EqualPack<String>();
        }
        this.sylGmjjHy.setValue(sylGmjjHy);
    }

    public void setSylGmjjHy(Pack<String> sylGmjjHy)
    {
        this.sylGmjjHy = sylGmjjHy;
    }

    public String getSylGmjjHy()
    {
        return this.sylGmjjHy == null ? null : this.sylGmjjHy.getValue();
    }

    public void setSylLcHy(String sylLcHy)
    {
        if (null == this.sylLcHy)
        {
            this.sylLcHy = new EqualPack<String>();
        }
        this.sylLcHy.setValue(sylLcHy);
    }

    public void setSylLcHy(Pack<String> sylLcHy)
    {
        this.sylLcHy = sylLcHy;
    }

    public String getSylLcHy()
    {
        return this.sylLcHy == null ? null : this.sylLcHy.getValue();
    }

    public void setSylY(String sylY)
    {
        if (null == this.sylY)
        {
            this.sylY = new EqualPack<String>();
        }
        this.sylY.setValue(sylY);
    }

    public void setSylY(Pack<String> sylY)
    {
        this.sylY = sylY;
    }

    public String getSylY()
    {
        return this.sylY == null ? null : this.sylY.getValue();
    }

    public void setSylGpY(String sylGpY)
    {
        if (null == this.sylGpY)
        {
            this.sylGpY = new EqualPack<String>();
        }
        this.sylGpY.setValue(sylGpY);
    }

    public void setSylGpY(Pack<String> sylGpY)
    {
        this.sylGpY = sylGpY;
    }

    public String getSylGpY()
    {
        return this.sylGpY == null ? null : this.sylGpY.getValue();
    }

    public void setSylGmjjY(String sylGmjjY)
    {
        if (null == this.sylGmjjY)
        {
            this.sylGmjjY = new EqualPack<String>();
        }
        this.sylGmjjY.setValue(sylGmjjY);
    }

    public void setSylGmjjY(Pack<String> sylGmjjY)
    {
        this.sylGmjjY = sylGmjjY;
    }

    public String getSylGmjjY()
    {
        return this.sylGmjjY == null ? null : this.sylGmjjY.getValue();
    }

    public void setSylLcY(String sylLcY)
    {
        if (null == this.sylLcY)
        {
            this.sylLcY = new EqualPack<String>();
        }
        this.sylLcY.setValue(sylLcY);
    }

    public void setSylLcY(Pack<String> sylLcY)
    {
        this.sylLcY = sylLcY;
    }

    public String getSylLcY()
    {
        return this.sylLcY == null ? null : this.sylLcY.getValue();
    }

    public void setKhcsY(Double khcsY)
    {
        if (null == this.khcsY)
        {
            this.khcsY = new EqualPack<Double>();
        }
        this.khcsY.setValue(khcsY);
    }

    public void setKhcsY(Pack<Double> khcsY)
    {
        this.khcsY = khcsY;
    }

    public Double getKhcsY()
    {
        return this.khcsY == null ? null : this.khcsY.getValue();
    }

    public void setDataBizDate(String dataBizDate)
    {
        if (null == this.dataBizDate)
        {
            this.dataBizDate = new EqualPack<String>();
        }
        this.dataBizDate.setValue(dataBizDate);
    }

    public void setDataBizDate(Pack<String> dataBizDate)
    {
        this.dataBizDate = dataBizDate;
    }

    public String getDataBizDate()
    {
        return this.dataBizDate == null ? null : this.dataBizDate.getValue();
    }

    public void setServiceRelation(String serviceRelation)
    {
        if (null == this.serviceRelation)
        {
            this.serviceRelation = new EqualPack<String>();
        }
        this.serviceRelation.setValue(serviceRelation);
    }

    public void setServiceRelation(Pack<String> serviceRelation)
    {
        this.serviceRelation = serviceRelation;
    }

    public String getServiceRelation()
    {
        return this.serviceRelation == null ? null : this.serviceRelation.getValue();
    }

    public void setAssessRelation(String assessRelation)
    {
        if (null == this.assessRelation)
        {
            this.assessRelation = new EqualPack<String>();
        }
        this.assessRelation.setValue(assessRelation);
    }

    public void setAssessRelation(Pack<String> assessRelation)
    {
        this.assessRelation = assessRelation;
    }

    public String getAssessRelation()
    {
        return this.assessRelation == null ? null : this.assessRelation.getValue();
    }

    public void setSaleRelation(String saleRelation)
    {
        if (null == this.saleRelation)
        {
            this.saleRelation = new EqualPack<String>();
        }
        this.saleRelation.setValue(saleRelation);
    }

    public void setSaleRelation(Pack<String> saleRelation)
    {
        this.saleRelation = saleRelation;
    }

    public String getSaleRelation()
    {
        return this.saleRelation == null ? null : this.saleRelation.getValue();
    }

    public void setNormDevRelation(String normDevRelation)
    {
        if (null == this.normDevRelation)
        {
            this.normDevRelation = new EqualPack<String>();
        }
        this.normDevRelation.setValue(normDevRelation);
    }

    public void setNormDevRelation(Pack<String> normDevRelation)
    {
        this.normDevRelation = normDevRelation;
    }

    public String getNormDevRelation()
    {
        return this.normDevRelation == null ? null : this.normDevRelation.getValue();
    }

    public void setCredDevRelation(String credDevRelation)
    {
        if (null == this.credDevRelation)
        {
            this.credDevRelation = new EqualPack<String>();
        }
        this.credDevRelation.setValue(credDevRelation);
    }

    public void setCredDevRelation(Pack<String> credDevRelation)
    {
        this.credDevRelation = credDevRelation;
    }

    public String getCredDevRelation()
    {
        return this.credDevRelation == null ? null : this.credDevRelation.getValue();
    }

    public void setOptDevRelation(String optDevRelation)
    {
        if (null == this.optDevRelation)
        {
            this.optDevRelation = new EqualPack<String>();
        }
        this.optDevRelation.setValue(optDevRelation);
    }

    public void setOptDevRelation(Pack<String> optDevRelation)
    {
        this.optDevRelation = optDevRelation;
    }

    public String getOptDevRelation()
    {
        return this.optDevRelation == null ? null : this.optDevRelation.getValue();
    }

    public void setInvAdvrSign(String invAdvrSign)
    {
        if (null == this.invAdvrSign)
        {
            this.invAdvrSign = new EqualPack<String>();
        }
        this.invAdvrSign.setValue(invAdvrSign);
    }

    public void setInvAdvrSign(Pack<String> invAdvrSign)
    {
        this.invAdvrSign = invAdvrSign;
    }

    public String getInvAdvrSign()
    {
        return this.invAdvrSign == null ? null : this.invAdvrSign.getValue();
    }

    public void setPdAdvrSign(String pdAdvrSign)
    {
        if (null == this.pdAdvrSign)
        {
            this.pdAdvrSign = new EqualPack<String>();
        }
        this.pdAdvrSign.setValue(pdAdvrSign);
    }

    public void setPdAdvrSign(Pack<String> pdAdvrSign)
    {
        this.pdAdvrSign = pdAdvrSign;
    }

    public String getPdAdvrSign()
    {
        return this.pdAdvrSign == null ? null : this.pdAdvrSign.getValue();
    }

    public void setServiceRelationNo(String serviceRelationNo)
    {
        if (null == this.serviceRelationNo)
        {
            this.serviceRelationNo = new EqualPack<String>();
        }
        this.serviceRelationNo.setValue(serviceRelationNo);
    }

    public void setServiceRelationNo(Pack<String> serviceRelationNo)
    {
        this.serviceRelationNo = serviceRelationNo;
    }

    public String getServiceRelationNo()
    {
        return this.serviceRelationNo == null ? null : this.serviceRelationNo.getValue();
    }

    public void setAgfinPdIncServIncm(Double agfinPdIncServIncm)
    {
        if (null == this.agfinPdIncServIncm)
        {
            this.agfinPdIncServIncm = new EqualPack<Double>();
        }
        this.agfinPdIncServIncm.setValue(agfinPdIncServIncm);
    }

    public void setAgfinPdIncServIncm(Pack<Double> agfinPdIncServIncm)
    {
        this.agfinPdIncServIncm = agfinPdIncServIncm;
    }

    public Double getAgfinPdIncServIncm()
    {
        return this.agfinPdIncServIncm == null ? null : this.agfinPdIncServIncm.getValue();
    }

    public void setNetCmsIncm(Double netCmsIncm)
    {
        if (null == this.netCmsIncm)
        {
            this.netCmsIncm = new EqualPack<Double>();
        }
        this.netCmsIncm.setValue(netCmsIncm);
    }

    public void setNetCmsIncm(Pack<Double> netCmsIncm)
    {
        this.netCmsIncm = netCmsIncm;
    }

    public Double getNetCmsIncm()
    {
        return this.netCmsIncm == null ? null : this.netCmsIncm.getValue();
    }

    public void setIntIncm(Double intIncm)
    {
        if (null == this.intIncm)
        {
            this.intIncm = new EqualPack<Double>();
        }
        this.intIncm.setValue(intIncm);
    }

    public void setIntIncm(Pack<Double> intIncm)
    {
        this.intIncm = intIncm;
    }

    public Double getIntIncm()
    {
        return this.intIncm == null ? null : this.intIncm.getValue();
    }

    public void setFundCode(String fundCode)
    {
        if (null == this.fundCode)
        {
            this.fundCode = new EqualPack<Clob>();
        }
        this.fundCode.setValue(fundCode);
    }

    public void setFundCode(Pack<String> fundCode)
    {
        this.fundCode = fundCode;
    }

    public String getFundCode()
    {
        return this.fundCode == null ? null : this.fundCode.getValue();
    }

    public void setStockCode(String stockCode)
    {
        if (null == this.stockCode)
        {
            this.stockCode = new EqualPack<Clob>();
        }
        this.stockCode.setValue(stockCode);
    }

    public void setStockCode(Pack<String> stockCode)
    {
        this.stockCode = stockCode;
    }

    public String getStockCode()
    {
        return this.stockCode == null ? null : this.stockCode.getValue();
    }

}
