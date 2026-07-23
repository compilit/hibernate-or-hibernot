package com.compilit.domain.api;

import java.util.List;
import java.util.UUID;

public record NewOrderDto(UUID id, List<NewOrderLineDto> orderLines) {}