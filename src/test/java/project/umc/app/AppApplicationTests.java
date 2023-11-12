package project.umc.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.Gender;
import project.umc.app.domain.UserEntity;
import project.umc.app.repository.UserRepository;

@SpringBootTest
@Transactional
@Rollback(false)
class AppApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Test
	@DisplayName("Auditing 기능 테스트")
	void contextLoads() {

		//given
		UserEntity userEntity = UserEntity.builder()
				.gender(Gender.MALE)
				.name("lee")
				.build();
		//when
		userRepository.save(userEntity);
		UserEntity result = userRepository.findOne(1L);


		//then
		Assertions.assertThat(result.getName()).isEqualTo(userEntity.getName());
	}

}
