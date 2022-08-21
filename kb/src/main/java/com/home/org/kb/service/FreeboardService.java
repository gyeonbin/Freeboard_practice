package com.home.org.kb.service;

import com.home.org.kb.entity.Freeboard;
import com.home.org.kb.repository.FreeboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeboardService {

    @Autowired
    FreeboardRepository freeboardRepository;



    //freeboard 생성,업데이트를 위한 함수
    public void create(Freeboard freeboard) {
        freeboardRepository.save(freeboard);
    }

    //freeboard 리스트
    public List<Freeboard> selectList() {
        List<Freeboard> list = freeboardRepository.findAll();
        return list;
    }

    //freeboard 내용 하나 선택
    public Freeboard bring_table(long index) {
        Freeboard freeboard =
                freeboardRepository.findById(index).orElse(new Freeboard());

        //hits 수 올리고 return해줌
        freeboard.setHits(freeboard.getHits()+1);
        return freeboard;
    }








}
