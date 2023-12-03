package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.MissionCompletion;
import project.umc.app.domain.MissionEntity;
import project.umc.app.domain.UserMissionEntity;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMissionResponseDto {

    private Long userId;
    private String content;
    private Float reward;
    private LocalDate deadline;
    private MissionCompletion missionCompletion;


    public static UserMissionResponseDto createUserMissionResponseDto(UserMissionEntity userMissionEntity){
        UserMissionResponseDto userMissionResponseDto = UserMissionResponseDto.builder()
                .userId(userMissionEntity.getId())
                .content(userMissionEntity.getMissionEntity().getContent())
                .reward(userMissionEntity.getMissionEntity().getReward())
                .deadline(userMissionEntity.getMissionEntity().getDeadline())
                .missionCompletion(userMissionEntity.getMissionCompletion())
                .build();

        return userMissionResponseDto;
    }
}
