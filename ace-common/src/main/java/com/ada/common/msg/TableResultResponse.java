package com.ada.common.msg;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Ada
 * @ClassName :TableResultResponse
 * @date 2019/10/4 17:06
 * @Description:
 */
@Getter
@Setter
public class TableResultResponse<T> extends BaseResponse {
    TableData<T> data;

    public TableResultResponse(long total, List<T> rows) {
        this.data = new TableData<T>(total, rows);
    }

    public TableResultResponse() {
        this.data = new TableData<T>();
    }

    TableResultResponse<T> total(int total) {
        this.data.setTotal(total);
        return this;
    }

    TableResultResponse<T> total(List<T> rows) {
        this.data.setRows(rows);
        return this;
    }

}
