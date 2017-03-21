package com.rms.controller;

import com.rms.common.entity.PositionEntity;
import com.rms.common.util.ErrorType;
import com.rms.common.util.ExceptionUtil;
import com.rms.common.util.Result;
import com.rms.facade.PositionService;
import com.rms.vo.PositionVo;
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
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    private static final Logger log = LoggerFactory.getLogger(PositionController.class);
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public @ResponseBody Result getAll(){
        List<PositionEntity> result;
        try {
            result = positionService.getAll();
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.ok(result);
    }
    
    @RequestMapping(value = "deleteAll",method = RequestMethod.DELETE)
    public @ResponseBody Result deleteAll(String[] ids){
        try {
            List idsList = CollectionUtils.arrayToList(ids);
            positionService.delete(idsList);
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
    public @ResponseBody Result add(PositionVo positionVo, BindingResult result, HttpSession session){
        PositionEntity positionEntity = new PositionEntity();
        try {
            if (result.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : result.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false, ErrorType.NormException.toString());
            }
            if(StringUtils.isEmpty(positionVo.getId())){
                positionVo.setInsertDate(new Date());
                positionVo.setInsertUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
            }else {
                positionEntity = positionService.getById(positionVo.getId());
                positionVo.setInsertDate(positionEntity.getInsertDate());
                positionVo.setInsertUserId(positionEntity.getInsertUserId());
            }
            positionVo.setUpDateDate(new Date());
            positionVo.setUpDateUserId(session.getAttribute("userId")==null?"guest":session.getAttribute("userId").toString());
            BeanUtils.copyProperties(positionVo,positionEntity);
            positionEntity = positionService.save(positionEntity);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.RuntimeException.toString());
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false, ErrorType.NormException.toString());
        }
        return Result.ok(positionEntity);
    }
}
