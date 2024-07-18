package com.vuelosglobales.plane.application.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.vuelosglobales.plane.domain.ports.in.DateValidator;

public class DateValidatorImp implements DateValidator {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    @Override
    public boolean isValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try{
            sdf.parse(date);
            return true;
        }catch(ParseException e){
            return false;
        }

    }
    
}
