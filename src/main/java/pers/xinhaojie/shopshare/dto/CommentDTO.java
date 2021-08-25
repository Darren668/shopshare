package pers.xinhaojie.shopshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xin haojie
 * @create 2021-08-25-17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer parentId;
    private Byte type;
    private  Integer commenterId;
    private String content;
}
