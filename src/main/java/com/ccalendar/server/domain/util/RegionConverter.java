package com.ccalendar.server.domain.util;

import com.ccalendar.server.api.data.RegionData;
import com.ccalendar.server.db.model.RegionModel;
import com.ccalendar.server.domain.model.Region;

import java.util.Collection;
import java.util.stream.Collectors;

public final class RegionConverter {
    private RegionConverter(){}

    public static Region convertToRegionDomain(RegionModel model){
        return new Region(
                model.getId(),
                model.getTitle()
        );
    }

    public static Collection<Region> convertToRegionDomain(Collection<RegionModel> models){
        return models.stream()
                .map(RegionConverter::convertToRegionDomain)
                .collect(Collectors.toList());
    }

    public static RegionData convertToRegionDTO(Region region){
        return new RegionData(
                region.getId(),
                region.getTitle()
        );
    }

    public static Collection<RegionData> convertToRegionDTO(Collection<Region> regions){
        return regions.stream()
                .map(RegionConverter::convertToRegionDTO)
                .collect(Collectors.toList());
    }
}
