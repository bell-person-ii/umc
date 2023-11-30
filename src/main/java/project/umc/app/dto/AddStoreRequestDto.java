package project.umc.app.dto;

import lombok.*;
import project.umc.app.domain.Address;
import project.umc.app.domain.StoreEntity;
import project.umc.app.service.OwnerService;
import project.umc.app.vaildation.annotation.ExistCategories;
import project.umc.app.vaildation.annotation.ExistCategory;
import project.umc.app.vaildation.annotation.ExistOwner;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AddStoreRequestDto {

    private final OwnerService ownerService;

    @NotNull
    private String name;
    @Valid
    @NotNull
    private Address address;
    @NotNull
    @ExistOwner
    private Long ownerId;
    @ExistCategory
    private Long storeCategoryId;

    public static StoreEntity toEntity(AddStoreRequestDto addStoreRequestDto){
        StoreEntity storeEntity = StoreEntity.builder()
                .name(addStoreRequestDto.getName())
                .address(addStoreRequestDto.getAddress())
                .ownerEntity(null)
                .category(null)
                .build();
        return storeEntity;
    }
}
