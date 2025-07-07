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
    public PostDto.CreateResDto create(PostDto.CreateSevDto createSevDto) {
        Long reqUserId = createSevDto.getReqUserId();
        if(reqUserId == null){
            throw new RuntimeException("not login");
        }

        System.out.println("PostRepository createSevDto title " + createSevDto.getTitle());

        PostDto.CreateResDto res = postRepository.save(createSevDto.toEntity()).toCreateRespDto();

        return res;
    }

    // Detail
    @Override
    public PostDto.DetailResDto detail(PostDto.DetailSevDto detailSevDto){
        PostDto.DetailResDto res = postMapper.detail(detailSevDto);

        Long reqUserId = detailSevDto.getReqUserId();
        if(res.getUserId().equals(reqUserId)){
            res.setApproved(true);
        }else{
            res.setApproved(false);
        }

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
    public void update(PostDto.UpdateSevDto updateSevDto){
        Post post = postRepository.findById(updateSevDto.getId()).orElse(null);
        if(post == null){
            throw new RuntimeException("no data");
        }

        Long reqUserId = updateSevDto.getReqUserId();
        if(reqUserId == null){
            throw new RuntimeException("not login");
        }
        if(!reqUserId.equals(post.getUserId())){
            throw new RuntimeException("not author matched");
        }
        if(updateSevDto.getTitle() != null){
            post.setTitle(updateSevDto.getTitle());
        }
        if(updateSevDto.getContent() != null){
            post.setContent(updateSevDto.getContent());
        }

        postRepository.save(post);
    }

    // Delete
    @Override
    public void delete(PostDto.DeleteSevDto deleteSevDto){
        Post post = postRepository.findById(deleteSevDto.getId()).orElse(null);
        if(post == null){
            throw new RuntimeException("no data");
        }

        Long reqUserId = deleteSevDto.getReqUserId();
        if(reqUserId == null){
            throw new RuntimeException("not login");
        }
        if(!reqUserId.equals(post.getUserId())){
            throw new RuntimeException("not author matched");
        }

        post.setDeleted(true);

        postRepository.save(post);
    }
}
