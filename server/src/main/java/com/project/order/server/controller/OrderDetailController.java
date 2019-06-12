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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
}
