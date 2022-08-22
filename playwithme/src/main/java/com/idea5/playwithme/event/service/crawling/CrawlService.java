package com.idea5.playwithme.event.service.crawling;

import java.time.LocalDateTime;

public interface CrawlService {
    LocalDateTime setUrl(int category);
    void saveEvent(int category);
}
