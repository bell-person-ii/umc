package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.MissionEntity;
import project.umc.app.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {
    private final MissionRepository missionRepository;

    public void save(MissionEntity missionEntity){
        missionRepository.save(missionEntity);
    }

    public List<MissionEntity> findAllByOwnerId(Long id){
        return missionRepository.findAllByStoreId(id);
    }
}
