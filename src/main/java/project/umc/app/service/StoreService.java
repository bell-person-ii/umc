package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.StoreEntity;
import project.umc.app.repository.StoreRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Optional<StoreEntity> isExistStore(Long id){
        Optional<StoreEntity> userEntity = Optional.of(storeRepository.findOneStore(id));
        return userEntity;
    }
}
