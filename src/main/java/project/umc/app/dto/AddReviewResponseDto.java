package project.umc.app.dto;

import lombok.*;
import project.umc.app.domain.ReviewEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddReviewResponseDto {

    private String userName;
    private Float point;
    private String content;


    public static AddReviewResponseDto createAddReviewResponseDto(ReviewEntity reviewEntity){

        AddReviewResponseDto addReviewResponseDto = AddReviewResponseDto.builder()
                .userName(reviewEntity.getUserEntity().getName())
                .point(reviewEntity.getPoint())
                .content(reviewEntity.getContent())
                .build();

        return addReviewResponseDto;

    }


}
