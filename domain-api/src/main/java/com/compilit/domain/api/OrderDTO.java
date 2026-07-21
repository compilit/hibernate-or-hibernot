package com.compilit.domain.api;

import java.util.List;

public record OrderDTO(List<OrderLineDTO> orderLines) {}