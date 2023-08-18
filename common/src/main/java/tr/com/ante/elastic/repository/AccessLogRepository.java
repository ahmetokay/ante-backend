package tr.com.ante.elastic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tr.com.ante.elastic.model.AccessLogDocument;

@Repository
public interface AccessLogRepository extends ElasticsearchRepository<AccessLogDocument, String> {

    Page<AccessLogDocument> findByUserId(Long userId, Pageable pageable);

    Page<AccessLogDocument> findAll(Pageable pageable);

}