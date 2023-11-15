package org.acme.demo.alerts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Program {
    private Integer id;

    private String name;

    private Boolean enabled;
}
