package com.github.udoo.ultron.config;

import com.alibaba.fastjson.JSON;
import com.github.udoo.ultron.common.Result;
import com.github.udoo.ultron.common.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author dong.yang
 * @data 2019/7/16 10:07
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        if (WebUtil.isAjax(request)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            try {
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(handleExceptionMessage(exception)));
                out.flush();
                out.close();
            } catch (Exception e) {
                log.error("", e);
            }
            return null;
        }
        //异常处理
        ModelAndView modelAndView = new ModelAndView("500");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        modelAndView.addObject("exception", sw.toString());
        return modelAndView;
    }

    /**
     * 处理所有接口数据验证异常@RequestBody才会抛出该异常，如果是表单提交，抛出BindException
     * 1、需要验证的方法，需要加@Valid,@Validated
     * 2、如果参数带有BindingResult就不会抛出异常,反之抛出MethodArgumentNotValidException
     * 3、使用@Validated时,只会对特定group有效
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return handleExceptionMessage(e);
    }


    /**
     * 处理各种异常展示
     *
     * @param exception
     * @return
     */
    private Result handleExceptionMessage(Exception exception) {
        StringBuilder builder = new StringBuilder();
        if (exception instanceof DuplicateKeyException) {
            builder.append("主键冲突，请检查重试");
        } else if (exception instanceof BindException) {
            BindingResult bindingResult = ((BindException) exception).getBindingResult();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrorList) {
                builder.append(fieldError.getDefaultMessage()).append(" ");
            }
        } else if (exception instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrorList = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors();
            for (FieldError fieldError : fieldErrorList) {
                builder.append(fieldError.getDefaultMessage()).append(" ");
            }
        } else {
            builder.append(exception.getMessage());
        }
        return Result.fail(builder.toString());
    }

}
