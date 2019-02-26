package com.app.controller;

import com.app.dto.AuthorDto;
import com.app.dto.BookDto;
import com.app.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private BookService bookService;

    public AppController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping("/book/{isbn}")
    public BookDto getOneBook(@PathVariable String isbn) {
        return bookService.getOneBookByIsbn(isbn);
    }

    @GetMapping("category/{categoryName}/books")
    public List<BookDto> getALLBooksByCategory(@PathVariable String categoryName) {
        return bookService.getALLBookByCategory(categoryName);
    }

    @GetMapping("/rating")
    public List<AuthorDto> getAllAuthors() {
        return bookService.getAllAuthor();
    }
}
