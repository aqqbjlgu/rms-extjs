package com.rms.controller;

import com.rms.common.entity.OrgTypeRuleEntity;
import com.rms.common.util.ErrorType;
import com.rms.common.util.ExceptionUtil;
import com.rms.common.util.Result;
import com.rms.facade.OrgTypeRuleService;
import com.rms.vo.OrgTypeRuleVo;
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
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by 国平 on 2016/10/23.
 */
@Controller
@RequestMapping("/orgTypeRule")
public class OrgTypeRuleContorller {

    @Autowired
    private OrgTypeRuleService orgTypeRuleService;
    private static final Logger log = LoggerFactory.getLogger(OrgTypeRuleContorller.class);

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody Result add(@Valid OrgTypeRuleVo orgTypeRuleVo, BindingResult result, HttpSession session){
        OrgTypeRuleEntity orgTypeRuleEntity = new OrgTypeRuleEntity();
        try {
            if (result.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : result.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false, ErrorType.NormException.toString());
            }
            if(StringUtils.isEmpty(orgTypeRuleVo.getRid())){
                orgTypeRuleVo.setInsertDate(new Date());
                orgTypeRuleVo.setInsertUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
            }else{
                orgTypeRuleEntity = orgTypeRuleService.getById(orgTypeRuleVo.getRid());
                orgTypeRuleVo.setInsertDate(orgTypeRuleEntity.getInsertDate());
                orgTypeRuleVo.setInsertUserId(orgTypeRuleEntity.getInsertUserId());
            }
            orgTypeRuleVo.setUpDateDate(new Date());
            orgTypeRuleVo.setId(orgTypeRuleVo.getRid());
            orgTypeRuleVo.setUpDateUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
            BeanUtils.copyProperties(orgTypeRuleVo,orgTypeRuleEntity);
            orgTypeRuleEntity = orgTypeRuleService.save(orgTypeRuleEntity);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
        }
        return Result.ok(orgTypeRuleEntity);
    }

    @RequestMapping(value = "deleteAll",method = RequestMethod.DELETE)
    public @ResponseBody Result deleteAll(String[] ids){
        try {
            List idsList = CollectionUtils.arrayToList(ids);
            orgTypeRuleService.delete(idsList);
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
