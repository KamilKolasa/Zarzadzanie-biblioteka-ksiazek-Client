package com.app.controller;

import com.app.dto.CategoryDto;
import com.app.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class WebController {

    private BookService bookService;

    public WebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/category")
    public String getCategory(Model model) {
        model.addAttribute("categories", bookService.getAllCategories());
        model.addAttribute("books", bookService.getAllBook());
        model.addAttribute("categoryDto", new CategoryDto());
        return "category";
    }

    @PostMapping("/category")
    public String postCategory(Model model, @ModelAttribute CategoryDto categoryDto) {
        model.addAttribute("categories", bookService.getAllCategories());
        model.addAttribute("books", bookService.getALLBookByCategory(categoryDto.getCategory()));
        return "category";
    }
}
