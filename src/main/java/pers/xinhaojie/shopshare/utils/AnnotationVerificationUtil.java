package pers.xinhaojie.shopshare.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * splice the error messages of annotation verification warnings
 *
 * @author xin haojie
 * @create 2021-08-12-22:14
 */
public class AnnotationVerificationUtil {

    public static String getAnnotationErrorMsg(BindingResult result) {
        StringBuilder sb = new StringBuilder("");

        if (result != null && result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error:errors){
                sb.append(error.getDefaultMessage()).append("\n");
            }
        }
        return sb.toString();
    }
}
