package org.example.myblog.controller;

import jakarta.servlet.http.HttpServletRequest;
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

    public Long getReqUserId(HttpServletRequest request) {
        if(request.getAttribute("reqUserId") == null){
            return null;
        }
        return (Long) request.getAttribute("reqUserId");
    }

    @PostMapping("")
    public ResponseEntity<PostDto.CreateResDto> create(@RequestBody PostDto.CreateReqDto createReqDto, HttpServletRequest request) {
        Long reqUserId = getReqUserId(request);

        System.out.println("Controller createReqDto title: " + createReqDto.getTitle());
        System.out.println("Controller reqUserId: " + createReqDto.getTitle());

        PostDto.CreateSevDto createSevDto = PostDto.CreateSevDto.builder().reqUserId(reqUserId).build();
        createSevDto = (PostDto.CreateSevDto) createSevDto.afterBuild(createReqDto);

        System.out.println("Controller createSevDto title: " + createSevDto.getTitle());

        return ResponseEntity.ok(postService.create(createSevDto));
    }

    @GetMapping("/detail")
    public ResponseEntity<PostDto.DetailResDto> detail(PostDto.DetailReqDto detailReqDto, HttpServletRequest request) {
        Long reqUserId = getReqUserId(request);
        PostDto.DetailSevDto detailSevDto = PostDto.DetailSevDto.builder().reqUserId(reqUserId).build();
        detailSevDto = (PostDto.DetailSevDto) detailSevDto.afterBuild(detailReqDto);

        return ResponseEntity.ok(postService.detail(detailSevDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostDto.ListResDto>> list(PostDto.ListReqDto listReqDto) {
        return ResponseEntity.ok(postService.list(listReqDto));
    }

    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostDto.UpdateReqDto updateReqDto, HttpServletRequest request) {
        Long reqUserId = getReqUserId(request);
        PostDto.UpdateSevDto updateSevDto = PostDto.UpdateSevDto.builder().reqUserId(reqUserId).build();
        updateSevDto = (PostDto.UpdateSevDto) updateSevDto.afterBuild(updateReqDto);
        postService.update(updateSevDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostDto.DeleteReqDto deleteReqDto, HttpServletRequest request) {
        Long reqUserId = getReqUserId(request);
        PostDto.DeleteSevDto deleteSevDto = PostDto.DeleteSevDto.builder().reqUserId(reqUserId).build();
        deleteSevDto = (PostDto.DeleteSevDto) deleteSevDto.afterBuild(deleteReqDto);
        postService.delete(deleteSevDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
