package project.umc.app.dto;

import lombok.*;
import project.umc.app.domain.ReviewEntity;
import project.umc.app.domain.UserEntity;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreReviewsResponseDto {

    private String userName;
    private Float reviewPoint;
    private String reviewContent;
    private LocalDateTime reviewLocalDateTime;

    public static StoreReviewsResponseDto createStoreReviewsResponseDto(ReviewEntity reviewEntity){

        StoreReviewsResponseDto storeReviewsResponseDto = StoreReviewsResponseDto.builder()
                .userName(reviewEntity.getUserEntity().getName())
                .reviewPoint(reviewEntity.getPoint())
                .reviewContent(reviewEntity.getContent())
                .reviewLocalDateTime(reviewEntity.getCreatedAt())
                .build();
        return storeReviewsResponseDto;
    }

}
