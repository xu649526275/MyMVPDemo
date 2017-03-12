package com.android.mvp.bean;

import java.util.List;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/22  16:00
 */
public class WYDetial {


    /**
     * body :
     * users : []
     * spinfo : [{"ref":"<!--SPINFO#0-->","spcontent":"","sptype":"回顾"}]
     * ydbaike : []
     * replyCount : 2200
     * link : []
     * img : [{"ref":"<!--IMG#0-->","pixel":"260*178","alt":"","src":"http://cms-bucket.nosdn.127.net/9d326cd7ebb84ae99fddef17aaa2cac620161111105250.jpeg"},{"ref":"<!--IMG#1-->","pixel":"600*1454","alt":"","src":"http://cms-bucket.nosdn.127.net/96891bf3b0da43ea852241ce66c8360020161111111907.jpeg"}]
     * votes : []
     * shareLink : http://c.m.163.com/news/a/C5J935DG000187VE.html?spss=newsapp&spsw=1
     * digest :
     * topiclist_news : [{"hasCover":false,"subnum":"0","alias":"yaowentuisong","tname":"要闻推送","ename":"pushliebiao","tid":"T1350294190231","cid":"C1350294152567"},{"hasCover":false,"subnum":"3.2万","alias":"Top News","tname":"头条","ename":"androidnews","tid":"T1348647909107","cid":"C1348646712614"},{"hasCover":false,"subnum":"超过1000万","alias":"Top News","tname":"头条","ename":"iosnews","tid":"T1348647853363","cid":"C1348646712614"},{"hasCover":false,"subnum":"0","alias":"todayNews2","tname":"今日要闻","ename":"todayNews2","tid":"T1429173762551","cid":"C1350294152567"},{"hasCover":false,"subnum":"0","alias":"newsToday1","tname":"今日要闻","ename":"newsToday1","tid":"T1429173683626","cid":"C1350294152567"},{"hasCover":false,"subnum":"0","alias":"androidpush","tname":"push列表（android）","ename":"androidpush","tid":"T1371543208049","cid":"C1350294152567"},{"hasCover":false,"subnum":"3.5万","alias":"yaowenspecial","tname":"精选","ename":"yaowenspecial","tid":"T1467284926140","cid":"C1348647991705"}]
     * dkeys : 内蒙古自治区,政协原副主席,判处死刑
     * ec : 黄达_NN5311
     * topiclist : []
     * docid : C5J935DG000187VE
     * picnews : true
     * title : 内蒙古自治区政协原副主席赵黎平一审被判处死刑
     * tid :
     * template : normal
     * threadVote : 1
     * threadAgainst : 4
     * boboList : []
     * replyBoard : news2_bbs
     * source : 澎湃新闻网
     * hasNext : false
     * voicecomment : off
     * relative_sys : [{"id":"C5HHR1QP0001875N","title":"农妇太原派出所被踩头后死亡 值班主办警察获刑5年","source":"澎湃新闻网","imgsrc":"http://cms-bucket.nosdn.127.net/f1e9e81857374e218ea35946ee91124a20161110192516.jpeg","docID":"C5HHR1QP0001875N","from":"HZ","type":"doc","ptime":"2016-11-10 18:45:07","href":""},{"id":"C56EFLM3041600AS","title":"撞倒人后停下又碾过去 交通肇事变成故意杀人","source":"江南都市报","imgsrc":"http://cms-bucket.nosdn.127.net/d267074692714e39a113133bed2fa11e20161106111603.jpeg","docID":"C56EFLM3041600AS","from":"HZ","type":"doc","ptime":"2016-11-06 11:14:49","href":""},{"id":"C57GGI870001875P","title":"陕西一村支书捅死村主任后被疑叫亲侄子顶罪","source":"前街一号","imgsrc":"http://cms-bucket.nosdn.127.net/catchpic/d/d7/d77383892aded285979fb0b20da7f609.jpg","docID":"C57GGI870001875P","from":"HZ","type":"doc","ptime":"2016-11-06 21:09:30","href":""}]
     * ptime : 2016-11-11 10:48:00
     */

    private String body;
    private int replyCount;
    private String shareLink;
    private String digest;
    private String dkeys;
    private String ec;
    private String docid;
    private boolean picnews;
    private String title;
    private String tid;
    private String template;
    private int threadVote;
    private int threadAgainst;
    private String replyBoard;
    private String source;
    private boolean hasNext;
    private String voicecomment;
    private String ptime;
    private List<?> users;
    /**
     * ref : <!--SPINFO#0-->
     * spcontent :
     * sptype : 回顾
     */

    private List<SpinfoBean> spinfo;
    private List<?> ydbaike;
    private List<?> link;
    /**
     * ref : <!--IMG#0-->
     * pixel : 260*178
     * alt :
     * src : http://cms-bucket.nosdn.127.net/9d326cd7ebb84ae99fddef17aaa2cac620161111105250.jpeg
     */

    private List<ImgBean> img;
    private List<?> votes;
    /**
     * hasCover : false
     * subnum : 0
     * alias : yaowentuisong
     * tname : 要闻推送
     * ename : pushliebiao
     * tid : T1350294190231
     * cid : C1350294152567
     */

    private List<TopiclistNewsBean> topiclist_news;
    private List<?> topiclist;
    private List<?> boboList;
    /**
     * id : C5HHR1QP0001875N
     * title : 农妇太原派出所被踩头后死亡 值班主办警察获刑5年
     * source : 澎湃新闻网
     * imgsrc : http://cms-bucket.nosdn.127.net/f1e9e81857374e218ea35946ee91124a20161110192516.jpeg
     * docID : C5HHR1QP0001875N
     * from : HZ
     * type : doc
     * ptime : 2016-11-10 18:45:07
     * href :
     */

    private List<RelativeSysBean> relative_sys;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDkeys() {
        return dkeys;
    }

    public void setDkeys(String dkeys) {
        this.dkeys = dkeys;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public boolean isPicnews() {
        return picnews;
    }

    public void setPicnews(boolean picnews) {
        this.picnews = picnews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getThreadVote() {
        return threadVote;
    }

    public void setThreadVote(int threadVote) {
        this.threadVote = threadVote;
    }

    public int getThreadAgainst() {
        return threadAgainst;
    }

    public void setThreadAgainst(int threadAgainst) {
        this.threadAgainst = threadAgainst;
    }

    public String getReplyBoard() {
        return replyBoard;
    }

    public void setReplyBoard(String replyBoard) {
        this.replyBoard = replyBoard;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getVoicecomment() {
        return voicecomment;
    }

    public void setVoicecomment(String voicecomment) {
        this.voicecomment = voicecomment;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<?> getUsers() {
        return users;
    }

    public void setUsers(List<?> users) {
        this.users = users;
    }

    public List<SpinfoBean> getSpinfo() {
        return spinfo;
    }

    public void setSpinfo(List<SpinfoBean> spinfo) {
        this.spinfo = spinfo;
    }

    public List<?> getYdbaike() {
        return ydbaike;
    }

    public void setYdbaike(List<?> ydbaike) {
        this.ydbaike = ydbaike;
    }

    public List<?> getLink() {
        return link;
    }

    public void setLink(List<?> link) {
        this.link = link;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }

    public List<?> getVotes() {
        return votes;
    }

    public void setVotes(List<?> votes) {
        this.votes = votes;
    }

    public List<TopiclistNewsBean> getTopiclist_news() {
        return topiclist_news;
    }

    public void setTopiclist_news(List<TopiclistNewsBean> topiclist_news) {
        this.topiclist_news = topiclist_news;
    }

    public List<?> getTopiclist() {
        return topiclist;
    }

    public void setTopiclist(List<?> topiclist) {
        this.topiclist = topiclist;
    }

    public List<?> getBoboList() {
        return boboList;
    }

    public void setBoboList(List<?> boboList) {
        this.boboList = boboList;
    }

    public List<RelativeSysBean> getRelative_sys() {
        return relative_sys;
    }

    public void setRelative_sys(List<RelativeSysBean> relative_sys) {
        this.relative_sys = relative_sys;
    }

    public static class SpinfoBean {
        private String ref;
        private String spcontent;
        private String sptype;

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getSpcontent() {
            return spcontent;
        }

        public void setSpcontent(String spcontent) {
            this.spcontent = spcontent;
        }

        public String getSptype() {
            return sptype;
        }

        public void setSptype(String sptype) {
            this.sptype = sptype;
        }
    }

    public static class ImgBean {
        private String ref;
        private String pixel;
        private String alt;
        private String src;

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getPixel() {
            return pixel;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }

    public static class TopiclistNewsBean {
        private boolean hasCover;
        private String subnum;
        private String alias;
        private String tname;
        private String ename;
        private String tid;
        private String cid;

        public boolean isHasCover() {
            return hasCover;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public String getSubnum() {
            return subnum;
        }

        public void setSubnum(String subnum) {
            this.subnum = subnum;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }
    }

    public static class RelativeSysBean {
        private String id;
        private String title;
        private String source;
        private String imgsrc;
        private String docID;
        private String from;
        private String type;
        private String ptime;
        private String href;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getDocID() {
            return docID;
        }

        public void setDocID(String docID) {
            this.docID = docID;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
