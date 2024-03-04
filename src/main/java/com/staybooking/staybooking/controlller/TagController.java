package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.tag.request.TagCreate;
import com.staybooking.staybooking.dto.tag.request.TagUpdate;
import com.staybooking.staybooking.dto.tag.response.TagResponse;
import com.staybooking.staybooking.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private TagService tagService;
    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<TagResponse>> findTag(@PathVariable Long id){
        APIResponse<TagResponse> apiResponse = tagService.getTagApiResponse(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @PostMapping
    public ResponseEntity<APIResponse<TagResponse>> createTag(@Valid @RequestBody TagCreate tagCreate){
        APIResponse<TagResponse> apiResponse = tagService.createTag(tagCreate);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<TagResponse>> changeTag(@PathVariable Long id, @Valid @RequestBody TagUpdate tagUpdate){
        APIResponse<TagResponse> apiResponse = tagService.updateTag(id, tagUpdate);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteTag(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = tagService.deleteTag(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

}
