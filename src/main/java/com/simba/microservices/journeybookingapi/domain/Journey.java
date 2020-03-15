package com.simba.microservices.journeybookingapi.domain;

import com.simba.microservices.dto.Connection;
import static java.util.concurrent.TimeUnit.SECONDS;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Journey {
    private Long id;
    private String from;
    private String to;
    private Date departure;
    private Date arrival;

    public static Journey fromConnection(Connection connection) {
        if (connection == null || connection.getFrom() == null || connection.getTo() == null) {
            throw new IllegalArgumentException("Invalid connection");
        }
        Journey journey = new Journey();
        journey.setFrom(connection.getFrom().getStation().getName());
        journey.setTo(connection.getTo().getStation().getName());
        journey.setDeparture(new Date(SECONDS.toMillis(connection.getFrom().getDeparture())));
        journey.setArrival(new Date(SECONDS.toMillis(connection.getTo().getArrival())));
        return journey;
    }

    public Journey withId(long id) {
        this.setId(id);
        return this;
    }
}
