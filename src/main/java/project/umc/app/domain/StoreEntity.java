package project.umc.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void editOwner(OwnerEntity ownerEntity){
        this.ownerEntity = ownerEntity;
    }

    public void editCategory(String category){this.category = category;}
}
