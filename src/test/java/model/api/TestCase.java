package model.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {

    private String description;
    private String preconditions;
    private String postconditions;
    private String title;
    private int severity;
    private int priority;
}
