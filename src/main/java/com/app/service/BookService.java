package com.app.service;

import com.app.dto.AuthorDto;
import com.app.dto.BookDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {
    private final RestTemplate restTemplate;

    @Value("${urls.api}")
    private String bookUrl;

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookDto> getAllBook() {
        try {
            final String url = bookUrl + "/book";

            ResponseEntity<BookDto[]> response = restTemplate.exchange(url, HttpMethod.GET, null, BookDto[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "get ALL BOOK");
        }
    }

    public BookDto getOneBookByIsbn(String isbn) {
        try {
            final String url = bookUrl + "/book/{isbn}";

            Map<String, String> params = new HashMap<>();
            params.put("isbn", String.valueOf(isbn));

            ResponseEntity<BookDto> response = restTemplate.exchange(url, HttpMethod.GET, null, BookDto.class, params);
            return response.getBody();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "get ONE by ISBN BOOK");
        }
    }

    public List<BookDto> getALLBookByCategory(String category) {
        try {
            final String url = bookUrl + "/category/{categoryName}/books";

            Map<String, String> params = new HashMap<>();
            params.put("categoryName", String.valueOf(category));

            ResponseEntity<BookDto[]> response = restTemplate.exchange(url, HttpMethod.GET, null, BookDto[].class, params);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.SERVICE, "get ALL by CATEGORY BOOK");
        }
    }

    public List<AuthorDto> getAllAuthor() {
        try {
            final String url = bookUrl + "/rating";

            ResponseEntity<AuthorDto[]> response = restTemplate.exchange(url, HttpMethod.GET, null, AuthorDto[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "get ALL RATING AUTHOR");
        }
    }

    public Set<String> getAllCategories() {
        try {
            Set<String> categories = new TreeSet<>();
            List<BookDto> bookList = getAllBook();
            for (BookDto book : bookList) {
                if (book.getCategories() != null) {
                    for (String s : book.getCategories()) {
                        categories.add(s);
                    }
                }
            }
            return categories;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "get ALL CATEGORIES");
        }
    }
}
