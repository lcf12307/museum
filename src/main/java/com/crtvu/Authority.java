package com.crtvu;

/**
 * Created by Jixw on 2017/12/26.
 */
public enum Authority {

    NotificationForm("申报书管理",1),
    ScoreTable("打分表管理",2),
    Calculate("计算管理",4),
    StatisticsTable("统计表管理",8),
    Rank("排名管理",16),
    Museum("博物馆管理",32),
    Quota("专家管理",64),
    Member("用户管理",128)
    ;

    private String name;
    private int authority;

    Authority(String name, int authority) {
        this.name = name;
        this.authority = authority;
    }



}
