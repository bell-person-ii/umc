package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.MissionEntity;
import project.umc.app.dto.MissionResponseDto;
import project.umc.app.repository.MissionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {
    private final MissionRepository missionRepository;

    public void save(MissionEntity missionEntity){
        missionRepository.save(missionEntity);
    }

    public MissionEntity findOneById(Long id){
        return missionRepository.findOneById(id);
    }

    public List<MissionEntity> findAllByStoreId(Long id){
        return missionRepository.findAllByStoreId(id);
    }

    public Optional<MissionEntity> isExistMission(Long id){

        MissionEntity findMissionEntity = missionRepository.findOneById(id);

        Optional<MissionEntity> optionalMissionEntity;

        if(findMissionEntity == null){
            return optionalMissionEntity = Optional.empty();
        }
        else{
            return optionalMissionEntity = Optional.of(findMissionEntity);
        }
    }

    public List<MissionEntity> findAllByOwnerId(Long id){
        return missionRepository.findAllByStoreId(id);
    }

    public List<MissionResponseDto> missionEntityListToDtoList(List<MissionEntity> missionEntityList){

        List<MissionResponseDto> missionResponseDtoList = new ArrayList<>();

        for(int i=0 ; i< missionEntityList.size();i++){
            missionResponseDtoList.add(MissionResponseDto.createMissionResponseDto(missionEntityList.get(i)));
        }
        return missionResponseDtoList;

    }


}
