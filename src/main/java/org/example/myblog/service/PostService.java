package org.example.myblog.service;

import org.example.myblog.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDto.CreateResDto create(PostDto.CreateReqDto createReqDto);
    PostDto.DetailResDto detail(PostDto.DetailReqDto detailReqDto);
    List<PostDto.ListResDto> list(PostDto.ListReqDto listReqDto);
    void update(PostDto.UpdateReqDto updateReqDto);
    void delete(PostDto.DeleteReqDto deleteReqDto);
}
