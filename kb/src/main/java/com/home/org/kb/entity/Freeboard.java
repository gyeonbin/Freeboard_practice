package com.home.org.kb.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Freeboard {

    @Id
    @Column(name = "board_index",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String filename;
    private int hits;
    private LocalDateTime regdate;

}
