package com.dio.live.dto;

public interface ModelDTO<M, D> {

    D toDTO(M obj);
    M toModel();
}
