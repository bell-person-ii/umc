package project.umc.app.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthday;

    @Embedded
    private Address address;

    private String category;

    @OneToMany(mappedBy = "userEntity")
    private List<UserMissionEntity> userMissionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<ReviewEntity> reviewEntities = new ArrayList<>();

}
