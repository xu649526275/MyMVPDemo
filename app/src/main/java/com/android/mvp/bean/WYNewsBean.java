package com.android.mvp.bean;

import java.util.List;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/22  16:15
 */
public class WYNewsBean {
    /**
     * postid : PHOT2348T000100A
     * hasCover : false
     * hasHead : 1
     * replyCount : 34495
     * hasImg : 1
     * digest :
     * hasIcon : false
     * docid : 9IG74V5H00963VRO_C1IJKKJGluowenwenupdateDoc
     * title : 河南一幼儿园校车发生车祸 与货车相撞
     * order : 1
     * priority : 340
     * lmodify : 2016-09-22 14:50:07
     * boardid : photoview_bbs
     * ads : [{"docid":"C1IH5P0A05169QC9","title":"谈恋爱不如养狗!这只狗比男友更实用","tag":"doc","imgsrc":"http://cms-bucket.nosdn.127.net/92fdb07b0494490bad81caa534cf145b20160922105437.jpeg","subtitle":"","url":"C1IH5P0A05169QC9"},{"title":"看客:泄洪地别样风景 大鱼被水冲断头","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/f23f7da7b0584e41b57266bb97242d5720160922110837.jpeg","subtitle":"","url":"3R710001|2199682"},{"docid":"C1IC62S305169QC9","title":"名媛林黛玉穿的那些中国高定,完胜LV们","tag":"doc","imgsrc":"http://cms-bucket.nosdn.127.net/c02df120eb92457a83b6cb890bee09b920160922101517.jpeg","subtitle":"","url":"C1IC62S305169QC9"},{"docid":"C18N19GU05169CRR","title":"25个小常识带你了解国际空间站","tag":"doc","imgsrc":"http://cms-bucket.nosdn.127.net/12ebd9e0f50e41859d1dd6672775608620160922131255.jpeg","subtitle":"","url":"C18N19GU05169CRR"},{"title":"咸阳学生家长质疑甲醛未散 拒搬新校区","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/e55a386d129745e7ba0d33161f465df220160922155244.jpeg","subtitle":"","url":"00AP0001|2199907"}]
     * photosetID : 00AP0001|2199837
     * template : normal1
     * votecount : 32014
     * skipID : 00AP0001|2199837
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/3847ef505365421788dbe8055dd450f920160922114113.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/71d29d565ecb4937ad30393d1c2b081120160922114114.jpeg"}]
     * source : 网易原创
     * ename : androidnews
     * imgsrc : http://cms-bucket.nosdn.127.net/617c7d8d91dd4fc49dba34e4335a2acc20160922114112.jpeg
     * tname : 头条
     * ptime : 2016-09-22 11:33:01
     */

    private String postid;
    private boolean hasCover;
    private int hasHead;
    private int replyCount;
    private int hasImg;
    private String digest;
    private boolean hasIcon;
    private String docid;
    private String title;
    private int order;
    private int priority;
    private String lmodify;
    private String boardid;
    private String photosetID;
    private String template;
    private int votecount;
    private String skipID;
    private String alias;
    private String skipType;
    private String cid;
    private int hasAD;
    private String source;
    private String ename;
    private String imgsrc;
    private String tname;
    private String ptime;
    /**
     * docid : C1IH5P0A05169QC9
     * title : 谈恋爱不如养狗!这只狗比男友更实用
     * tag : doc
     * imgsrc : http://cms-bucket.nosdn.127.net/92fdb07b0494490bad81caa534cf145b20160922105437.jpeg
     * subtitle :
     * url : C1IH5P0A05169QC9
     */

    private List<AdsBean> ads;
    /**
     * imgsrc : http://cms-bucket.nosdn.127.net/3847ef505365421788dbe8055dd450f920160922114113.jpeg
     */

    private List<ImgextraBean> imgextra;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public static class AdsBean {
        private String docid;
        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ImgextraBean {
        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
