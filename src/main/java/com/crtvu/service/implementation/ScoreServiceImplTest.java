package com.crtvu.service.implementation;

import com.crtvu.service.ScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Jixw on 2018/1/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-web.xml"})
public class ScoreServiceImplTest {

    @Autowired
    ScoreService scoreService;


    public static boolean canParseInt(String  str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Test
    public void Calculate()  {
        try {
            System.out.println(scoreService.Calculate("2017").get("msg"));
        } catch (Exception e) {
            if(canParseInt(e.getMessage())) {
                System.out.println(Integer.parseInt(e.getMessage()) + ":" + e.getCause().getMessage());
            }else
                System.out.println(e.getMessage());

        }
    }

}