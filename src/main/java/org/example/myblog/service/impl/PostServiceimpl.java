package org.example.myblog.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.myblog.domain.Post;
import org.example.myblog.dto.PostDto;
import org.example.myblog.mapper.PostMapper;
import org.example.myblog.repository.PostRepository;
import org.example.myblog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceimpl implements PostService {

    final PostRepository postRepository;
    final PostMapper postMapper;

    // Create
    @Override
    public PostDto.CreateResDto create(PostDto.CreateReqDto createReqDto) {
        PostDto.CreateResDto res = postRepository.save(createReqDto.toEntity()).toCreateRespDto();

        return res;
    }

    // Detail
    @Override
    public PostDto.DetailResDto detail(PostDto.DetailReqDto detailReqDto){
        PostDto.DetailResDto res = postMapper.detail(detailReqDto);

        return res;
    }

    // List
    @Override
    public List<PostDto.ListResDto> list(PostDto.ListReqDto listReqDto) {
        listReqDto.setDeleted(false);
        List<PostDto.ListResDto> res = postMapper.list(listReqDto);

        return res;
    }

    // Update
    public void update(PostDto.UpdateReqDto updateReqDto){
        Post post = postRepository.findById(updateReqDto.getId()).orElse(null);
        if(post == null){
            throw new RuntimeException("no data");
        }

        if(updateReqDto.getTitle() != null){
            post.setTitle(updateReqDto.getTitle());
        }
        if(updateReqDto.getContent() != null){
            post.setContent(updateReqDto.getContent());
        }

        postRepository.save(post);
    }

    // Delete
    @Override
    public void delete(PostDto.DeleteReqDto deleteReqDto){
        Post post = postRepository.findById(deleteReqDto.getId()).orElse(null);
        if(post == null){
            throw new RuntimeException("no data");
        }

        post.setDeleted(true);

        postRepository.save(post);
    }
}
