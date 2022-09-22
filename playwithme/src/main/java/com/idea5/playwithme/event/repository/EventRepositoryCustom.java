package com.idea5.playwithme.event.repository;

import com.idea5.playwithme.event.domain.Event;

public interface EventRepositoryCustom {
    Event findTopEventByArticleCount(Integer categoryNo);
}
