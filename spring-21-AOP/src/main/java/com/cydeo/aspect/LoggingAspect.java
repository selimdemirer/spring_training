package com.cydeo.aspect;

import com.cydeo.dto.CourseDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger; //simple logging fasat for java
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    // Logger: to put some information in console
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


        // EXECUTION EXAMPLE

//    @Pointcut("execution(* com.cydeo.controller.CourseController.*(..))") //includes all methods in the CourseController
//    public void myPointcut(){}
//
//    @Before("myPointcut()")
//    public void log() {
//        logger.info("Info log..........");
//    }
//
//    @Before("execution(* com.cydeo.controller.CourseController.*(..))")
//    public void log() {
//        logger.info("Info log..........");
//    }


        // JOINPOINT EXAMPLE

//    @Pointcut("execution(* com.cydeo.repository.CourseRepository.findById(*))") //it takes only one parameter
//    public void courseRepositoryFindByIdPC() {}
//
//    @Before("courseRepositoryFindByIdPC()")
//    public void beforeCourseRepositoryFindById(JoinPoint joinPoint) {
//        logger.info("Before -> Method: {}, Arguments: {}, Target: {}"
//                , joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());
//    }


        // WITHIN AND @WITHIN EXAMPLE

//    @Pointcut("within(com.cydeo.controller..*)")
//    public void anyControllerOperation() {}
//
//    @Pointcut("@within(org.springframework.stereotype.Service)")
//    public void anyServiceOperation() {}
//
//    @Before("anyControllerOperation() || anyServiceOperation()")
//    public void beforeControllerOrServiceAdvice(JoinPoint joinPoint) {
//        logger.info("Before -> Method: {}, Arguments: {}, Target: {}"
//                , joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());
//    }


        // ANNOTATION EXAMPLE

//    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)") // It only cares "DeleteMapping" methods
//    public void anyDeleteControllerOperation() {}
//
//    @Before("anyDeleteControllerOperation()")
//    public void beforeDeleteMappingAnnotation(JoinPoint joinPoint) {
//        logger.info("Before -> Method: {}, Arguments: {}, Target: {}"
//                , joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());
//    }


        // CUSTOM ANNOTATION EXAMPLE : You can define your methods using only one annotation => @LoggingAnnotation

//    @Pointcut("@annotation(com.cydeo.annotation.LoggingAnnotation)")
//    public void loggingAnnotationPC() {}
//
//    @Before("loggingAnnotationPC()")
//    public void beforeLoggingAnnotation(JoinPoint joinPoint) {
//        logger.info("Before -> Method: {}, Arguments: {}, Target: {}"
//                , joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());
//    }


        // CUSTOM ANNOTATION WITH "AFTER RETURNING" EXAMPLES

//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)") // It only cares "GetMapping" methods
//    public void afterReturningGetMappingAnnotation() {}

//    @AfterReturning(pointcut = "afterReturningGetMappingAnnotation()", returning = "result")
//    public void afterReturningGetMappingOperation(JoinPoint joinPoint, Object result) {
//        logger.info("After Returning -> Method: {}, Result: {}", joinPoint.getSignature(), result.toString());
//    }

//    @AfterReturning(pointcut = "afterReturningGetMappingAnnotation()", returning = "results")
//    public void afterReturningGetMappingOperation(JoinPoint joinPoint, List<CourseDTO> results) {
//        logger.info("After Returning -> Method: {}, Result: {}", joinPoint.getSignature(), results.toString());
//    }

/*    CourseDTO -> Object     --> This is ok
    List<CourseDTO> -> List<Object>     --> This is not ok    */


            // CUSTOM ANNOTATION WITH "AFTER THROWING" EXAMPLE

//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)") // It only cares "GetMapping" methods
//    public void afterReturningGetMappingAnnotation() {}

//    @AfterThrowing(pointcut = "afterReturningGetMappingAnnotation()", throwing = "exception")
//    public void afterThrowingGetMappingOperation(JoinPoint joinPoint, RuntimeException exception) { // If getAllCourses method works perfectly, I won't see anything in the console
//        logger.error("After Throwing -> Method: {}, Exception: {}"
//                , joinPoint.getSignature().toShortString(), exception.getMessage()); // toShortString: it will show only method name
//    }


        // CUSTOM ANNOTATION WITH "AROUND" EXAMPLE

    @Pointcut("@annotation(com.cydeo.annotation.LoggingAnnotation)")
    public void loggingAnnotationPC() {}

    @Around("loggingAnnotationPC()")
    public Object anyLoggingAnnotationOperation(ProceedingJoinPoint proceedingJoinPoint) {

        logger.info("Before -> Method: {} - Parameter {}"
                , proceedingJoinPoint.getSignature().toShortString(), proceedingJoinPoint.getArgs());

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        logger.info("After -> Method: {} - Result: {}"
                , proceedingJoinPoint.getSignature().toShortString(), result.toString());
        return result;

    }


}
