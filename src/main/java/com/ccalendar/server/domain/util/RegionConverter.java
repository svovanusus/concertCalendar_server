package com.ccalendar.server.domain.util;

import com.ccalendar.server.api.data.RegionData;
import com.ccalendar.server.db.model.RegionModel;
import com.ccalendar.server.domain.model.Region;

import java.util.Collection;
import java.util.stream.Collectors;

public final class RegionConverter {
    private RegionConverter(){}

    /**
     * Конвертация объекта БД в доменный уровень
     * @param model объект БД
     * @return доменный объект
     */
    public static Region convertToRegionDomain(RegionModel model){
        return new Region(
                model.getId(),
                model.getTitle()
        );
    }

    /**
     * Конвертация списка объектов БД в список доменных объектов
     * @param models список объектов БД
     * @return список доменных объектов
     */
    public static Collection<Region> convertToRegionDomain(Collection<RegionModel> models){
        return models.stream()
                .map(RegionConverter::convertToRegionDomain)
                .collect(Collectors.toList());
    }

    /**
     * Конвертация доменного объекта в DTO
     * @param region доменный объект
     * @return DTO
     */
    public static RegionData convertToRegionDTO(Region region){
        return new RegionData(
                region.getId(),
                region.getTitle()
        );
    }

    /**
     * Конвертация списка доменных объектов в список DTO
     * @param regions список доменных объектов
     * @return список DTO
     */
    public static Collection<RegionData> convertToRegionDTO(Collection<Region> regions){
        return regions.stream()
                .map(RegionConverter::convertToRegionDTO)
                .collect(Collectors.toList());
    }
}
