package com.ccalendar.server.api.controllers;

import com.ccalendar.server.api.data.GenreData;
import com.ccalendar.server.api.data.RegionData;
import com.ccalendar.server.api.data.ResultResponse;
import com.ccalendar.server.domain.services.info.InfoService;
import com.ccalendar.server.domain.util.GenreConverter;
import com.ccalendar.server.domain.util.RegionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping("/list")
public class InfoController {
    private InfoService infoService;

    @GetMapping("/regions")
    @ResponseBody
    public ResultResponse<Collection<RegionData>> getRegions() {
        Collection<RegionData> result = RegionConverter.convertToRegionDTO(
                infoService.getAllRegions()
        );
        return new ResultResponse<>(result);
    }

    @GetMapping("/genres")
    @ResponseBody
    public ResultResponse<Collection<GenreData>> getGenres() {
        Collection<GenreData> result = GenreConverter.convertToGenreDTO(
                infoService.getAllGenres()
        );
        return new ResultResponse<>(result);
    }

    @Autowired
    public void setInfoService(InfoService infoService){
        this.infoService = infoService;
    }
}
