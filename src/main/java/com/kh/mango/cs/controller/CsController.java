package com.kh.mango.cs.controller;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CsController {

    @Autowired
    private CsService cService;

    // 공지사항 등록 화면
    @RequestMapping(method = RequestMethod.GET, value = "/notice")
    public String noticeView() {
        return "notice";
    }
    // 공지사항 등록
    @RequestMapping(method = RequestMethod.GET, value = "/notice.do")
    public String noticeRegister(
            @ModelAttribute Cs cs
            , HttpServletRequest request
            , Model model) {
        int result = cService.insertNotice(cs);
        if(result > 0) {
            return "notice.do";
        }else {
            model.addAttribute("msg", "공지사항 등록이 완료되지 않았어요.");
            return "error";
        }
    }

}
