package com.librarymanagement.maids.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.librarymanagement.maids.controller..*(..))")
    public void controllerPointcut() {}

    @Pointcut("execution(* com.librarymanagement.maids.service..*(..))")
    public void servicePointcut() {}

    @Before("controllerPointcut() || servicePointcut()")
    public void logMethodCall() {
        // Log method call
        logger.info("Method call intercepted.");
    }

    @AfterThrowing(pointcut = "controllerPointcut() || servicePointcut()", throwing = "ex")
    public void logException(Exception ex) {
        // Log exception
        logger.error("Exception occurred: {}", ex.getMessage(), ex);
    }
}

