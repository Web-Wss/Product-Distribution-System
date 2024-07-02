package com.example.pds_api.model;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("notice")
public class Notice {

    @TableId
    private Integer noticeId;//公告id

    private String noticeContent;//公告内容

}
