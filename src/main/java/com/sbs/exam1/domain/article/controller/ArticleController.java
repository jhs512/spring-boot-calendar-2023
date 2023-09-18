package com.sbs.exam1.domain.article.controller;

import com.sbs.exam1.domain.article.entity.Article;
import com.sbs.exam1.domain.article.service.ArticleService;
import com.sbs.exam1.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
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

    @GetMapping("/calendar")
    public String showCalendar_() {
        LocalDate today = LocalDate.now();

        return "redirect:/article/calendar/" + today.getYear() + "/" + Ut.str.padWithZeros(today.getMonthValue(), 2);
    }

    @GetMapping("/calendar/{year}/{month}")
    public String showCalendar(@PathVariable String year, @PathVariable String month, Model model) {
        LocalDate startDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        // articleService의 find 메소드를 사용하여 해당 범위에 포함되는 게시물을 검색
        List<Article> articles = articleService.findByEventDateBetween(startDate, endDate);

        model.addAttribute("articles", articles);

        LocalDate prevMonth = startDate.minusMonths(1);
        LocalDate nextMonth = startDate.plusMonths(1);

        model.addAttribute("prevYear", prevMonth.getYear());
        model.addAttribute("prevMonth", Ut.str.padWithZeros(prevMonth.getMonthValue(), 2));
        model.addAttribute("nextYear", nextMonth.getYear());
        model.addAttribute("nextMonth", Ut.str.padWithZeros(nextMonth.getMonthValue(), 2));

        return "usr/article/calendar";
    }
}
