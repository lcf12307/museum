package com.crtvu.dao;

import com.crtvu.dto.StrValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Jixw on 2018/1/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class MuQASDAOTest {

    @Autowired
    MuQASDAO muQASDAO;

    @Test
    public void getQuotaIdCount() throws Exception {
        /*HashMap<String,Integer> kk =;
        for(String key:kk.keySet()){
            System.out.print(key+",");System.out.println(kk.get(key));
        }*/
        System.out.println(muQASDAO.getQuotaIdCount("2017"));
        List<StrValue> s=muQASDAO.getQuotaIdCount("2017");
        System.out.println(s.get(0));
    }

}