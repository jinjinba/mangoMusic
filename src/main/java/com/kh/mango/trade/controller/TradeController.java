package com.kh.mango.trade.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.mango.comment.domain.Comment;
import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.PageInfo;
import com.kh.mango.trade.domain.Trade;
import com.kh.mango.trade.domain.TradeComment;
import com.kh.mango.trade.domain.TradeSearch;
import com.kh.mango.trade.service.TradeService;
import com.kh.mango.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.List;

@Controller
public class TradeController {
    @Autowired
    private TradeService tService;

    @GetMapping("/trade")
    public String tradeView( @RequestParam(value="page", required=false, defaultValue="1") Integer page, Model model){
        int totalCount = tService.getTradeListCount();
        PageInfo pi = this.getPageInfo(page, totalCount);
        List<Trade> tList =  tService.selectTradeList(pi);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tList.size(); i++){
            tList.get(i).setRowNum(i+1);
        }
        for(int i = 0; i < pi.getEndNavi(); i++){
            sb.append("<a href='http://localhost:8985/trade?page="+(i+1)+"'>"+(i+1)+"</a> ");
        }
        model.addAttribute("paging",sb);
        model.addAttribute("pi",pi);
        model.addAttribute("tList",tList);
        return "trade";
    }

    @GetMapping("/tradeSearch")
    public String tradeSearch(@ModelAttribute TradeSearch tradeSearch,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                              Model model
    ){
        int totalCount = tService.getListCount(tradeSearch);
        PageInfo pi = this.getPageInfo(currentPage, totalCount);
        List<Trade> searchList = tService.selectTradeListByKeyword(pi, tradeSearch);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < searchList.size(); i++){
            searchList.get(i).setRowNum(i+1);
        }
        for(int i = 0; i < pi.getEndNavi()-1; i++) {
            sb.append("<a href='http://localhost:8985/tradeSearch?page="+(i+1)+"'>"+(i+1)+"</a> ");
        }
        if (!searchList.isEmpty()) {
            model.addAttribute("paging", sb);
            model.addAttribute("search", tradeSearch);
            model.addAttribute("pi", pi);
            model.addAttribute("trade", searchList);
            return "/tradeSearch";
        } else {
            model.addAttribute("error", "조회실패!");
            return "redirect:trade";
        }

    }

    private PageInfo getPageInfo(int currentPage, int totalCount) {
        PageInfo pi = null;
        int boardLimit = 10;
        int naviLimit = 5;
        int maxPage;
        int startNavi;
        int endNavi;

        maxPage = (int) ((double)totalCount/boardLimit+0.9);
        Math.ceil((double)totalCount/boardLimit);
        startNavi = (((int)((double)currentPage/naviLimit+0.9))-1)*naviLimit+1;
        endNavi = startNavi + naviLimit -1;
        if(endNavi > maxPage) {
            endNavi = maxPage;
        }
        pi = new PageInfo(currentPage, boardLimit, naviLimit, startNavi, endNavi, totalCount, maxPage);
        return pi;
    }


    @GetMapping("/tradeDetail")
    public String tradeDetail(
            @RequestParam("tradeNo") int tradeNo,
            Model model
    ){
        Trade trade = tService.selectTradeOneByNo(tradeNo);
        List<TradeComment> tc = tService.selectTradeCommentList(tradeNo);
        model.addAttribute("tradeDetail",trade);
        model.addAttribute("cmList",tc);
        return "tradeDetail";
    }

    @GetMapping("/tradeWrite")
    public String tradeWriteView(){
        return "tradeWrite";
    }

    @PostMapping("/tradeWrite")
    public String tradeWrite(@ModelAttribute Trade trade){
        int result = tService.insertTrade(trade);
        return "redirect:trade";
    }

    @GetMapping("/tradeModify")
    public String tradeModifyView(@RequestParam("tradeNo") int tradeNo,Model model){
        Trade trade = tService.selectTradeModify(tradeNo);
        model.addAttribute("trade",trade);
        return "tradeModify";
    }

    @PostMapping("/tradeWriteSubmit")
    public String tradeModify(@ModelAttribute Trade trade){
        int result = tService.updateTrade(trade);

        return "redirect:tradeDetail?tradeNo="+trade.getTradeNo();
    }

    private String path2="C:\\study\\programing\\project holder\\mangoMusic\\src\\main\\resources\\static\\userMusic";

    @PostMapping("/ajaxAudioUpload")
    @ResponseBody
    public String audio(@RequestParam("file") MultipartFile multi, HttpServletRequest request, HttpServletResponse response ) {
        String url = null;

        String jsonString = null;
        try {

            //String uploadpath = request.getServletContext().getRealPath(path);
            String uploadpath = path2;

            String originFilename = multi.getOriginalFilename();
            String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
            String saveFileName = genSaveFileName("_" + originFilename.replace(extName, "") + extName);

            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = "";

            System.out.println("uploadpath : " + uploadpath);
            System.out.println("originFilename : " + originFilename);
            System.out.println("extensionName : " + extName);
            System.out.println("saveFileName : " + saveFileName);
            if (!multi.isEmpty()) {
                File file = new File(uploadpath.substring(0, uploadpath.length() - 10), saveFileName);
                multi.transferTo(file);
            }
            jsonString = saveFileName + "," + uploadpath;
        } catch (Exception e) {
            e.printStackTrace();
            return jsonString;
        }
        return jsonString;
    }

    // 현재 시간을 기준으로 파일 이름 생성
    private String genSaveFileName(String extName) {
        String fileName = "";

        Calendar calendar = Calendar.getInstance();
        fileName += "";
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += calendar.get(Calendar.MILLISECOND);
        fileName += extName;

        return fileName;
    }






}

