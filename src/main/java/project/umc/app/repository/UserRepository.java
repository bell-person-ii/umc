package project.umc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.umc.app.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
