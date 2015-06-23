package cn.grandcloud.email.repository;

import cn.grandcloud.email.model.SendNormalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 智康 on 2015/6/23 0023.
 */
@Repository
public interface SendNormalRepository extends JpaRepository<SendNormalEntity, Integer> {

    @Query("from SendNormalEntity sn order by sn.sendTime desc")
    List<SendNormalEntity> findAllSendTimeDesc();
}
