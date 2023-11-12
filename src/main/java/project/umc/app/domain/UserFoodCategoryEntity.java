package project.umc.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_food_category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFoodCategoryEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_food_category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "food_category_id")
    private FoodCategoryEntity foodCategoryEntity;

}
