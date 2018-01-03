package com.crtvu.dao;

import com.crtvu.entity.AttachmentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-dao.xml"})
public class AttachmentDAOTest {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Test
    public void insertAttachment() throws Exception {

        System.out.println(attachmentDAO.insertAttachment("博物馆23号","../../file/test.doc",1,2017,"1514980863247"));
    }

    @Test
    public void deleteAttachment() throws Exception {
        attachmentDAO.deleteAttachment(1);
    }

    @Test
    public void deleteAttachmentbyName() throws Exception {
    }

    @Test
    public void deleteAttachmentbyNameAndYear() throws Exception {
    }

    @Test
    public void selectAttachment() throws Exception {
        attachmentDAO.selectAttachment(1);
    }

    @Test
    public void updateAttachFile() throws Exception {
    }

    @Test
    public void selectAttachementByLimit() throws Exception {
        List<AttachmentEntity> result = attachmentDAO.selectAttachementByLimit("",2017,0,2,1);
        System.out.print(result);
    }

    @Test
    public void selectAttachementByName() throws Exception {
    }

    @Test
    public void countAttachementByLimit() throws Exception {
        int result = attachmentDAO.countAttachementByLimit("海燕博物馆",2017,1);
        System.out.print(result);
    }

    @Test
    public void countAttachementByName() throws Exception {
    }

    @Test
    public void countAttachement() throws Exception {
        System.out.println(attachmentDAO.countAttachement("海燕博物馆",2017,1));
    }

}