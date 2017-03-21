package com.rms.repository;

import com.rms.common.entity.OrgTypeRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrgTypeRuleRepository extends JpaRepository<OrgTypeRuleEntity, String> {
    
    @Query("from OrgTypeRuleEntity where pid = :pid")
    List<OrgTypeRuleEntity> getAllByPid(@Param("pid") String pid);
    
    @Modifying
    @Query("delete from OrgTypeRuleEntity where id in(:ids)")
    void delete(@Param("ids") List<String> ids);
}

