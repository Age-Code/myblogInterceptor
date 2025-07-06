package org.example.myblog.controller;

import lombok.RequiredArgsConstructor;
import org.example.myblog.dto.PostDto;
import org.example.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostRestController {

    final PostService postService;

    @PostMapping("")
    public ResponseEntity<PostDto.CreateResDto> create(@RequestBody PostDto.CreateReqDto createReqDto) {
        return ResponseEntity.ok(postService.create(createReqDto));
    }

    @GetMapping("/detail")
    public ResponseEntity<PostDto.DetailResDto> detail(PostDto.DetailReqDto detailReqDto) {
        return ResponseEntity.ok(postService.detail(detailReqDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostDto.ListResDto>> list(PostDto.ListReqDto listReqDto) {
        return ResponseEntity.ok(postService.list(listReqDto));
    }

    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostDto.UpdateReqDto updateReqDto) {
        postService.update(updateReqDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostDto.DeleteReqDto deleteReqDto) {
        postService.delete(deleteReqDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
