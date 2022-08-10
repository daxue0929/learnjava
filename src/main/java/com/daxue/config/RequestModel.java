//package com.daxue.config;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//
//public class RequestModel {
//
//    public static final String UFINTERFACE = "ufinterface";
//    public static final String BILL = "bill";
//    public static final String BILLHEAD = "billhead";
//    public static final String PK_GROUP = "pk_group";
//    public static final String PK_ORG = "pk_org";
//    public static final String CODE = "code";
//    public static final String NAME = "name";
//    public static final String USEDNAME = "usedname";
//    public static final String BIRTHDATE = "birthdate";
//    public static final String SEX = "sex";
//    public static final String IDTYPE = "idtype";
//    public static final String ID = "id";
//    public static final String MNECODE = "mnecode";
//    public static final String JOINWORKDATE = "joinworkdate";
//    public static final String ADDR = "addr";
//    public static final String OFFICEPHONE = "officephone";
//    public static final String HOMEPHONE = "homephone";
//    public static final String MOBILE = "mobile";
//    public static final String EMAIL = "email";
//    public static final String PSNJOBS = "psnjobs";
//    public static final String ITEM = "item";
//
//    public static final String PSNCODE = "psncode";
//    public static final String PK_PSNCL = "pk_psncl";
//    public static final String PK_DEPT = "pk_dept";
//    public static final String ISMAINJOB = "ismainjob";
//    public static final String INDUTYDATE = "indutydate";
//
//
//    public static void main(String[] args) {
//        com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel
//            main = new com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel();
//        String requestWithXMLString = main.getRequestWithXMLString();
//
//        System.out.println(requestWithXMLString);
//    }
//
//    public String getRequestWithXMLString() {
//        initValue();
//        Document document = DocumentHelper.createDocument();
//        Element ufinterfaceElement = document.addElement(UFINTERFACE)
//            .addAttribute("account", "develop")
//            .addAttribute("billtype", "psndoc")
//            .addAttribute("filename", "")
//            .addAttribute("groupcode", "01")
//            .addAttribute("isexchange", "Y")
//            .addAttribute("replace", "Y")
//            .addAttribute("roottag","")
//            .addAttribute("sender", "yufu");
//        Element billElement = ufinterfaceElement.addElement(BILL).addAttribute("id", "498JUQ123");
//        Element headElement = billElement.addElement(BILLHEAD);
//
//        headElement.addElement(PK_GROUP).addText(pkGroup);
//        headElement.addElement(PK_ORG).addText(pkOrg);
//        headElement.addElement(CODE).addText(code);
//        headElement.addElement(NAME).addText(name);
//        headElement.addElement(USEDNAME).addText(usedName);
//        headElement.addElement(BIRTHDATE).addText(birthdate);
//        headElement.addElement(SEX).addText(sex);
//        headElement.addElement(IDTYPE).addText(idType);
//        headElement.addElement(ID).addText(id);
//        headElement.addElement(MNECODE).addText(mnecode);
//        headElement.addElement(JOINWORKDATE).addText(joinworkdate);
//        headElement.addElement(ADDR).addText(addr);
//        headElement.addElement(OFFICEPHONE).addText(officephone);
//        headElement.addElement(HOMEPHONE).addText(homephone);
//        headElement.addElement(MOBILE).addText(mobile);
//        headElement.addElement(EMAIL).addText(email);
//
//        Element psnjobsElement = headElement.addElement(PSNJOBS);
//        Element itemElement = psnjobsElement.addElement(ITEM);
//
//        itemElement.addElement(PK_GROUP).addText(pkGroup);
//        itemElement.addElement(PK_ORG).addText(pkOrg);
//        itemElement.addElement(PSNCODE).addText(psncode);
//        itemElement.addElement(PK_PSNCL).addText(pk_psncl);
//        itemElement.addElement(PK_DEPT).addText(pk_dept);
//        itemElement.addElement(ISMAINJOB).addText(ismainjob);
//        itemElement.addElement(INDUTYDATE).addText(indutydate);
//
//        return document.asXML();
//    }
//
//    private void initValue() {
//        this.pkGroup = "";
//        this.pkOrg = "";
//        this.code = "";
//        this.name = "";
//        this.usedName = "";
//        this.birthdate = "";
//        this.sex = "";
//        this.idType = "";
//        this.id = "";
//        this.mnecode = "";
//        this.joinworkdate = "";
//        this.addr = "";
//        this.officephone = "";
//        this.homephone = "";
//        this.email = "";
//        this.mobile = "";
//        this.psncode = "";
//        this.pk_psncl = "";
//        this.pk_dept = "";
//        this.ismainjob = "";
//        this.indutydate = "";
//    }
//
//    /**
//     * 所属集团,最大长度为20,类型为:String
//     */
//    private String pkGroup;
//
//    /**
//     * 所属业务单元,最大长度为20,类型为:String
//     */
//    private String pkOrg;
//
//    /**
//     * 人员编码,最大长度为40,类型为:String
//     */
//    private String code;
//
//    /**
//     * 姓名,最大长度为200,类型为:String
//     */
//    private String name;
//
//    /**
//     * 曾用名,最大长度为200,类型为:String
//     */
//    private String usedName;
//
//    /**
//     * 出生日期,最大长度为19,类型为:UFDate
//     */
//    private String birthdate;
//
//    /**
//     * 性别,最大长度为0,类型为:Integer
//     */
//    private String sex;
//
//    /**
//     * 证件类型,最大长度为0,类型为:Integer
//     */
//    private String idType;
//
//    /**
//     * 证件号,最大长度为25,类型为:String
//     */
//    private String id;
//
//    /**
//     * 助记码,最大长度为50,类型为:String
//     */
//    private String mnecode;
//
//    /**
//     * 参加工作日期,最大长度为19,类型为:UFDate
//     * 2020-12-14 12:27:26
//     */
//    private String joinworkdate;
//
//    /**
//     * 家庭地址,最大长度为20,类型为:String
//     */
//    private String addr;
//
//    /**
//     * 办公电话,最大长度为30,类型为:String
//     */
//    private String officephone;
//
//    /**
//     * 家庭电话,最大长度为30,类型为:String
//     */
//    private String homephone;
//
//    /**
//     * 手机,最大长度为30,类型为:String
//     */
//    private String mobile;
//
//    /**
//     * 电子邮件,最大长度为50,类型为:String-
//     */
//    private String email;
//
//    /**
//     * 员工编号,最大长度为40,类型为:String
//     */
//    private String psncode;
//
//    /**
//     * 人员类别,最大长度为20,类型为:String
//     */
//    private String pk_psncl;
//
//    /**
//     * 所在部门,最大长度为20,类型为:String
//     */
//    private String pk_dept;
//
//    /**
//     * 是否主职,最大长度为1,类型为:UFBoolean
//     * Y
//     */
//    private String ismainjob;
//
//    /**
//     * 到职日期,最大长度为19,类型为:UFDate
//     */
//    private String indutydate;
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setPkGroup(final String pkGroup) {
//        this.pkGroup = pkGroup;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setPkOrg(final String pkOrg) {
//        this.pkOrg = pkOrg;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setCode(final String code) {
//        this.code = code;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setName(final String name) {
//        this.name = name;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setUsedName(final String usedName) {
//        this.usedName = usedName;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setBirthdate(final String birthdate) {
//        this.birthdate = birthdate;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setSex(final String sex) {
//        this.sex = sex;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setIdType(final String idType) {
//        this.idType = idType;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setId(final String id) {
//        this.id = id;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setMnecode(final String mnecode) {
//        this.mnecode = mnecode;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setJoinworkdate(final String joinworkdate) {
//        this.joinworkdate = joinworkdate;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setAddr(final String addr) {
//        this.addr = addr;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setOfficephone(final String officephone) {
//        this.officephone = officephone;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setHomephone(final String homephone) {
//        this.homephone = homephone;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setMobile(final String mobile) {
//        this.mobile = mobile;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setEmail(final String email) {
//        this.email = email;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setPsncode(final String psncode) {
//        this.psncode = psncode;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setPk_psncl(final String pk_psncl) {
//        this.pk_psncl = pk_psncl;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setPk_dept(final String pk_dept) {
//        this.pk_dept = pk_dept;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setIsmainjob(final String ismainjob) {
//        this.ismainjob = ismainjob;
//        return this;
//    }
//
//    public com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel setIndutydate(final String indutydate) {
//        this.indutydate = indutydate;
//        return this;
//    }
//}
