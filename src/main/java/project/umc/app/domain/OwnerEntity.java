package project.umc.app.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="owner")
@Getter
public class OwnerEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "owner_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "ownerEntity")
    private List<StoreEntity> storeEntities = new ArrayList<>();

}
