package com.ccalendar.server.domain.services.info;

import com.ccalendar.server.db.model.GenreModel;
import com.ccalendar.server.db.model.RegionModel;
import com.ccalendar.server.db.repository.GenreRepository;
import com.ccalendar.server.db.repository.RegionRepository;
import com.ccalendar.server.domain.exceptions.GenreNotFoundException;
import com.ccalendar.server.domain.exceptions.RegionNotFoundException;
import com.ccalendar.server.domain.model.Genre;
import com.ccalendar.server.domain.model.Region;
import com.ccalendar.server.domain.util.GenreConverter;
import com.ccalendar.server.domain.util.RegionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InfoServiceImpl implements InfoService {
    private RegionRepository regionRepository;
    private GenreRepository genreRepository;

    @Override
    public Region getRegionById(long id) throws RegionNotFoundException {
        Optional<RegionModel> region = regionRepository.findById(id);
        if (!region.isPresent())
            throw new RegionNotFoundException();

        return RegionConverter.convertToRegionDomain(region.get());
    }

    @Override
    public Collection<Region> getAllRegions() {
        return StreamSupport.stream(regionRepository.findAll().spliterator(), false)
                .map(RegionConverter::convertToRegionDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Genre getGenreById(long id) throws GenreNotFoundException {
        Optional<GenreModel> genre = genreRepository.findById(id);
        if (!genre.isPresent())
            throw new GenreNotFoundException();

        return GenreConverter.convertToGenreDomain(genre.get());
    }

    @Override
    public Collection<Genre> getAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(GenreConverter::convertToGenreDomain)
                .collect(Collectors.toList());
    }

    @Autowired
    public void setRegionRepository(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }
}
