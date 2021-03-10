package com.ycy.pojo;

import java.util.List;

public class Page {
    public static final Integer PAGE_SIZE =4;
    static  int pageSize=PAGE_SIZE;//每页显示的记录数
    private int pageNo;//当前页码(前端给)
    private int pageTotal;//总页数
    private int pageTotalCount;//总记录数
    List<Shower> myShower;//文件信息
    private  int ParentId;//上级父文件夹id
    private int MyId;//当前文件夹id

    public Page(){

    }

    public Page(int pageNo, int pageTotal, int pageTotalCount, List<Shower> myShower, int parentId, int myId) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.myShower = myShower;
        ParentId = parentId;
        MyId = myId;
    }

    public static int getPageSize() {
        return pageSize;
    }

    public static void setPageSize(int pageSize) {
        Page.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(int pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<Shower> getMyShower() {
        return myShower;
    }

    public void setMyShower(List<Shower> myShower) {
        this.myShower = myShower;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public int getMyId() {
        return MyId;
    }

    public void setMyId(int myId) {
        MyId = myId;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", myShower=" + myShower +
                ", ParentId=" + ParentId +
                ", MyId=" + MyId +
                '}';
    }
}
