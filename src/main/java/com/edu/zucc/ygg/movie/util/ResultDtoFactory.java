package com.edu.zucc.ygg.movie.util;


import com.edu.zucc.ygg.movie.dto.ResultCode;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import org.springframework.validation.BindingResult;


/**
 * Class Name: ResultDtoFactory Description:
 *
 * @author SC
 *
 */
public final class ResultDtoFactory {

    private ResultDtoFactory() {
    }

    /**
     * Description: 返回ack结果
     */
    public static <T> ResultDto<T> toAck(String msg) {
        return toAck(msg, null);
    }

    /**
     * Description: 返回ack结果
     */
    public static <T> ResultDto<T> toAck(String msg, T data) {
        ResultDto<T> dto = new ResultDto<T>();
        dto.setCode(ResultCode.ACK);
        dto.setMessage(msg);
        dto.setData(data);
        return dto;
    }

    /**
     * Description: 返回REDIRECT结果
     */
    public static ResultDto<String> toRedirect(String url) {
        ResultDto<String> dto = new ResultDto<String>();
        dto.setCode(ResultCode.REDIRECT);
        dto.setData(url);
        return dto;
    }

    /**
     * Description: 在controller层直接返回错误消息，避免在controller中用该方法catch异常做处理
     */
    public static <T> ResultDto<T> toNack(String msg) {
        return toNack(msg, null);
    }


    /**
     * Description: 在controller层直接返回错误消息，避免在controller中用该方法catch异常做处理
     */
    public static <T> ResultDto<T> toNack(String msg, T data) {
        ResultDto<T> dto = new ResultDto<T>();
        dto.setCode(ResultCode.NACK);
        dto.setMessage(msg);
        dto.setData(data);
        return dto;
    }

    /**
     * Description: 验证错误
     */
    public static ResultDto<BindingResult> toValidationError(String msg, BindingResult br) {
        ResultDto<BindingResult> dto = new ResultDto<BindingResult>();
        dto.setCode(ResultCode.VALIDATION_ERROR);
        dto.setMessage(msg);
        dto.setData(br);
        return dto;
    }


}
