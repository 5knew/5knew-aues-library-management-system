package com.aues.library.service;

import com.aues.library.model.Book;

import java.util.List;

public interface BookService {
    // Создание новой книги
    Book createBook(Book book);

    // Получение книги по ID
    Book getBookById(Long id);

    // Получение всех книг
    List<Book> getAllBooks();

    // Обновление существующей книги
    Book updateBook(Book book);

    // Удаление книги по ID
    void deleteBook(Long id);

    // Поиск книг по заданному критерию (например, по названию или автору)
    List<Book> searchBooks(String query);
}

