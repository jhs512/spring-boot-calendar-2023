package com.sbs.exam1.domain.article.service;

import com.sbs.exam1.domain.article.entity.Article;
import com.sbs.exam1.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> findLatest(int count) {
        PageRequest pageRequest = PageRequest.of(0, count);
        return articleRepository.findByOrderByIdDesc(pageRequest);
    }

    public List<Article> findLatestAfterId(int count, long lastId) {
        PageRequest pageRequest = PageRequest.of(0, count);

        if ( lastId == 0 ) return articleRepository.findByOrderByIdDesc(pageRequest);

        return articleRepository.findByIdLessThanOrderByIdDesc(lastId, pageRequest);
    }

    @Transactional
    public Article write(String title) {
        Article article = Article.builder()
                .title(title)
                .build();
        return articleRepository.save(article);
    }
}
