package project.umc.app.dto;

import lombok.*;
import project.umc.app.domain.MissionEntity;
import project.umc.app.vaildation.annotation.ExistStore;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMissionRequestDto {

    @NotNull
    @ExistStore
    private Long storeId;
    @NotNull
    private String content;
    @NotNull
    private Float reward;
    @NotNull
    private String year;
    @NotNull
    private String month;
    @NotNull
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
