package com.ycy.pojo;

import java.util.List;

public class Page {
    public static final Integer PAGE_SIZE =10;
    static  int pageSize=PAGE_SIZE;//每页显示的记录数
    private int pageNo;//当前页码(前端给)
    private int pageTotal;//总页数
    private int pageTotalCount;//总记录数
    List<Shower> myShower;//文件信息

    public Page(){

    }
    public Page(int pageNo, int pageTotal, int pageTotalCount, int pageSize, List<Shower> myShower) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.pageSize = pageSize;
        this.myShower = myShower;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Shower> getMyShower() {
        return myShower;
    }

    public void setMyShower(List<Shower> myShower) {
        this.myShower = myShower;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", myShower=" + myShower +
                '}';
    }
}
