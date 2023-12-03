package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.MissionEntity;
import project.umc.app.domain.UserEntity;
import project.umc.app.domain.UserMissionEntity;
import project.umc.app.dto.MissionResponseDto;
import project.umc.app.dto.UserMissionResponseDto;
import project.umc.app.repository.MissionRepository;
import project.umc.app.repository.UserMissionRepository;
import project.umc.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionService {
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    public void save(UserMissionEntity userMissionEntity){
        userMissionRepository.save(userMissionEntity);
    }

    public UserMissionEntity insertUserAndMissionEntityById(UserMissionEntity userMissionEntity,
                                                            Long userId, Long missionId){

        UserEntity findUserEntity = userRepository.findOneById(userId);
        MissionEntity findMissionEntity = missionRepository.findOneById(missionId);
        userMissionEntity.editUserEntity(findUserEntity);
        userMissionEntity.editMissionEntity(findMissionEntity);
        return userMissionEntity;
    }

    public List<UserMissionEntity> findNotCompletedUserMissions(Long userId){
        return userMissionRepository.findNotCompletedUserMissions(userId);
    }

    public List<UserMissionResponseDto> EntityListToDtoList(List<UserMissionEntity> userMissionEntityList){

        List<UserMissionResponseDto> userMissionResponseDtoList = new ArrayList<>();

        for(int i=0 ; i< userMissionEntityList.size();i++){
            userMissionResponseDtoList.add(UserMissionResponseDto.createUserMissionResponseDto(userMissionEntityList.get(i)));
        }
        return userMissionResponseDtoList;

    }

    public UserMissionEntity changeCompletion(Long userMissionId){
        return userMissionRepository.changeCompletion(userMissionId);
    }

}
