package com.rms.repository;

import com.rms.common.entity.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrganizationRepository extends JpaRepository<OrgEntity, String> {
    
    @Query("from OrgEntity where typeId in (:typeIds)")
    List<OrgEntity> getAllByTypeIds(@Param("typeIds") List<String> typeIds);
    
    @Query("from OrgEntity where parentId = :parentId")
    List<OrgEntity> getAllByParentId(@Param("parentId") String parentId);
    
    @Query("from OrgEntity where parentId in (:parentIds)")
    List<OrgEntity> getAllByParentId(@Param("parentIds") List<String> parentIds);
    
    @Query("from OrgEntity where id in (:ids)")
    List<OrgEntity> getAllByIds(@Param("ids") List<String> ids);
    
    @Modifying
    @Query("delete from OrgEntity where id in(:ids)")
    void delete(@Param("ids") List<String> ids);

}

