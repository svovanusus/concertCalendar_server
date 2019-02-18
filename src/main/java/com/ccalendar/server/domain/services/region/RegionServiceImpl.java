package com.ccalendar.server.domain.services.region;

import com.ccalendar.server.db.repository.RegionRepository;
import com.ccalendar.server.domain.model.Region;
import com.ccalendar.server.domain.util.RegionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {
    private RegionRepository regionRepository;

    @Override
    public Region getById(long id) {
        return RegionConverter.convertToRegionDomain(regionRepository.findById(id).get());
    }

    @Autowired
    public void setRegionRepository(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }
}
