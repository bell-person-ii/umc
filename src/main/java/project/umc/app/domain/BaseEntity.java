package project.umc.app.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false,name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = false,name = "updated_at")
    private LocalDateTime updatedAt;
}
