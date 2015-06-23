package cn.grandcloud.email.repository;

import cn.grandcloud.email.model.EmailContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ÖÇ¿µ on 2015/6/23 0023.
 */
@Repository
public interface EmailContentRepository extends JpaRepository<EmailContentEntity, Integer> {

    @Query("from EmailContentEntity ec order by ec.pubDate desc ")
    List<EmailContentEntity> findAllPubDateDesc();

    @Modifying
    @Transactional
    @Query("update EmailContentEntity ec set ec.subject = :qSub, ec.content = :qContent where ec.eid = :qEid")
    void updateEmailById(@Param("qSub") String qSub, @Param("qContent") String qContent, @Param("qEid") int qEid);
}
