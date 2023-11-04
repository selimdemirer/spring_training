package com.cydeo.service;

import com.cydeo.config.AppConfigData;
import com.cydeo.config.DBConfigData;
import com.cydeo.model.Comment;
import com.cydeo.proxy.CommentNotificationProxy;
import com.cydeo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommentService {

    //Using "final" is good practise! Because it is warning us to create constructor
    private final CommentRepository commentRepository; // We need Has A relationship!
    private final CommentNotificationProxy commentNotificationProxy; // We need Has A relationship!
    private final AppConfigData appConfigData; // We need Has A relationship!
    private final DBConfigData dbConfigData; // We need Has A relationship!

    // No need for @Autowired, because we have one constructor
    public CommentService(CommentRepository commentRepository, @Qualifier("EMAIL") CommentNotificationProxy commentNotificationProxy, AppConfigData appConfigData, DBConfigData dbConfigData) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
        this.appConfigData = appConfigData;
        this.dbConfigData = dbConfigData;
    }

    public void publishComment(Comment comment){

        //save in the DB
        commentRepository.storeComment(comment); // In order to use this method, we created Has A relationship using class (interface -> loosely coupled) name above fields!

        //send email
        commentNotificationProxy.sendComment(comment); // In order to use this method, we created Has A relationship using class (interface -> loosely coupled) name above fields!

    }

    public void printConfigData(){
        //print ozzy
        System.out.println(appConfigData.getUserName());
        //print abc123
        System.out.println(appConfigData.getPassword());
        //print url
        System.out.println(appConfigData.getUrl());
    }

    public void printDBConfigData(){
        System.out.println(dbConfigData.getUserName());
        System.out.println(dbConfigData.getPassword());
        System.out.println(dbConfigData.getType());
    }

}
