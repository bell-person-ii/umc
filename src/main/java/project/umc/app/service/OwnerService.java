package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.UserEntity;
import project.umc.app.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public void saveOwner(OwnerEntity ownerEntity){
        ownerRepository.save(ownerEntity);
    }

    public OwnerEntity findOwnerById(Long id){return ownerRepository.findOneById(id);}

    public OwnerEntity findOwnerByEmail(String email){
        return ownerRepository.findOneByEmail(email);
    }


    public Optional<OwnerEntity> isAlreadyExistUserByEmail(String email){

        OwnerEntity ownerEntity = ownerRepository.findOneByEmail(email);
        Optional<OwnerEntity> ownerEntityOptional;

        if(ownerEntity == null){
            return ownerEntityOptional = Optional.empty();
        }

        else{
            ownerEntityOptional = Optional.of(ownerRepository.findOneByEmail(email));
            return ownerEntityOptional;
        }

    }

    public Optional<OwnerEntity> isExistOwner(Long id){

        OwnerEntity findOwnerEntity = ownerRepository.findOneById(id);

        Optional<OwnerEntity> optionalOwnerEntity;

        if(findOwnerEntity == null){
            return optionalOwnerEntity = Optional.empty();
        }
        else{
            return optionalOwnerEntity = Optional.of(findOwnerEntity);
        }
    }
}
