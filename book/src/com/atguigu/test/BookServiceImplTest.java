package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "博文真是太帅了", "博文", new BigDecimal(1555555), 200000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(21, "博文帅惨了", "博文", new BigDecimal(21313131), 100005, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for(Book bookquery : bookService.queryBooks())
            System.out.println(bookquery);
    }
}