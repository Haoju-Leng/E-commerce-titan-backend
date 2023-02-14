package com.titans.ecommerce.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFile extends BaseEntity{

    @Lob
    @Column(name = "file", columnDefinition = "LONGBLOB")
    private byte[] data;
}
