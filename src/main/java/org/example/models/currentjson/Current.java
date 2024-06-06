package org.example.models.currentjson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Current {

    @JsonProperty("last_updated_epoch")
    private long lastUpdatedEpoch;
    @JsonProperty("last_updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd H:mm")
    private LocalDateTime lastUpdated;
    @JsonProperty("temp_c")
    private long tempC;
    @JsonProperty("temp_f")
    private long tempF;
    @JsonProperty("is_day")
    private int isDay;
    private Condition condition;
    @JsonProperty("wind_mph")
    private long windMph;
    @JsonProperty("wind_kph")
    private long windKph;
    @JsonProperty("wind_degree")
    private int windDegree;
    @JsonProperty("wind_dir")
    private String windDir;
    @JsonProperty("pressure_mb")
    private long pressureMb;
    @JsonProperty("pressure_in")
    private long pressureIn;
    @JsonProperty("precip_mm")
    private long precipMm;
    @JsonProperty("precip_in")
    private long precipIn;
    private int humidity;
    private int cloud;
    @JsonProperty("feelslike_c")
    private long feelslikeC;
    @JsonProperty("feelslike_f")
    private long feelslikeF;
    @JsonProperty("windchill_c")
    private long windchillC;
    @JsonProperty("windchill_f")
    private long windchillF;
    @JsonProperty("heatindex_c")
    private long heatindexC;
    @JsonProperty("heatindex_f")
    private long heatindexF;
    @JsonProperty("dewpoint_c")
    private long dewpointC;
    @JsonProperty("dewpoint_f")
    private long dewpointF;
    @JsonProperty("vis_km")
    private long visKm;
    @JsonProperty("vis_miles")
    private long visMiles;
    private long uv;
    @JsonProperty("gust_mph")
    private long gustMph;
    @JsonProperty("gust_kph")
    private long gustKph;

}
