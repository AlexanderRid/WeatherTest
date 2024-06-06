package org.example.models.currentjson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Location {

    private String name;
    private String region;
    private String country;
    private long lat;
    private long lon;
    @JsonProperty("tz_id")
    private String tzId;
    @JsonProperty("localtime_epoch")
    private String localtimeEpoch;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd H:mm")
    private LocalDateTime localtime;

}
