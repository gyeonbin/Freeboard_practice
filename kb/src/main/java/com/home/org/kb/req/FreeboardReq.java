package com.home.org.kb.req;


import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class FreeboardReq {

    private Long index;

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;


    private String filename;
    private int hits;
    private LocalDateTime regdate;

}
