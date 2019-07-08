package com.project.order.server.controller;

import com.project.core.utils.ResultVOUtil;
import com.project.core.vo.ResultVO;
import com.project.order.common.vo.OrderDetailVO;
import com.project.order.server.entity.OrderDetail;
import com.project.order.server.message.MessageSender;
import com.project.order.server.service.OrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/order/detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private MessageSender messageSender;

    @GetMapping(value = "/{detailId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResultVO queryOrderDetailById(@PathVariable("detailId") String detailId) {
//        messageSender.sendMessage("我哈哈哈哈");
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orderDetailService.queryOrderDetailById(detailId), orderDetailVO);
        return ResultVOUtil.success(orderDetailVO);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResultVO insertDetail(@Valid OrderDetail orderDetail, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMsg = new ArrayList<>();
                // 获取校验失败的所有字段的错误信息
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                for (FieldError fieldError : fieldErrors) {
                    errorMsg.add(fieldError.getDefaultMessage());
                }
                return ResultVOUtil.error(errorMsg);
            }
            String primaryId = orderDetailService.insertOrder(orderDetail);
            return ResultVOUtil.success(primaryId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtil.error("新增订单详情失败");
        }
    }

    /**
     * 只有 ROLE_USER 角色的用户才能访问
     * @return 问候信息
     */
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloUser(){
        return "hello User";
    }

    /**
     * 只有 ROLE_ADMIN 角色的用户才能访问
     * @return 问候信息
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String helloAdmin(){
        return "hello Admin";
    }

    /**
     * 只有 ROLE_GUEST 角色的用户才能访问
     * @return 问候信息
     */
    @GetMapping("/guest")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public String helloGuest(){
        return "hello Guest";
    }

}
