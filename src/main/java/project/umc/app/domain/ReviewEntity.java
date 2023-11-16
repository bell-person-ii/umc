package project.umc.app.domain;

import javax.persistence.*;

@Entity
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

}
