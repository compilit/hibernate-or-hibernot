package com.compilit.domain.api;

import java.util.List;
import java.util.UUID;

public record OrderDto(UUID id, List<OrderLineDto> orderLines, String createdAt) {}