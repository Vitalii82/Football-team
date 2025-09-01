
package com.football.service;

import com.football.model.Stadium;
import com.football.util.JsonLoader;

import java.util.Collections;
import java.util.List;

public class StadiumJsonService {
    private List<Stadium> cache;

    public List<Stadium> getAll() {
        if (cache == null) {
            cache = JsonLoader.readList("json/stadiums.json", Stadium.class);
        }
        return Collections.unmodifiableList(cache);
    }

    public Stadium findById(Integer id) {
        return getAll().stream()
                .filter(s -> s.getStadiumId() != null && s.getStadiumId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
