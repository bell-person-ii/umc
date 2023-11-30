package project.umc.app.dto;


import lombok.*;
import project.umc.app.domain.ReviewEntity;
import project.umc.app.vaildation.annotation.ExistStore;
import project.umc.app.vaildation.annotation.ExistUser;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddReviewRequestDto {

    @NotNull
    @ExistUser
    private Long userId;
    @NotNull
    @ExistStore
    private Long storeId;
    @NotNull
    private Float point;
    private String content;

    public static ReviewEntity toEntity(AddReviewRequestDto addReviewRequestDto){
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .userEntity(null)
                .storeEntity(null)
                .point(addReviewRequestDto.getPoint())
                .content(addReviewRequestDto.content)
                .build();
        return reviewEntity;
    }

}
