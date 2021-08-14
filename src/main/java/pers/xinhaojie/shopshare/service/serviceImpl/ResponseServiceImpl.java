package pers.xinhaojie.shopshare.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import pers.xinhaojie.shopshare.service.ResponseService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xin haojie
 * @create 2021-08-12-22:25
 */
@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void response(HttpServletResponse response, Object message) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(message));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
