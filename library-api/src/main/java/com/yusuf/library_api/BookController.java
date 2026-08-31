package com.yusuf.library_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    private String currentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Book findOwnedOrThrow(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kitap bulunamadı"));
        if (!currentUsername().equals(book.getOwner())) {
            // Var olan ama başkasına ait bir kitabı 404 gibi davranarak gizliyoruz,
            // böylece hangi id'lerin dolu olduğu dışarıdan anlaşılamaz.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kitap bulunamadı");
        }
        return book;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findByOwner(currentUsername());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book.setOwner(currentUsername());
        return bookRepository.save(book);
    }

    @PutMapping("/{id}/status")
    public Book updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        Book book = findOwnedOrThrow(id);
        book.setStatus(request.getStatus());
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book book = findOwnedOrThrow(id);
        bookRepository.delete(book);
    }

    public static class StatusUpdateRequest {
        private String status;
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}