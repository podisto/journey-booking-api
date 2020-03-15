package com.simba.microservices.journeybookingapi;

import com.simba.microservices.dto.Connection;
import com.simba.microservices.dto.Connections;
import com.simba.microservices.journeybookingapi.domain.Journey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.simba.microservices.journeybookingapi.JourneyNotFoundException.MSG_JOURNEY_NOT_FOUND;
import static com.simba.microservices.journeybookingapi.domain.Journey.fromConnection;
import java.util.List;

@Service
@Slf4j
public class JourneyService {
    private final JourneyRepository journeyRepository;
    private final ConnectionLookupClient connectionLookupClient;

    public JourneyService(JourneyRepository journeyRepository, ConnectionLookupClient connectionLookupClient) {
        this.journeyRepository = journeyRepository;
        this.connectionLookupClient = connectionLookupClient;
    }

    public Connections searchConnections(String from, String to) {
        Connections connections = connectionLookupClient.getConnections(from, to);
        return connections == null ? new Connections() : connections;
    }

    public Journey bookJourney(final Connection connection) {
        Journey journey = fromConnection(connection);
        return journeyRepository.save(journey);
}

    public Journey deleteJourney(final Long id) {
        return journeyRepository.findById(id).map(journey -> {
            journeyRepository.deleteById(id);
            return journey;
        }).orElseThrow(() -> new JourneyNotFoundException(String.format(MSG_JOURNEY_NOT_FOUND, id)));
    }

    public Journey getJourney(final Long id) {
        return journeyRepository.findById(id).orElseThrow(() -> new JourneyNotFoundException(String.format(MSG_JOURNEY_NOT_FOUND, id)));
    }

    public List<Journey> findAll() {
        return journeyRepository.findAll();
    }

    public Journey updateJourney(final Long id, Connection connection) {
        return journeyRepository.findById(id)
                .map(journey -> journeyRepository.save(fromConnection(connection).withId(journey.getId())))
                .orElseThrow(() -> new JourneyNotFoundException(String.format(MSG_JOURNEY_NOT_FOUND, id)));
    }
}
