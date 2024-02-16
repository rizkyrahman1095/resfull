package com.rizkyrahman.restfull.dto.request.respons;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
public class PageResponWrapper<T> {
    private List<T> data;
    private Long totalElement;
    private Integer totalPage;
    private Integer page;
    private Integer size;

    public PageResponWrapper(Page<T> pages) {
        this.data = pages.getContent();
        this.totalElement = pages.getTotalElements();
        this.totalPage = pages.getTotalPages();
        this.page = pages.getNumber();
        this.size = pages.getSize();
    }
}
