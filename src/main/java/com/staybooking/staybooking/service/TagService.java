package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.tag.request.TagCreate;
import com.staybooking.staybooking.dto.tag.request.TagUpdate;
import com.staybooking.staybooking.dto.tag.response.TagResponse;

public interface TagService {
    APIResponse<TagResponse> findTag(Long id);
    APIResponse<TagResponse> createTag(TagCreate tagToCreate);
    APIResponse<TagResponse> updateTag(Long id, TagUpdate tagToUpdate);
    APIResponse<Boolean> deleteTag(Long id);
}
