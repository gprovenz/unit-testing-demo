package org.acme.demo.alerts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Alert {
    private String type;
    private Program program;
}
