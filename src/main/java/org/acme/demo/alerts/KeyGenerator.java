package org.acme.demo.alerts;

import java.util.ArrayList;
import java.util.List;

public class KeyGenerator {
    public String generateKey(Alert alert) {
        List<String> components = new ArrayList<>();

        switch (alert.getType()) {
            case "type1":
                components.add(getId(alert));
                components.add(getName(alert));
                components.add(getEnabled(alert));
                break;
            case "type2":
                components.add(getId(alert));
                components.add(getName(alert));
                break;
            default:
                components.add(getId(alert));
        }

        return String.join("-", components);
    }

    private String getId(Alert alert) {
        if (alert.getProgram()!=null) {
            return String.valueOf(alert.getProgram().getId());
        }
        return "null";
    }

    private String getName(Alert alert) {
        if (alert.getProgram()!=null) {
            return String.valueOf(alert.getProgram().getName());
        }
        return "null";
    }

    private String getEnabled(Alert alert) {
        if (alert.getProgram()!=null) {
            return String.valueOf(alert.getProgram().getEnabled());
        }
        return "null";
    }
}
