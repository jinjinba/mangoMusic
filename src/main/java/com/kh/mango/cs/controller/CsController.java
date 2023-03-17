package com.kh.mango.cs.controller;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;
import com.kh.mango.cs.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CsController {

    @Autowired
    private CsService cService;

    // 공지사항 등록 회면
    @RequestMapping(value = "/noticeWrite", method = RequestMethod.GET)
    public String notice_wrView(){
        return "/noticeWrite";
    }
    // 공지사항 등록
    @RequestMapping(value = "/noticeWrite", method = RequestMethod.POST)
    public String noticeRegister(
            @ModelAttribute Cs cs
            , HttpServletRequest request
            , Model model) {
        int result = cService.insertCs(cs);
        if(result > 0) {
            return "redirect:/notice";
        }else {
            return "/noticeWrite";
        }
    }
    // Q&A 등록 화면
    @RequestMapping(value = "/qnaWrite", method = RequestMethod.GET)
    public String qnaWriteView() { return  "/qnaWrite"; }
    // Q&A 등록
    @RequestMapping(value = "/qnaWrite", method = RequestMethod.POST)
    public String qnaRegister(
            @ModelAttribute Cs cs
            , HttpServletRequest request
            , Model model) {
        int result = cService.insertQna(cs);
        if(result > 0) {
            return "redirect:/qna";
        }else {
            return "/qnaWrite";
        }
    }
    // 공지사항 수정 화면
    @RequestMapping(method = RequestMethod.GET, value = "/noticeModify")
    public String noticeModifyView(
            @RequestParam("csNo") Integer csNo
            , Model model) {
        Cs cs = cService.selectOneByNo(csNo);
        if(cs != null) {
            model.addAttribute("cs", cs);
            return "/noticeModify";
        }else {
            model.addAttribute("error", "실패");
            return "/notice";
        }
    }
    // Q&A 수정 화면
    @GetMapping(value = "/qnaModify")
    public String qnaModifyView(
            @RequestParam("csNo") Integer csNo
            , Model model) {
        Cs cs = cService.selectQnaOneByNo(csNo);
        if(cs != null) {
            model.addAttribute("cs", cs);
            return "/qnaModify";
        }else {
            model.addAttribute("error", "실패");
            return "/qna";
        }
    }
    // 공지사항 수정
    @PostMapping(value = "/noticeModify")
    public String noticeModify(
            @ModelAttribute Cs cs
            , Model model
            , HttpServletRequest request){
        int result = cService.updateNotice(cs);
        if(result > 0) {
            return "redirect:/noticeDetail?csNo="+cs.getCsNo();
        }else {
            model.addAttribute("msg", "실패!");
            return "/noticeModify";
        }
    }
    // Q&A 수정
    @PostMapping(value = "/qnaModify")
    public String qnaModify(@ModelAttribute Cs cs
            , Model model
            , HttpServletRequest request){
        int result = cService.updateQna(cs);
        if(result > 0) {
            return "redirect:/qnaDetail?csNo="+cs.getCsNo();
        }else {
            model.addAttribute("msg", "실패!");
            return "/qnaModify";
        }
    }
    // 공지사항 목록 화면
    @RequestMapping(method = RequestMethod.GET, value = "/notice")
    public String noticeView(
            Model model) {
        List<Cs> noticeList = cService.selectNoticeList();
        for(int i = 0; i < noticeList.size(); i++){
            noticeList.get(i).setRowNum(i+1);
        }
        model.addAttribute("noticeList",noticeList);
        return "/notice";
    }
    // Q&A 목록 화면
    @GetMapping(value = "/qna")
    public String qnaView(
            Model model) {
        List<Cs> qnaList = cService.selectQnaList();
        for(int i = 0; i < qnaList.size(); i++) {
            qnaList.get(i).setRowNum(i+1);
        }
        model.addAttribute("qnaList", qnaList);
        return "/qna";
    }

    // 공지사항 삭제
    @GetMapping(value = "/noticeRemove")
    public String noticeRemove(
            @RequestParam("csNo") int csNo
            , Model model) {
        int result = cService.deleteNotice(csNo);
        if(result > 0) {
            return "redirect:/notice";
        }else {
            model.addAttribute("msg", "삭제 실패!");
            return "/noticeDetail";
        }
    }
    // Q&A 삭제
    @GetMapping(value = "qnaRemove")
    public String qnaRemove(
            @RequestParam("csNo") int csNo
            , Model model) {
        int result = cService.deleteQna(csNo);
        if(result > 0) {
            return "redirect:/qna";
        }else {
            model.addAttribute("msg", "삭제 실패!");
            return "/qnaDetail";
        }
    }

    // 공지사항 상세
    @GetMapping(value = "/noticeDetail")
    public String noticeDetailView(
            @RequestParam("csNo") int csNo
            , Model model){
        Cs cs = cService.selectOneByNo(csNo);
        model.addAttribute("cs", cs);
        return "/noticeDetail";
    }
    // Q&A 상세
    @GetMapping(value = "/qnaDetail")
    public String qnaDetailView(
            @RequestParam("csNo") int csNo
            , Model model) {
        Cs cs = cService.selectQnaOneByNo(csNo);
        model.addAttribute("cs", cs);
        return "/qnaDetail";
    }

    // 공지사항 검색
    @GetMapping(value = "/noticeSearch")
    public String noticeSearchView(
            @ModelAttribute CsSearch nSearch
            , @RequestParam("searchValue") String keyword
            , @RequestParam(value = "searchCondition") String condition
            , Model model) {
        List<Cs> searchList = cService.selectListByKeyword(nSearch);
        if(!searchList.isEmpty()) {
            model.addAttribute("search", nSearch);
//            model.addAttribute("pi", pi);
            model.addAttribute("nSearchList", searchList);
            return "/noticeSearch";
        }else {
            model.addAttribute("msg", "조회실패!");
            return "/notice";
        }
    }
    // Q&A 검색
    @RequestMapping(value = "/qnaSearch", method = RequestMethod.GET)
    public String qnaSearchView(
            @ModelAttribute CsSearch qSearch
            , @RequestParam("searchValue") String keyword
            , @RequestParam(value = "searchCondition") String condition
            , Model model) {
        List<Cs> searchList = cService.selectQnaListByKeyword(qSearch);
        if (!searchList.isEmpty()) {
            model.addAttribute("search", qSearch);
//            model.addAttribute("pi", pi);
            model.addAttribute("qSearchList", searchList);
            return "/qnaSearch";
        } else {
            model.addAttribute("msg", "조회실패!");
            return "/qna";
        }
    }
}
