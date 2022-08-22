package com.home.org.kb.controller;

import com.home.org.kb.entity.Freeboard;
import com.home.org.kb.req.FreeboardReq;
import com.home.org.kb.service.FreeboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
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
    public String view(Model model, long id) {
        //제목링크 눌러서 전달된 id가 여기 id로 들어옴

        //bring_table안에 hits 올려주는 함수 있음
        Freeboard freeboard = freeboardService.bring_table(id);
        model.addAttribute("freeboard",freeboard);

        return "freeboard/view";
    }

    //----------------------------write-----------------------------//
    @GetMapping("freeboard/write")
    public String write(FreeboardReq freeboardReq){
        return "freeboard/write";
    }

    @PostMapping("freeboard/write")
    public String pwrite(@Valid FreeboardReq freeboardReq,
                         BindingResult bindingResult,
                         @RequestParam("file") MultipartFile file) {
        //html에서 id="file" 인 post된 인자를 전달받음

        if(bindingResult.hasErrors()) {
            return "freeboard/write";
        }
        //파일이름 가져오기
        String filename = file.getOriginalFilename();

        //파일 static 폴더안에 저장하기
        File savefile = new File("C:/Users/15/Desktop/Freeboard_practice" +
                "kb/src/main/resource/sstatic/img/"+filename);

        //file.transferTo()는 예외를 발생 시킬 수 있으므로 transfer로 감싸야함
        try {
            file.transferTo(savefile);
        } catch (IOException e) {
            e.printStackTrace();
            //예외가 발생하게 되면 write화면으로 넘겨줌
            return "freeboard/write";
        }

        freeboardService.create(
                Freeboard.builder().
                        title(freeboardReq.getTitle()).
                        content(freeboardReq.getContent()).
                        filename(freeboardReq.getFilename()).
                        hits(0).
                        regdate(LocalDateTime.now()).
                        build()
        );

        return "redirect:/freeboard";
    }
    //----------------------------write-----------------------------//



}