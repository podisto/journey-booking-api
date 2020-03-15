package com.simba.microservices.journeybookingapi;

import com.simba.microservices.journeybookingapi.domain.Journey;

import java.util.List;
import java.util.Optional;

public interface JourneyRepository {

    <S extends Journey> S save(S entity);

    Optional<Journey> findById(Long id);

    List<Journey> findAll();

    void deleteById(Long id);

    List<Journey> findByFromAndTo(String from, String to);
}
