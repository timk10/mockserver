package com.mockserver.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tim on 2/6/16.
 */
public class Exchange {

    private long id;

    private String filename;

    @JsonCreator
    public Exchange(@JsonProperty("filename")String filename) {
        this.filename = filename;
    }

    @JsonProperty
    public String getFilename() {
        return filename;
    }

    @JsonProperty
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
