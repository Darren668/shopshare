package pers.xinhaojie.shopshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author xin haojie
 * @create 2021-08-25-16:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer parentId;
    private Byte type;
    private Integer commenterId;
    private Integer likeCount;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;

}
