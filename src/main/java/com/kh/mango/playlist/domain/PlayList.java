package com.kh.mango.playlist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayList {
    private int playListNo;
    private String musicMBID;
    private String playListName;
    private int userNo;
}
