package com.kh.mango.point.controller;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.point.service.PointService;
import com.kh.mango.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class PointController {

    @Autowired
    private PointService pService;

    @GetMapping("/chargePoint")
    public String pointChargeView(){
        return "chargePoint";
    }

    @GetMapping("/refundPoint")
    public String pointRefundView(){
        return "refundPoint";
    }

    @GetMapping("/chargePointAction")
    public String pointChargeAction(@RequestParam("pointVal") int pointVal, Model model, @SessionAttribute("loginUser") User user){
            Point point = new Point(user.getUserNo(), pointVal);
            int result = pService.updateAddPoint(point);
            if(result > 0){
                model.addAttribute("com","window.close()");
                return "chargePoint";
            }else {
                model.addAttribute("fal",1);
                return "chargePoint";
            }
    }

    @GetMapping("/refundPointAction")
    public String pointRefund(@RequestParam("pointVal") int pointVal, Model model, @SessionAttribute("loginUser") User user){
        Point point = new Point(user.getUserNo(), pointVal);
        int result = pService.updateRefundPoint(point);
        if(result > 0){
            model.addAttribute("com","window.close()");
            return "refundPoint";
        }else {
            model.addAttribute("fal",1);
            return "refundPoint";
        }
    }


}
