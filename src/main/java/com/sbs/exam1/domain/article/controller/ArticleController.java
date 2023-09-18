package com.sbs.exam1.domain.article.controller;

import com.sbs.exam1.domain.article.entity.Article;
import com.sbs.exam1.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final int pageItemsCount = 50;

    @GetMapping("/list")
    public String showList() {
        return "usr/article/list";
    }

    @GetMapping("/listMore")
    @ResponseBody
    public Map getListMore(long lastId) {
        List<Article> articles = articleService.findLatestAfterId(pageItemsCount, lastId);

        if (articles.isEmpty())
            return Map.of(
                    "resultCode", "S-2",
                    "msg", "성공"
            );

        return Map.of(
                "resultCode", "S-1",
                "msg", "성공",
                "data", articles,
                "lastId", articles.get(articles.size() - 1).getId()
        );
    }
}
