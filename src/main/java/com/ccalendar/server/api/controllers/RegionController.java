package com.ccalendar.server.api.controllers;

import com.ccalendar.server.api.data.RegionData;
import com.ccalendar.server.api.data.ResultResponse;
import com.ccalendar.server.domain.services.region.RegionService;
import com.ccalendar.server.domain.util.RegionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class RegionController {
    private RegionService regionService;

    @GetMapping("/regions")
    @ResponseBody
    public ResultResponse<Collection<RegionData>> getRegions() {
        Collection<RegionData> result = RegionConverter.convertToRegionDTO(
                regionService.getAll()
        );
        return new ResultResponse<>(result);
    }

    @Autowired
    public void setRegionService(RegionService regionService){
        this.regionService = regionService;
    }
}
