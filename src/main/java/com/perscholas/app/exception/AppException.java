package com.perscholas.app.exception;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppException extends Exception{


        public AppException(String message) {
            super(message);
        }


    }

