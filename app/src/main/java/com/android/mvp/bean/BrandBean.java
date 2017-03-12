package com.android.mvp.bean;

import java.io.Serializable;

/**
 * 首页分类
 *
 * @autor 徐文龙
 * @time 2016/7/15  18:08
 */
public class BrandBean implements Serializable{


    /**
     * id : 7
     * brandcode : CW
     * brand : 磁味君子养生
     * explain :
     * IconUrl : http://shianyun-oss.oss-cn-beijing.aliyuncs.com/uploads/ProductBrandIcon/haoyou.png
     * Alias : 好油
     * ProductCount : 0
     * BrandIconUrl : 商标图片
     * CoverIconUrl : 封面大图
     * VideoUrl : 视频图片
     * LongName : 食安养生好油
     */

    private int id;
    private String brandcode;
    private String brand;
    private String explain;
    private String IconUrl;
    private String Alias;
    private int ProductCount;
    private String BrandIconUrl;
    private String CoverIconUrl;
    private String VideoUrl;
    private String LongName;

    public BrandBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String IconUrl) {
        this.IconUrl = IconUrl;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public int getProductCount() {
        return ProductCount;
    }

    public void setProductCount(int ProductCount) {
        this.ProductCount = ProductCount;
    }

    public String getBrandIconUrl() {
        return BrandIconUrl;
    }

    public void setBrandIconUrl(String BrandIconUrl) {
        this.BrandIconUrl = BrandIconUrl;
    }

    public String getCoverIconUrl() {
        return CoverIconUrl;
    }

    public void setCoverIconUrl(String CoverIconUrl) {
        this.CoverIconUrl = CoverIconUrl;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String VideoUrl) {
        this.VideoUrl = VideoUrl;
    }

    public String getLongName() {
        return LongName;
    }

    public void setLongName(String LongName) {
        this.LongName = LongName;
    }
}
