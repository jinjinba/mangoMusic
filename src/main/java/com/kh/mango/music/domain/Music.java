package com.kh.mango.music.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    private String musicMbid;
    private int musicPrice;
    private int musicLike;
}
