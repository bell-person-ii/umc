package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.umc.app.domain.UserEntity;
import project.umc.app.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }
}
