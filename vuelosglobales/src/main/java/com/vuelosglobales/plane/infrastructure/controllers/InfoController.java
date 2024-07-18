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
        airlineList.forEach(System.out::println);
    }
    public void showStatuses(){
        List<String> statusList = infoService.getStatus();
        statusList.forEach(System.out::println);
    }
    public void showModels(){
        List<String> modelList = infoService.getModel();
        modelList.forEach(System.out::println);
    }
}
