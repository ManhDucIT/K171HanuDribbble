package com.androidtraining.k171hanudribbble.webservice.inputs;

import com.androidtraining.k171hanudribbble.webservice.DribbbleAPIs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShotsInput extends BasePagingInput {

    public enum ListType {
        none,
        animated,
        attachments,
        debuts,
        playoffs,
        rebounds,
        teams
    }

    public enum TimeFrameFype {
        none,
        week,
        month,
        year,
        ever
    }

    public enum SortType {
        none,
        comments,
        recent,
        views
    }

    @Override
    public String getResource() {
        return DribbbleAPIs.GET_SHOTS;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    public void setList(ListType list) {
        getQueryParamters().put("list", list.name());
    }

    public void setTimeframe(TimeFrameFype timeframe) {
        getQueryParamters().put("timeframe", timeframe.name());
    }

    public void setDate(String date) {
        getQueryParamters().put("date", date);
    }

    public void setSort(SortType sort) {
        getQueryParamters().put("sort", sort.name());
    }

}