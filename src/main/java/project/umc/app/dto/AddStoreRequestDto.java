package project.umc.app.dto;

import lombok.*;
import project.umc.app.domain.Address;
import project.umc.app.domain.StoreEntity;
import project.umc.app.service.OwnerService;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AddStoreRequestDto {

    private final OwnerService ownerService;

    private String name;
    private Address address;
    private Long ownerId;

    private List<Long> storeCategory = new ArrayList<>();

    public static StoreEntity toEntity(AddStoreRequestDto addStoreRequestDto){
        StoreEntity storeEntity = StoreEntity.builder()
                .name(addStoreRequestDto.getName())
                .address(addStoreRequestDto.getAddress())
                .ownerEntity(null)
                .build();
        return storeEntity;
    }
}
