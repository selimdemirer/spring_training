package com.cydeo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ResponseWrapper { // This is our customized class. Basically, in my application every end point is gonna show "success-message-status code" fields and then object data..

    private boolean success;
    private String message;
    private Integer code;
    private Object data;

    public ResponseWrapper(String message, Object data){ //Why I created two constructor? Of course to be able to create "new ResponseWrapper" object. And based on the situation either I can pass only message (success-message-status code) (for ex:GetMapping) or I can pass message&data (for ex: DeleteMapping).
        this.message=message;
        this.data=data;
        this.code= HttpStatus.OK.value();
        this.success=true;
    }

    public ResponseWrapper(String message){
        this.message=message;
        this.code= HttpStatus.OK.value();
        this.success=true;
    }

}
