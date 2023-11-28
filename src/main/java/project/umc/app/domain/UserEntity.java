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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthday;

    @Embedded
    private Address address;

    private String email;


    @Builder.Default
    @OneToMany(mappedBy = "userEntity")
    private List<UserMissionEntity> userMissionEntities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "userEntity")
    private List<ReviewEntity> reviewEntities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "userEntity")
    private List<UserFoodCategoryEntity> userFoodCategories = new ArrayList<>();

    public void addUserFoodCategories(List<UserFoodCategoryEntity> userFoodCategoryEntities){
        this.userFoodCategories.addAll(userFoodCategoryEntities);
    }


}
