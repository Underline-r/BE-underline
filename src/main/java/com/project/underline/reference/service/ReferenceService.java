package com.project.underline.reference.service;

import com.project.underline.reference.entity.Reference;
import com.project.underline.reference.entity.repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReferenceService {
    private final ReferenceRepository referenceRepository;

    public Reference checkExistReference(String title){

        Optional<Reference> existReference = referenceRepository.findByTitle(title);

        if(existReference.isPresent()){
            return existReference.get();
        }

        Reference newReference = referenceRepository.save(new Reference(title));

        return newReference;
    }
}
