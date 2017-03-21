package com.rms.controller;

import com.rms.common.dto.PersonDto;
import com.rms.common.dto.PersonOrgPosDto;
import com.rms.common.entity.PersonEntity;
import com.rms.common.util.ErrorType;
import com.rms.common.util.ExceptionUtil;
import com.rms.common.util.Result;
import com.rms.facade.PersonService;
import com.rms.vo.PersonVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 国平 on 2016/10/23.
 */
@Controller
@RequestMapping("/person")
public class PersonContorller {

    @Autowired
    private PersonService personService;
    
    private static final Logger log = LoggerFactory.getLogger(PersonContorller.class);

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public @ResponseBody Result getAll(int page, int limit){
        log.info("page================="+page);
        Page<PersonDto> pageReturn = null;
        try {
            Pageable pageRequest = new PageRequest(page-1, limit);
            pageReturn = personService.getAllWithOidAndPid(pageRequest);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
        }
        return Result.ok(pageReturn);
    }
    
    @RequestMapping(value = "/getByEmail",method = RequestMethod.GET)
    public @ResponseBody Result getByEmail(String email){
        PersonEntity personEntity = null;
        try {
            personEntity = personService.getByEmail(email);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
        }
        return Result.ok(personEntity);
    }
    
    @RequestMapping(value = "/getByIdCard/{idCard}",method = RequestMethod.GET)
    public @ResponseBody Result getByIdCard(@PathVariable String idCard){
        PersonEntity personEntity = null;
        try {
            personEntity = personService.getByIdCard(idCard);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
        }
        return Result.ok(personEntity);
    }
    
    @RequestMapping(value = "/getByPhone/{phone}",method = RequestMethod.GET)
    public @ResponseBody Result getByPhone(@PathVariable String phone){
        PersonEntity personEntity = null;
        try {
            personEntity = personService.getByPhone(phone);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
        }
        return Result.ok(personEntity);
    }
    
    @RequestMapping(value = "/getByNickName/{nickName}",method = RequestMethod.GET)
    public @ResponseBody Result getByNickName(@PathVariable String nickName){
        PersonEntity personEntity = null;
        try {
            personEntity = personService.getByNickName(nickName);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
        }
        return Result.ok(personEntity);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody Result add(@Valid PersonVo personVo, BindingResult result, HttpSession session){
        PersonDto personDto = new PersonDto();
        try {
            if (result.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : result.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false, ErrorType.NormException.toString());
            }
            PersonEntity personEntity = personService.getById(personVo.getId());
            if(StringUtils.isEmpty(personVo.getId())){
                personVo.setInsertDate(new Date());
                personVo.setInsertUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
            }else{
                personVo.setInsertDate(personEntity.getInsertDate());
                personVo.setInsertUserId(personEntity.getInsertUserId());
            }
            personVo.setUpDateDate(new Date());
            personVo.setUpDateUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
            BeanUtils.copyProperties(personVo,personDto);
            List<PersonOrgPosDto> personOrgPosDtos = personVo.getPersonOrgPosDtos();
            Iterator<PersonOrgPosDto> iter = personOrgPosDtos.iterator();
            while(iter.hasNext()){
                PersonOrgPosDto personOrgPosDto = iter.next();
                if(StringUtils.isEmpty(personOrgPosDto.getOrgId())){
                    iter.remove();
                }
            }
            personDto.setPersonOrgPosDtos(personOrgPosDtos);
            personService.save(personDto);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
        }
        return Result.ok(personDto);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable String id){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(id);
        personService.delete(personEntity);
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Result update(PersonEntity personEntity){
        return Result.ok(personService.save(personEntity));
    }
    
    @RequestMapping(value = "deleteAll",method = RequestMethod.DELETE)
    public @ResponseBody Result deleteAll(String[] ids){
        try {
            List idsList = CollectionUtils.arrayToList(ids);
            personService.delete(idsList);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.build(200,"删除成功",true);
    }
}
