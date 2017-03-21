package com.rms.controller;

import com.rms.common.dto.OrganizationDto;
import com.rms.common.entity.OrgEntity;
import com.rms.common.util.ErrorType;
import com.rms.common.util.ExceptionUtil;
import com.rms.common.util.Result;
import com.rms.facade.OrganizationService;
import com.rms.vo.OrgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by 国平 on 2016/10/23.
 */
@Controller
@RequestMapping("/org")
public class OrgController {
    @Autowired
    private OrganizationService organizationService;
    private static final Logger log = LoggerFactory.getLogger(OrgController.class);
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public @ResponseBody Result getAll(String node){
        List<OrgEntity> result;
        try {
            result = organizationService.getAllByParentId(node);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.ok(result);
    }
    
    @RequestMapping(value = "/getAllByRule",method = RequestMethod.GET)
    public @ResponseBody Result getAllByRule(String orgId){
        List<OrgEntity> result;
        try {
            result = organizationService.getAllByRule(orgId);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.ok(result);
    }
    
    @RequestMapping(value = "/getAllBesideSelf",method = RequestMethod.GET)
    public @ResponseBody Result getAllBesideSelf(String node){
        List<OrgEntity> result;
        try {
            result = organizationService.getAllByParentId(node);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.ok(result);
    }
    
    @RequestMapping(value = "/getAllLikeTree",method = RequestMethod.GET)
    public @ResponseBody List<OrganizationDto> getAllLikeTree(){
        List<OrganizationDto> result = organizationService.getAllByParentId() ;
        return result;
    }
    
    @RequestMapping(value = "deleteAll",method = RequestMethod.DELETE)
    public @ResponseBody Result deleteAll(String[] ids){
        try {
            List idsList = CollectionUtils.arrayToList(ids);
            organizationService.delete(idsList);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.build(200,"删除成功",true);
    }
    
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public @ResponseBody Result add(OrgVo orgVo, BindingResult result, HttpSession session){
        OrgEntity orgEntity = new OrgEntity();
        try {
            if (result.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : result.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false, ErrorType.NormException.toString());
            }
            if(StringUtils.isEmpty(orgVo.getId())){
                orgVo.setInsertDate(new Date());
                orgVo.setInsertUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
                orgVo.setLeaf(true);
            }else {
                orgEntity = organizationService.getById(orgVo.getId());
                orgVo.setInsertDate(orgEntity.getInsertDate());
                orgVo.setInsertUserId(orgEntity.getInsertUserId());
            }
            orgVo.setUpDateDate(new Date());
            orgVo.setUpDateUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
            if(StringUtils.isEmpty(orgVo.getParentId()) || "root".equals(orgVo.getParentId())){
                orgVo.setParentId("0");
            }
            BeanUtils.copyProperties(orgVo,orgEntity);
            orgEntity = organizationService.save(orgEntity);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.ok(orgEntity);
    }
}
