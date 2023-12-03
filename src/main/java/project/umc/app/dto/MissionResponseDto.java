package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.MissionEntity;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionResponseDto {

    private String content;
    private Float reward;
    private LocalDate deadline;


    public static MissionResponseDto createMissionResponseDto(MissionEntity missionEntity){
        MissionResponseDto missionResponseDto = MissionResponseDto.builder()
                .content(missionEntity.getContent())
                .reward(missionEntity.getReward())
                .deadline(missionEntity.getDeadline())
                .build();

        return missionResponseDto;
    }
}
