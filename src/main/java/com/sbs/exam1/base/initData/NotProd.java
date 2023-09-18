package com.sbs.exam1.base.initData;

import com.sbs.exam1.domain.article.service.ArticleService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner init(ArticleService articleService) {
        return args -> {
            IntStream.rangeClosed(1, 122).forEach(i -> {
                articleService.write("제목 " + i);
            });
        };
    }
}
