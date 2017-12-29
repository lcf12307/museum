package com.crtvu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-dao.xml"})
public class AttachmentDAOTest {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Test
    public void insertAttachment() throws Exception {

        attachmentDAO.insertAttachment("xiaoming","../../file/test.doc",1,2017);
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
    }

    @Test
    public void selectAttachementByName() throws Exception {
    }

    @Test
    public void countAttachementByLimit() throws Exception {
    }

    @Test
    public void countAttachementByName() throws Exception {
    }

}