package com.project.underline.source.service;

import com.project.underline.source.entity.Source;
import com.project.underline.source.entity.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SourceService {
    private final SourceRepository sourceRepository;

    public Source checkExistSource(String title){

        Optional<Source> existReference = sourceRepository.findByTitle(title);

        if(existReference.isPresent()){
            return existReference.get();
        }

        Source newSource = sourceRepository.save(new Source(title));

        return newSource;
    }
}
