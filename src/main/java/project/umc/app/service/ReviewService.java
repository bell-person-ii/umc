package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.ReviewEntity;
import project.umc.app.domain.StoreEntity;
import project.umc.app.domain.UserEntity;
import project.umc.app.dto.StoreReviewsResponseDto;
import project.umc.app.repository.ReviewRepository;
import project.umc.app.repository.StoreRepository;
import project.umc.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public void save(ReviewEntity reviewEntity){
        reviewRepository.save(reviewEntity);
    }

    public ReviewEntity insertUserAndStoreEntity(ReviewEntity reviewEntity,Long userId,Long storeId){

        UserEntity findUserEntity = userRepository.findOneById(userId);
        StoreEntity findStoreEntity = storeRepository.findOneStore(storeId);

        reviewEntity.editUserEntity(findUserEntity);
        reviewEntity.editStoreEntity(findStoreEntity);
        return reviewEntity;
    }

    public List<StoreReviewsResponseDto> ReviewEntitiesToResponseDto(List<ReviewEntity> reviewEntityList){
        List<StoreReviewsResponseDto> storeReviewsResponseDtoList= new ArrayList<>();
        for(int i=0; i<reviewEntityList.size();i++){
            StoreReviewsResponseDto resultDto = StoreReviewsResponseDto.createStoreReviewsResponseDto(reviewEntityList.get(i));
            storeReviewsResponseDtoList.add(resultDto);
        }
        return storeReviewsResponseDtoList;
    }



    public List<ReviewEntity> findAllByStoreId(Long id){
        List<ReviewEntity> reviewEntityList = reviewRepository.findAllByStoreId(id);
        return reviewEntityList;
    }
}
