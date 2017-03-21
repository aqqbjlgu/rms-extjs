package com.rms.repository;

import com.rms.common.entity.PersonOrgPosEntity;
import com.rms.common.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonOrgPosRepository extends JpaRepository<PersonOrgPosEntity, String> {
    @Modifying
    @Query("delete from PersonOrgPosEntity where id in(:ids)")
    void delete(@Param("ids") List<String> ids);
    
    @Modifying
    @Query("delete from PersonOrgPosEntity where pId in(:ids)")
    void deleteByPid(@Param("ids") List<String> ids);

}

