/*
 * @Description:BaseResponse
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-02 09:48
 * @LastEditTime: 2021-7-4 14:45:11
 * @LastEditors: the-ruffian
 */
package com.example.demo.utils.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("响应对象数据")
public class BaseResponse {
    @ApiModelProperty("是否成功")
    protected boolean success;
    @ApiModelProperty("返回编码")
    protected int code;
    @ApiModelProperty("返回具体业务子编码")
    protected int subCode;
    @ApiModelProperty("返回信息")
    protected String message;


    public static BaseResponse fail(int code, String message){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setSuccess(false);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSubCode() {
        return subCode;
    }

    public void setSubCode(int subCode) {
        this.subCode = subCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
