package com.example.pds_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Notice {

    private Integer noticeId;//公告id
    private String noticeContent;//公告内容

}
