package com.cydeo.service;

import com.cydeo.model.Comment;
import com.cydeo.proxy.CommentNotificationProxy;
import com.cydeo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("singleton")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentService {

    //Using "final" is good practise! Because it is warning us to create constructor
    private final CommentRepository commentRepository; // We need Has A relationship!
    private final CommentNotificationProxy commentNotificationProxy; // We need Has A relationship!

    // No need for @Autowired, because we have one constructor
    public CommentService(CommentRepository commentRepository, @Qualifier("EMAIL") CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
    }

    public void publishComment(Comment comment){

        //save in the DB
        commentRepository.storeComment(comment); // In order to use this method, we created Has A relationship using class (interface -> loosely coupled) name above fields!

        //send email
        commentNotificationProxy.sendComment(comment); // In order to use this method, we created Has A relationship using class (interface -> loosely coupled) name above fields!

    }

}
