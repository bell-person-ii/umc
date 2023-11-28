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
@Table(name="owner")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;
    private String name;
    private LocalDate birthday;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "ownerEntity")
    private List<StoreEntity> storeEntities = new ArrayList<>();

}
