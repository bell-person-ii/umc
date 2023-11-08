package project.umc.app.domain;

import lombok.Getter;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter
public class MissionEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity storeEntity;

    private String content;

    private Float reward;

    private LocalDate deadline;

    @OneToMany(mappedBy = "missionEntity")
    private List<UserMissionEntity> userMissionEntities =new ArrayList<>();
}
