package com.home.org.kb.controller;

import com.home.org.kb.entity.Freeboard;
import com.home.org.kb.service.FreeboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FreeboardController {

    @Autowired
    FreeboardService freeboardService;

    //목록화면
    @GetMapping("freeboard")
    public String index(Model model){

        //정상적인 출력을 확인하기위한 빌더
        freeboardService.create(
                Freeboard.builder()
                        .index(1L)
                        .title("제목제목")
                        .content("내용")
                        .regdate(LocalDateTime.now())
                        .build()
        );
        freeboardService.create(
                Freeboard.builder()
                        .index(2L)
                        .title("123제목123제목")
                        .content("내용22")
                        .regdate(LocalDateTime.now())
                        .build()
        );

        //모델에 list를 추가해서 프론트로 전달
        List<Freeboard> list = freeboardService.selectList();
        model.addAttribute("list", list);
        return "freeboard/freeboard";
    }

    @GetMapping("freeboard/view")
    public String view(Model model, Long id) { //제목링크 눌러서 전달된 id가 여기 id로 들어옴

        //bring_table안에 hits 올려주는 함수 있음
        Freeboard freeboard = freeboardService.bring_table(id);
        model.addAttribute(freeboard);

        return "freeboard/view";
    }


}
