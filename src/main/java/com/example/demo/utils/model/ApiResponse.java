/*
 * @Description:ApiRespone
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-02 11:25
 * @LastEditTime: 2021-07-02 11:25
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
public class ApiResponse<T> extends BaseResponse implements Serializable {

    @ApiModelProperty("返回数据")
    private T result;

    public static <T> ApiResponse<T> ok(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setCode(Status.SUCCESS.getCode());
        apiResponse.setMessage(message);
        return apiResponse;
    }
    public static <T> ApiResponse<T> ok(String message,T result){
        return ok("ok", result);
    }

    public static <T> ApiResponse<T> ok(Status status){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setCode(status.getCode());
        apiResponse.setMessage(status.getMessage());
        apiResponse.setResult(apiResponse.result);
        return apiResponse;
    }
}
