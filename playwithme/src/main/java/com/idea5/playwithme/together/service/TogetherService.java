package com.idea5.playwithme.together.service;

import com.idea5.playwithme.timeline.exception.DataNotFoundException;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TogetherService {
    private final TogetherRepository togetherRepository;

    public Together findById(long id) {
        return togetherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("no %d question not found,".formatted(id)));
    }
}
