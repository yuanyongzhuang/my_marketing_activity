package com.marketing.activity.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户信息
 * @author yyz
 */
@Data
public class UserInfoDTO implements Serializable {

    /**
     *
     */
    private String originUrl;
    /**
     *
     */
    private String beiZhu;
    /**
     *
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     *
     */
    private String password;
    /**
     * BindTelNumber
     */
    private String passwordSalt;
    /**
     *
     */
    private String commonName;
    /**
     *
     */
    private Integer country;
    /**
     *
     */
    private Integer province;
    /**
     *
     */
    private Integer mobileProvince;
    /**
     *
     */

    private Integer city;
    /**
     *
     */
    private Integer mobileCity;
    /**
     *
     */
    private Integer area;
    /**
     *
     */
    private Integer cooperationType;
    /**
     *
     */
    private Integer cooperationID;
    /**
     *
     */
    private BigDecimal accountAmount;
    /**
     *
     */
    private BigDecimal jiFenAmount;
    /**
     *
     */
    private String MSN;
    /**
     *
     */
    private String QQ;
    /**
     *
     */
    private String postalCode;
    /**
     *
     */
    private String phone;
    /**
     *
     */
    private String mobile;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private String address;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createDate;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime lastUpdateDate;
    /**
     *
     */
    private Boolean LockedOut;
    /**
     *
     */
    private String ipAddress;
    /**
     *
     */
    private String QQAccessToken;
    /**
     *
     */
    private String sinaAccessToken;
    /**
     *
     */
    private String company;
    /**
     *
     */
    private String imageUrl;
    /**
     *
     */
    private String sex;
    /**
     *
     */
    private Integer likeClass;
    /**
     *
     */
    private String nickName;
    /**
     *
     */
    private Boolean AuthMobile;
    /**
     *
     */
    private Boolean AuthEmail;
    /**
     *
     */
    private Integer pageType;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;
    /**
     *
     */
    private Integer source;
    /**
     *
     */
    private String pictrue;
    /**
     *
     */
    private String jianJie;
    /**
     *
     */
    private String qianMing;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastQianDaoTime;
    /**
     *
     */
    private Integer qianDaoCount;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime lastJiFenBalanceTime;
    /**
     *
     */
    private String yeWuMobile;
    /**
     *
     */
    private String yeWuEmail;
    /**
     *
     */
    private String yeWuQQ;
    /**
     *
     */
    private String weiXinHao;
    /**
     *
     */
    private Boolean CrmHiden;
    /**
     *
     */
    private Boolean hiden;
    /**
     *
     */
    private Integer loginCount;
    /**
     *
     */
    private Integer userTableId;
    /**
     *
     */
    private String jinJiDianHua;
    /**
     *
     */
    private String shenFenZheng;
    /**
     *
     */
    private Integer xueLi;
    /**
     *
     */
    private String qiTaGanXingQuKeCheng;
    /**
     *
     */
    private String yaoQingMa;
    /**
     *
     */
    private Integer daQuId;
    /**
     *
     */
    private Integer xiaoQuId;
    /**
     *
     */
    private Integer gongZuoNianXian;
    /**
     *
     */
    private Integer baoKaoKeMu;
    /**
     *
     */
    private String zhuanYe;
    /**
     *
     */
    private Integer XueYuan;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime kaiKeShiJian;
    /**
     *
     */
    private Integer siteIndex;
    /**
     *
     */
    private Integer xinXiLiu;
    /**
     *
     */
    private String zhiWei;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime ruZhiTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime zhuanZhengTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime liZhiTime;
    /**
     *
     */
    private String zpFuZheRen;
    /**
     *
     */
    private String pxFuZheRen;
    /**
     *
     */
    private String jieShaoRen;
    /**
     *
     */
    private String mobileYuanGong;
    /**
     *
     */
    private String renGongChengBen;
    /**
     *
     */
    private Integer yuanGong;
    /**
     *
     */
    private Integer createUserTableId;
    /**
     * 学员ID
     */
    private Integer groupId;
    /**
     *
     */
    private Integer daoChuCheck;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime daoChuCheckTime;
    /**
     *
     */
    private Integer targetYuanXiao;
    /**
     *
     */
    private Integer age;
    /**
     *
     */
    private Integer PeiXun;
    /**
     *
     */
    private Integer appSource;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime birthday;
    /**
     *
     */
    private Integer quDao;
    /**
     *
     */
    private Integer tuiGuangFangShi;
    /**
     *
     */
    private Integer createUserGroupId;
    /**
     *
     */
    private Integer quDaoBaoTableId;
    /**
     *
     */
    private Integer lastLoginDay;
    /**
     *
     */
    private Integer intentionCollege;
    /**
     *
     */
    private Integer shenFen;
    /**
     *
     */
    private String rawURL;
    /**
     *
     */
    private Integer intentionId;

    public static interface IBuilder {
        /**
         * 获取用户ID
         * @return
         */
        Integer getUserId();

        /**
         * 通过用户信息构建
         * @param userInfoDTO
         */
        void build(UserInfoDTO userInfoDTO);
    }
}
