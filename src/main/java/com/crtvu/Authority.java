package com.crtvu;

import java.util.HashMap;
import java.util.HashSet;

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

    public static HashSet<String> getAuthority(int auth){
        HashSet<String> set = new HashSet<>();
        for(Authority e:Authority.values()){
            if((e.authority&auth)>0)
                set.add(e.toString());
        }
        return set;
    }

    public static HashSet<String> getAuthorityNames(int auth){
        HashSet<String> set = new HashSet<>();
        for(Authority e:Authority.values()){
            if((e.authority&auth)>0)
                set.add(e.name);
        }
        return set;
    }

    public static void main(String[] args) {

        //test test
        //System.out.println(Authority.NotificationForm.name);
        //System.out.println(Authority.NotificationForm.authority);
        System.out.println(getAuthority(255));
        System.out.println(getAuthorityNames(255));
    }

}
