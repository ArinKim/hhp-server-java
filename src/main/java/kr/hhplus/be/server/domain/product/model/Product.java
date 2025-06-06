package kr.hhplus.be.server.domain.product.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int price;
    private int quantity;
    private LocalDateTime createdAt;
    private Date updatedAt;

    public void assignId(Long id) { this.id = id; } // ID를 할당하는 메서드, infrastructure에서 사용
}