package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.repository.OwnerRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public void saveOwner(OwnerEntity ownerEntity){
        ownerRepository.save(ownerEntity);
    }

    public OwnerEntity findOwnerByEmail(String email){
        return ownerRepository.findOneByEmail(email);
    }
}
