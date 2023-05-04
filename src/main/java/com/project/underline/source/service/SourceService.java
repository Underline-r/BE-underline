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

        Optional<Source> existSource = sourceRepository.findByTitle(title);

        if(existSource.isPresent()){
            return existSource.get();
        }

        Source newSource = sourceRepository.save(new Source(title));

        return newSource;
    }
}
