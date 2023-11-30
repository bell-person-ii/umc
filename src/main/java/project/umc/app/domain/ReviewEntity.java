package project.umc.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ReviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private StoreEntity storeEntity;

    private Float point;

    private String content;

    @OneToOne(mappedBy = "reviewEntity",fetch = FetchType.LAZY)
    private UserMissionEntity userMissionEntity;

    public void editUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public void editStoreEntity(StoreEntity storeEntity){
        this.storeEntity = storeEntity;
    }

}
