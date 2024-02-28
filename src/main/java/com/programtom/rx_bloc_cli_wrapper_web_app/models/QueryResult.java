package com.programtom.rx_bloc_cli_wrapper_web_app.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class QueryResult<T> {

    private List<T> list;
    private long count;
    private String message;

    public QueryResult() {
        list = new ArrayList<>();
        count = 0;
        message = "";
    }

    public QueryResult(List<T> list, long count) {
        super();
        this.list = list;
        this.count = count;
    }
}