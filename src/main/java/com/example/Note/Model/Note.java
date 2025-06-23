package com.example.Note.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("notes")
public class Note {
    @Id
    private String id;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();
}
