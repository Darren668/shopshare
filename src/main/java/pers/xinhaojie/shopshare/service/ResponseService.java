package pers.xinhaojie.shopshare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xin haojie
 * @create 2021-08-12-22:22
 */
public interface ResponseService {

    /**define the status and message of success response*/
    public void response(HttpServletResponse response, Object message);
}
