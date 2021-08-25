package pers.xinhaojie.shopshare.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xinhaojie.shopshare.entity.Comment;
import pers.xinhaojie.shopshare.mapper.CommentMapper;
import pers.xinhaojie.shopshare.service.CommentService;

/**
 * @author xin haojie
 * @create 2021-08-25-17:14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
