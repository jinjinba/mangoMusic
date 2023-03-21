package com.kh.mango.playlist.store;

import com.kh.mango.playlist.domain.PlayList;

public interface PlayListStore {
    int addPlaylist(PlayList playlistParam);
}
