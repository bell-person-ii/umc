package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.MissionCompletion;
import project.umc.app.domain.UserMissionEntity;
import project.umc.app.vaildation.annotation.ExistMission;
import project.umc.app.vaildation.annotation.ExistUser;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AddUserMissionRequestDto {

    @NotNull
    @ExistUser
    private Long userId;
    @NotNull
    @ExistMission
    private Long missionId;

    public static UserMissionEntity toEntity(AddUserMissionRequestDto addUserMissionRequestDto){
        UserMissionEntity userMissionEntity = UserMissionEntity.builder()
                .userEntity(null)
                .missionEntity(null)
                .missionCompletion(MissionCompletion.No)
                .build();
        return userMissionEntity;
    }
}


