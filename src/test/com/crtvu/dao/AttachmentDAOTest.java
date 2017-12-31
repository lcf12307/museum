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

        attachmentDAO.insertAttachment("xiaoming","../../file/test.doc",1,2017,12321);
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
        List<AttachmentEntity> result = attachmentDAO.selectAttachementByLimit("",2017,0,2);
        System.out.print(result);
    }

    @Test
    public void selectAttachementByName() throws Exception {
    }

    @Test
    public void countAttachementByLimit() throws Exception {
        int result = attachmentDAO.countAttachementByLimit("",2017);
        System.out.print(result);
    }

    @Test
    public void countAttachementByName() throws Exception {
    }

}