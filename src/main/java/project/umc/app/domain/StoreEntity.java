package project.umc.app.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
public class StoreEntity extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    private String name;

    private String category;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private OwnerEntity ownerEntity;

    @OneToMany(mappedBy = "storeEntity")
    private List<MissionEntity> missionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "storeEntity")
    private List<ReviewEntity> reviewEntities = new ArrayList<>();
}
