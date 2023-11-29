package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.Address;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.StoreEntity;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AddStoreResponseDto {

    private Long storeId;
    private String storeName;
    private Address storeAddress;
    private String storeCategory;
    private Long ownerId;
    private String ownerName;
    private String ownerEmail;

    public static AddStoreResponseDto createAddStoreResponseDto(StoreEntity storeEntity, OwnerEntity ownerEntity){
        AddStoreResponseDto addStoreResponseDto = AddStoreResponseDto.builder()
                .storeId(storeEntity.getId())
                .storeName(storeEntity.getName())
                .storeAddress(storeEntity.getAddress())
                .storeCategory(storeEntity.getCategory())
                .ownerId(ownerEntity.getId())
                .ownerName(ownerEntity.getName())
                .ownerEmail(ownerEntity.getEmail())
                .build();

        return addStoreResponseDto;
    }
}
