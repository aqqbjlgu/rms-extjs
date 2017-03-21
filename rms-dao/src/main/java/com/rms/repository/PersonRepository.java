package com.rms.repository;

import com.rms.common.dto.PersonDto;
import com.rms.common.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonRepository extends JpaRepository<PersonEntity, String> {
    @Query("from PersonEntity where id in (select new com.rms.common.dto.PersonOrgPosDto(pId) from PersonOrgPosEntity where posId in (:positionIds))")
    List<PersonEntity> getAllByPositoinId(@Param("positionIds") List<String> positionIds);
    
    @Query("select new com.rms.common.dto.PersonDto(p.id, pop.id, o.id, o.name, pos.id, pos.name, p.name, p.password, p.sex, p.idCard, p.phone, p.nickName, p.image, p.status, p.email) from PersonEntity p left join PersonOrgPosEntity pop on p.id = pop.pId left join OrgEntity o on o.id = pop.orgId left join PositionEntity pos on pos.id = pop.posId")
    Page<PersonDto> getAll(Pageable pageRequest);
    
    @Modifying
    @Query("delete from PersonEntity where id in(:ids)")
    void delete(@Param("ids") List<String> ids);
    
    @Query("from PersonEntity where email = :email")
    PersonEntity getByEmail(@Param("email") String email);
    
    @Query("from PersonEntity where idCard = :idCard")
    PersonEntity getByIdCard(@Param("idCard") String idCard);
    
    @Query("from PersonEntity where phone = :phone")
    PersonEntity getByPhone(@Param("phone") String phone);
    
    @Query("from PersonEntity where nickName = :nickName")
    PersonEntity getByNickName(@Param("nickName") String nickName);
}

