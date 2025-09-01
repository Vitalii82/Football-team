
package com.football.service;

import com.football.model.TrainingSession;
import com.football.util.JsonLoader;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingSessionJsonService {
    private List<TrainingSession> cache;

    public List<TrainingSession> getAll() {
        if (cache == null) {
            cache = JsonLoader.readList("json/training_sessions.json", TrainingSession.class);
        }
        return Collections.unmodifiableList(cache);
    }

    public List<TrainingSession> getByTeam(Integer teamId) {
        return getAll().stream()
                .filter(ts -> ts.getTeamId() != null && ts.getTeamId().equals(teamId))
                .collect(Collectors.toList());
    }
}
