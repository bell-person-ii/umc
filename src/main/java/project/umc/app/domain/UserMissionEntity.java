package project.umc.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "user_mission")
public class UserMissionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private MissionEntity missionEntity;

    @Enumerated(EnumType.STRING)
    private MissionCompletion missionCompletion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;

    public void editUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public void editMissionEntity(MissionEntity missionEntity){
        this.missionEntity = missionEntity;
    }

    public void editMissionCompletion(MissionCompletion missionCompletion){
        this.missionCompletion = missionCompletion;
    }
}
