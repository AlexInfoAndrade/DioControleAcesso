package com.dio.live.dto;

import lombok.*;

public interface ModelDTO<M, D> {

    D toDTO(M obj);
    M toModel();
}
