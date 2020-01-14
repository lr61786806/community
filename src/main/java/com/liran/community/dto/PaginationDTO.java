package com.liran.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PaginationDTO{
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    public void setPagination(Integer totalCount,Integer page,Integer size) {
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }


        pages.add(page);
        for(int i = 1; i <= 3 ; i++){
            if(page-i>0){
                pages.add(page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //pages排序
        Collections.sort(pages);
        //是否展示上一页
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //是否展示下一页
        if(page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }
        //是否展示首页
        if(page>4){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        //是否展示尾页
        if(page<(totalPage-3)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }


    }
}
