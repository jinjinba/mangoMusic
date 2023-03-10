package com.kh.mango.point.controller;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.service.PointService;
import com.kh.mango.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/chargePointAction")
    public String pointChargeAction(@RequestParam("pointVal") int pointVal, Model model, @SessionAttribute("loginUser") User user){
            Map<String, Integer> map = new HashMap<String,Integer>();
            Point point = new Point(user.getUserNo(), pointVal);
            int result = pService.updateAddPoint(point);
            Point pointValue = pService.selectPoint(user.getUserNo());
            if(result > 0){
                map.put("pointVal",pointValue.getPointVal());
                return null;
            }else {
                model.addAttribute("fal",1);
                return null;
            }
    }

    @PostMapping("/ajaxPoint")
    public void ajaxPoint(@RequestParam("userNo")int userNo, @RequestParam("pointVal") int pointVal){
        System.out.println(userNo);
        System.out.println(pointVal);
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
