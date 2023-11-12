package project.umc.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Builder
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

    @Builder.Default
    @OneToMany(mappedBy = "userEntity")
    private List<UserMissionEntity> userMissionEntities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "userEntity")
    private List<ReviewEntity> reviewEntities = new ArrayList<>();

}
