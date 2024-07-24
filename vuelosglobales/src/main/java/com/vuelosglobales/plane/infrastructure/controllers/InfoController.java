package com.vuelosglobales.plane.infrastructure.controllers;

import java.util.List;

import com.vuelosglobales.plane.application.services.InfoService;

public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }
    public void showAirlines(){
        List<String> airlineList = infoService.getAirline();
        int count=0;
        for (String airline : airlineList) {
            count++;
            System.out.println("ID: "+count+" "+airline);
        }
    }
    public void showStatuses(){
        List<String> statusList = infoService.getStatus();
        int count = 0;
        for (String status : statusList) {
            count++;
            System.out.println("ID :"+count+" "+status);
        }
    }
    public void showModels(){
        List<String> modelList = infoService.getModel();
        int count = 0;
        for (String model : modelList) {
            count++;
            System.out.println("ID: "+count+" "+model);
        }
    }
    public void showIds(){
        List<String> idList = infoService.getId();
        for(String id : idList){
            System.out.println(id);
        }
    }
}
