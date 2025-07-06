package org.example.myblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.example.myblog.domain.Post;

import java.time.LocalDateTime;

public class PostDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {

        Long userId;
        String title;
        String content;

        public Post toEntity() { return Post.of(getUserId(), getTitle(), getContent()); }

    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    // Detail Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto {
        Long id;
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        String title;
        Long userId;
        LocalDateTime createdAt;
        LocalDateTime modifiedAt;
        Boolean deleted;
        String content;
    }

    // List Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto {
        Boolean deleted;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {

        Long id;
        String title;
        Long userId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;


    }

    // Update Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto {
        Long id;
        String title;
        String content;
    }

    // Delete Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteReqDto {
        Long id;
    }
}
