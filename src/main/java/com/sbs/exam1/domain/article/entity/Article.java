package com.sbs.exam1.domain.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Article {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
}
