package com.kh.mango.playlist.store.logic;

import com.kh.mango.playlist.domain.PlayList;
import com.kh.mango.playlist.store.PlayListStore;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayListStoreLogic implements PlayListStore {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int addPlaylist(PlayList playlistParam) {
        return sqlSession.insert("PlaylistMapper.addPlaylist",playlistParam);
    }
}
