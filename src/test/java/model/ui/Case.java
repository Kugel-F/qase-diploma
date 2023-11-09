package model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Case {

    private String title;
    private String description;
    private String preConditions;
    private String postConditions;
    private String severity;
    private String priority;
}
