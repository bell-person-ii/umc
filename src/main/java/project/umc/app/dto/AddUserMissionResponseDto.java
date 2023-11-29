package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.MissionCompletion;
import project.umc.app.domain.UserMissionEntity;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AddUserMissionResponseDto {

    private Long userId;
    private Long missionId;
    private String missionContent;
    private LocalDate deadline;
    private MissionCompletion missionCompletion;

    public static AddUserMissionResponseDto createAddUserMissionResponseDto(UserMissionEntity userMissionEntity){
        AddUserMissionResponseDto addUserMissionResponseDto = AddUserMissionResponseDto.builder()
                .userId(userMissionEntity.getUserEntity().getId())
                .missionId(userMissionEntity.getMissionEntity().getId())
                .missionContent(userMissionEntity.getMissionEntity().getContent())
                .deadline(userMissionEntity.getMissionEntity().getDeadline())
                .missionCompletion(userMissionEntity.getMissionCompletion())
                .build();
        return addUserMissionResponseDto;
    }
}
