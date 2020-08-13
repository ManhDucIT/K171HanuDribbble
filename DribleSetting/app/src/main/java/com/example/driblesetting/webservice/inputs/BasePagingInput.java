package com.example.driblesetting.webservice.inputs;

public abstract class BasePagingInput extends BaseInput {

    public void setPageSize(int pageSize) {
        getQueryParamters().put("per_page", String.valueOf(pageSize));
    }

    public void setPageIndex(int pageIndex) {
        getQueryParamters().put("page", String.valueOf(pageIndex));
    }

}