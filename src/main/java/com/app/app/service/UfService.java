package com.app.app.service;

import com.app.app.model.dto.UfDTO;
import com.app.app.model.entity.Uf;
import com.app.app.repository.UfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UfService {

    private final UfRepository repository;

    public UfDTO save(UfDTO ufDTO){
        Uf entity = ufDTO.toEntity();
        return repository.save(entity).toDTO();
    }

    public UfDTO findById(Long id){
        Uf uf = repository.findById(id).orElse(new Uf());
        return uf.toDTO();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<UfDTO> findAll(){
        return repository.findAll().stream().map(Uf::toDTO).collect(Collectors.toList());
    }
}

