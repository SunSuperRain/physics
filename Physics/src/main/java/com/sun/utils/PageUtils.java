package com.sun.utils;

        import lombok.val;

        import java.util.List;

/**
 * @author 超雨
 * @create 2020--06--02--17:19
 *
 * 封装分页对象
 */
public class PageUtils<T> {

    private Integer pageNo;
    //设置每页显示的数据
    private int pageSize = 12;
    private int totalCount;
    private List<T> list;

    //利用构造方法  初始化值 初始化首页面
    public PageUtils(Integer pageNo) {
        if(pageNo == null || "".equals(pageNo)){
            this.pageNo = 1;
        }else {
            this.pageNo = pageNo;
        }
    }

    public int getTotalPage(){
        int totalPage = (totalCount % pageSize) == 0 ? (totalCount / pageSize ) : (totalCount / pageSize + 1);
        if(totalPage == 0){
            totalPage = 1;
        }
        return totalPage;
    }

    //判断上一页  下一页的情况
    public int getPrePage(){
        int prePage = 0;
        if(pageNo <= 1){
            prePage = 1;
        }else {
            prePage = pageNo - 1;
        }
        return prePage;
    }

    public int getNextPage(){
        int nextPage = 0;
        if(pageNo >= this.getTotalPage()){
            nextPage = this.getTotalPage();
        }else {
            nextPage = pageNo + 1;
        }
        return nextPage;
    }

    //判断是否可用点击
    public boolean getIsFirstPage(){
        boolean isFirstPage = false;
        if(pageNo > 1){
            isFirstPage = false;
        }else {
            isFirstPage = true;
        }
        return isFirstPage;
    }

    public boolean getIsLastPage(){
        boolean isLastPage = false;
        if(pageNo < this.getTotalPage()){
            isLastPage = false;
        }else {
            isLastPage = true;
        }
        return isLastPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
