package pers.xinhaojie.shopshare.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.exception.CustomizeException;
import pers.xinhaojie.shopshare.response.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * return view and page, the error key is errorMsg
 * return JSON , the error key is msg(defined in CustomizeException)
 * @author xin haojie
 * @create 2021-08-24-21:20
 */
@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {
        //利用content-type来区分请求类型返回不同数据类型
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //使用原始方法直接吧数据写回去从而传输JSON数据
            PrintWriter writer;
            ResponseData responseData;
            if(ex instanceof  CustomizeException){
                responseData = ResponseData.fail((CustomizeException) ex);
            }else{
                responseData = ResponseData.fail(StatusCode.ServerNotWork);
            }
            try {
                writer = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                writer.write(JSON.toJSONString(responseData));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            //如果容器中任何自定义异常就返回这里捕获,返回错误页面
            if(ex instanceof CustomizeException){
                model.addAttribute("errorMsg", ((CustomizeException) ex).getMsg());
            }else{
                model.addAttribute("errorMsg", StatusCode.ServerNotWork.getMsg());
            }
            //ModelAndView
            return new ModelAndView("error");
        }
    }
}