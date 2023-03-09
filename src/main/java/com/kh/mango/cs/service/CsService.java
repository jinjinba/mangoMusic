package com.kh.mango.cs.service;

import com.kh.mango.cs.domain.Cs;

public interface CsService {

    /**
     * 공지사항 등록
     * @param cs
     * @return int
     */
    int insertNotice(Cs cs);

}
