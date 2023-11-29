package project.umc.app.dto;

import lombok.*;
import project.umc.app.domain.MissionEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMissionRequestDto {

    private Long storeId;
    private String content;
    private Float reward;
    private String year;
    private String month;
    private String day;


    public static LocalDate stringDateToLocalDate(AddMissionRequestDto addMissionRequestDto){
        String year = addMissionRequestDto.getYear();
        String month = addMissionRequestDto.getMonth();
        String day = addMissionRequestDto.getDay();
        String strDate = year+'-'+month+'-'+day;
        LocalDate resultDate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        return resultDate;
    }

    public static MissionEntity toEntity(AddMissionRequestDto addMissionRequestDto){
        MissionEntity missionEntity = MissionEntity.builder()
                .storeEntity(null)
                .content(addMissionRequestDto.getContent())
                .reward(addMissionRequestDto.getReward())
                .deadline(AddMissionRequestDto.stringDateToLocalDate(addMissionRequestDto))
                .build();
        return missionEntity;
    }

}
