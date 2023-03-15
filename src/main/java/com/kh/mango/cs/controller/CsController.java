package com.kh.mango.cs.controller;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.Notice;
import com.kh.mango.cs.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CsController {

    @Autowired
    private CsService cService;

    // 공지사항 등록 회면
    @RequestMapping(value = "/notice_wrView", method = RequestMethod.GET)
    public String notice_wrView(){
        return "notice_wr";
    }
    // 공지사항 등록
    @RequestMapping(value = "/notice_wr", method = RequestMethod.POST)
    public String noticeRegister(
            @ModelAttribute Cs cs
            , HttpServletRequest request
            , Model model) {
        int result = cService.insertCs(cs);
        if(result > 0) {
            return "/notice";
        }else {
            model.addAttribute("error", "실패");
            return "/notice_wr";
        }
    }
    // 공지사항 수정
    @RequestMapping(method = RequestMethod.GET, value = "/nModifyView")
    public String noticeModifyView(
            @RequestParam("csNo") Integer csNo
            , Model model) {
        Cs cs = cService.selectOneByName(csNo);
        if(cs != null) {
            model.addAttribute("cs", cs);
            return "nModify";
        }else {
            model.addAttribute("error", "실패");
            return "notice";
        }
    }


    // 공지사항 목록 화면
    @RequestMapping(method = RequestMethod.GET, value = "/notice")
    public String noticeView(

            Model model
    ) {
        List<Notice> noticeList = cService.selectNoticeList();
        for(int i = 1; i < noticeList.size(); i++){
            noticeList.get(i-1).setRowNum(i);
        }
        model.addAttribute("noticeList",noticeList);
        return "notice";
    }

    // 공지사항 삭제
    @GetMapping(value = "remove")
    public String noticeRemove(@RequestParam("csNo") int csNo, Model model) {
        int result = cService.deleteNotice(csNo);
        if(result > 0) {
            return "notice";
        }else {
            model.addAttribute("msg", "실패!");
            return "nDetail";
        }
    }

    // 공지사항 상세
    @GetMapping(value = "/nDetail")
    public String noticeDetailView(
            @RequestParam("csNo") int csNo
            , Model model){
            Cs cs = cService.selectOneByName(csNo);
            model.addAttribute("cs", cs);
            return "cDetail";
    }

}
