package com.vuelosglobales.flight.trip.application.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.vuelosglobales.flight.trip.domain.ports.in.DateValidator;



public class DateValidatorService implements DateValidator{
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public DateValidatorService(){}
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
