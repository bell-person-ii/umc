package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.StoreEntity;
import project.umc.app.dto.AddStoreRequestDto;
import project.umc.app.repository.FoodCategoryRepository;
import project.umc.app.repository.OwnerRepository;
import project.umc.app.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final OwnerRepository ownerRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    public void save(StoreEntity storeEntity){
        storeRepository.save(storeEntity);
    }

    public StoreEntity findOneById(Long id){
        return storeRepository.findOneStore(id);
    }

    // Dto에서 Owner 필드에 직접 Owner엔티티를 주입하는걸 피하고자 스토어 엔티티에 오너 엔티티를 추가하는 함수를 추가
    public StoreEntity addOwnerEntityToStoreEntity(AddStoreRequestDto addStoreRequestDto,StoreEntity storeEntity){
        OwnerEntity ownerEntity = ownerRepository.findOneById(addStoreRequestDto.getOwnerId());
        storeEntity.editOwner(ownerEntity);
        return storeEntity;
    }

    public List<StoreEntity> findStoreEntityByOwnerId(Long id){
        return storeRepository.findOneByOwnerId(id);
    }

    public StoreEntity findLastStoreEntityByOwnerId(Long id){
        List<StoreEntity> storeEntityList=storeRepository.findOneByOwnerId(id);
        int listSize = storeEntityList.size();
        return storeEntityList.get(listSize-1);
    }

    public List<StoreEntity> findStoreEntityByOwnerEmail(String email){
        return storeRepository.findOneByOwnerEmail(email);
    }

    public FoodCategoryEntity findFoodCategoryById(Long id){
        return foodCategoryRepository.findFoodCategory(id);
    }

    public Optional<StoreEntity> isExistStore(Long id){
        Optional<StoreEntity> userEntity = Optional.of(storeRepository.findOneStore(id));
        return userEntity;
    }
}
