package project.umc.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "food_category")
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class FoodCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "foodCategoryEntity")
    private List<UserFoodCategoryEntity> foodCategoryEntities = new ArrayList<>();


}
