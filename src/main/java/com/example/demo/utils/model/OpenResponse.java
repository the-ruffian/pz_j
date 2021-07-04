/*
 * @Description:OpenRespone
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-02 11:25
 * @LastEditTime: 2021-7-4 10:03:30
 * @LastEditors: the-ruffian
 */
package com.example.demo.utils.model;

import com.example.demo.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("JSON响应数据")
@Data
public class OpenResponse<T> extends BaseResponse implements Serializable {

    @ApiModelProperty("返回数据")
    private T result;

    public static <T> OpenResponse<T> ok(String message) {
        OpenResponse<T> openResponse = new OpenResponse<>();
        openResponse.setSuccess(true);
        openResponse.setCode(Status.SUCCESS.getCode());
        openResponse.setMessage(message);
        return openResponse;
    }
    public static <T> OpenResponse<T> ok(String message, T result){
        return ok("ok", result);
    }

    public static <T> OpenResponse<T> ok(Status status){
        OpenResponse<T> openResponse = new OpenResponse<>();
        openResponse.setSuccess(true);
        openResponse.setCode(status.getCode());
        openResponse.setMessage(status.getMessage());
        openResponse.setResult(openResponse.result);
        return openResponse;
    }
    public static <T> OpenResponse<T> fail(Status status){
        OpenResponse<T> openResponse = new OpenResponse<>();
        openResponse.setSuccess(false);
        openResponse.setCode(status.getCode());
        openResponse.setMessage(status.getMessage());
        openResponse.setResult(openResponse.result);
        return openResponse;
    }
    public static <T> OpenResponse<T> fail(String message) {
        OpenResponse<T> openResponse = new OpenResponse<>();
        openResponse.setSuccess(false);
        openResponse.setCode(Status.FAIL.getCode());
        openResponse.setMessage(message);
        return openResponse;
    }
    public static <T> OpenResponse<T> fail(String message, T result){
        return fail("fail",result);
    }
}
