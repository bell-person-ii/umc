package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.Address;
import project.umc.app.domain.MissionEntity;
import project.umc.app.domain.StoreEntity;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMissionResponseDto {

    private String missionContent;
    private Float missionReward;
    private LocalDate deadLine;
    private Long storeId;
    private Address storeAddress;

    public static AddMissionResponseDto createAddMissionResponseDto
            (MissionEntity missionEntity, StoreEntity storeEntity){
        AddMissionResponseDto addMissionResponseDto = AddMissionResponseDto.builder()
                .missionContent(missionEntity.getContent())
                .missionReward(missionEntity.getReward())
                .deadLine(missionEntity.getDeadline())
                .storeId(storeEntity.getId())
                .storeAddress(storeEntity.getAddress())

                .build();

        return addMissionResponseDto;
    }

}
