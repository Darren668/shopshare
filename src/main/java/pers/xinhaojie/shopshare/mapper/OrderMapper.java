package pers.xinhaojie.shopshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xinhaojie.shopshare.entity.SharedOrder;

/**
 * @author xin haojie
 * @create 2021-08-22-21:41
 */
@Mapper
public interface OrderMapper extends BaseMapper<SharedOrder> {
}
