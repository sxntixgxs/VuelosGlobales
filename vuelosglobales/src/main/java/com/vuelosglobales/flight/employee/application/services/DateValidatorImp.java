package com.vuelosglobales.flight.employee.application.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.vuelosglobales.flight.employee.domain.ports.in.DateValidator;

public class DateValidatorImp implements DateValidator{
    private static final String DATE_FORMAT = "YYYY-MM-DD";

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
