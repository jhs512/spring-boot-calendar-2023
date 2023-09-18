package com.sbs.exam1.domain.article.repository;

import com.sbs.exam1.domain.article.entity.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByOrderByIdDesc(PageRequest pageRequest);

    List<Article> findByIdLessThanOrderByIdDesc(Long id, PageRequest pageRequest);
}
