package com.clever.dalo.config.properties;

import lombok.Data;

@Data
public class MigrationProperties {

    private String url;

    public MigrationProperties(String url) {

        this.url = url;
    }
}
