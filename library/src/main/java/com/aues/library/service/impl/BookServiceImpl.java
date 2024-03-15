package com.aues.library.service.impl;

import com.aues.library.exceptions.BookCreationException;
import com.aues.library.exceptions.BookDeletionException;
import com.aues.library.exceptions.BookNotFoundException;
import com.aues.library.exceptions.BookUpdateException;
import com.aues.library.model.Book;
import com.aues.library.repository.BookRepository;
import com.aues.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book object cannot be null");
        }

        // Validate required fields. For simplicity, only checking 'name' and 'author' here.
        if (book.getName() == null || book.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Book name is required");
        }

        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Book author is required");
        }

        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            // Wrap and rethrow any persistence exceptions in a more user-friendly manner
            throw new BookCreationException("Failed to create book", e);
        }
    }


    @Override
    public Book getBookById(Long id) {
        try {
            return bookRepository.findById(id)
                    .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the book with ID " + id, e);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book object cannot be null");
        }

        if (book.getId() == null) {
            throw new IllegalArgumentException("Book ID is required for updating");
        }

        // Fetch the existing book to ensure it exists and to prevent overwriting unrelated fields
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + book.getId() + " not found"));

        // Update fields. Only update fields that have been changed or are allowed to be updated.
        // Note: This is a simple approach. For more complex scenarios, consider using patch semantics or DTOs to handle partial updates.
        existingBook.setName(book.getName() != null ? book.getName() : existingBook.getName());
        existingBook.setDescription(book.getDescription() != null ? book.getDescription() : existingBook.getDescription());
        existingBook.setPhotos(book.getPhotos() != null ? book.getPhotos() : existingBook.getPhotos());
        existingBook.setPdf(book.getPdf() != null ? book.getPdf() : existingBook.getPdf());
        existingBook.setAuthor(book.getAuthor() != null ? book.getAuthor() : existingBook.getAuthor());
        existingBook.setPublicationDate(book.getPublicationDate() != null ? book.getPublicationDate() : existingBook.getPublicationDate());
        existingBook.setIsbn(book.getIsbn() != null ? book.getIsbn() : existingBook.getIsbn());
        existingBook.setCategory(book.getCategory() != null ? book.getCategory() : existingBook.getCategory());
        existingBook.setAvailabilityStatus(book.getAvailabilityStatus() != null ? book.getAvailabilityStatus() : existingBook.getAvailabilityStatus());

        try {
            return bookRepository.save(existingBook);
        } catch (Exception e) {
            // Wrap and rethrow any persistence exceptions in a more user-friendly manner
            throw new BookUpdateException("Failed to update book with ID " + book.getId(), e);
        }
    }


    @Override
    public void deleteBook(Long id) {
        try {
            if (!bookRepository.existsById(id)) {
                throw new BookNotFoundException("Book with ID " + id + " not found");
            }
            bookRepository.deleteById(id);
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BookDeletionException("Failed to delete book with ID " + id, e);
        }
    }

    @Override
    public List<Book> searchBooks(String query) {
        // Implement search logic. This example searches by name or author.
        // Adjust according to your entity's fields and requirements.
        return bookRepository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

}

