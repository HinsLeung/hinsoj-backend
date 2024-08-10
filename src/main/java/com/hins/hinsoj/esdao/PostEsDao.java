package com.hins.hinsoj.esdao;

import com.hins.hinsoj.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 * @author HinsLeung
 * @from <a href="https://github.com/HinsLeung/hinsoj-backend"> HinsLeungOJ后端代码 </a> 
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);
}