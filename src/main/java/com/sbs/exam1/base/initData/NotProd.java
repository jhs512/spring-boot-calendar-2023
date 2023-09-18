package com.sbs.exam1.base.initData;

import com.sbs.exam1.domain.article.service.ArticleService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner init(ArticleService articleService) {
        return args -> {
            LocalDate today = LocalDate.now(); // 오늘 날짜를 가져옵니다.

            IntStream.rangeClosed(1, 30).forEach(i -> {
                LocalDate articleDate = today.plusDays(i - 1);
                String formattedDate = articleDate.toString();
                articleService.write("제목 " + i, formattedDate);
            });

            LocalDate today2 = LocalDate.now(); // 오늘 날짜를 가져옵니다.

            IntStream.rangeClosed(1, 10).forEach(i -> {
                LocalDate articleDate = today2.plusDays(i - 1);
                String formattedDate = articleDate.toString();
                articleService.write("제목 " + i, formattedDate);
            });
        };
    }
}
