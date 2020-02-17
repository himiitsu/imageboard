package com.project.imageboard.service;

import com.project.imageboard.dao.MessageDao;
import com.project.imageboard.dao.ThreadsDao;
import com.project.imageboard.model.Message;
import com.project.imageboard.model.MessageThread;
import org.hibernate.Hibernate;
import org.hibernate.MultiIdentifierLoadAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ThreadsDao threadsDao;

    public void setIdFromDB(List<MessageThread> messageThreads){
        if (messageThreads.size() > 0){
            Message.setId(messageDao.findFirst());
        }
    }

    public void threadBump(List<MessageThread> messageThreads){
        List<Integer> order = threadsDao.sortThreads();
        for(int i = 0; i < messageThreads.size(); i++){
            if(messageThreads.get(i).getId() != order.get(i)){
                int j = 0;
                MessageThread buffer = messageThreads.get(i);
                int oldPlace = messageThreads.indexOf(messageThreads.get(i));
                while(messageThreads.get(j).getId() == order.get(j)){
                    j++;
                    if(j == messageThreads.size() - 1) break;
                }
                int newPlace = messageThreads.indexOf(messageThreads.get(j));
                messageThreads.set(oldPlace, messageThreads.get(j));
                messageThreads.set(newPlace, buffer);
            }
        }
    }

    public void addThread(String header, String text){
        threadsDao.save(new MessageThread(header, text));
    }

    public int addMessage(int id, String text){
        int i = 0;
        List<MessageThread> messageThreads = new ArrayList<>();
        threadsDao.findAll().forEach(messageThreads::add);
        setIdFromDB(messageThreads);
        Message message = new Message(text);
        messageDao.save(message);
        while (messageThreads.get(i).getId() != id){
            i++;
        }
        messageThreads.get(i).getMessages().add(message);
        threadsDao.save(messageThreads.get(i));
        return 0;
    }

    public int addMessage(int id, String text, MultipartFile imageFile) {
        int i = 0;
        List<MessageThread> messageThreads = new ArrayList<>();
        threadsDao.findAll().forEach(messageThreads::add);
        setIdFromDB(messageThreads);
        Message message = new Message(text);
        try {

            Byte[] byteObjects = new Byte[imageFile.getBytes().length];

            int j = 0;

            for (byte b : imageFile.getBytes()){
                byteObjects[j++] = b;
            }

            message.setImage(byteObjects);

            messageDao.save(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (messageThreads.get(i).getId() != id){
            i++;
        }
        messageThreads.get(i).getMessages().add(message);
        threadsDao.save(messageThreads.get(i));
        return 0;
    }

    public List<MessageThread> getThreads(){
        List<MessageThread> messageThreads = new ArrayList<>();
        threadsDao.findAll().forEach(messageThreads::add);
        setIdFromDB(messageThreads);
        threadBump(messageThreads);
        return messageThreads;
    }

    public List<Message> getMessages(int id){
        int i = 0;
        List<MessageThread> messageThreads = new ArrayList<>();
        threadsDao.findAll().forEach(messageThreads::add);
        setIdFromDB(messageThreads);
        while (messageThreads.get(i).getId() != id){
            i++;
        }
        return messageThreads.get(i).getMessages();
    }

//    public void saveImage(Blob imageFile) throws IOException {
//        String folder = "C:/photos/";
//        byte[] bytes = imageFile.getBytes();
//        Path path = Paths.get(folder + imageFile.getOriginalFilename());
//        Files.write(path, bytes);
//    }
}
