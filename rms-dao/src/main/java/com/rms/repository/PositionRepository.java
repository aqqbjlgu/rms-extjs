package com.rms.repository;

import com.rms.common.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PositionRepository extends JpaRepository<PositionEntity, String> {
    @Modifying
    @Query("delete from PositionEntity where id in(:ids)")
    void delete(@Param("ids") List<String> ids);

}

