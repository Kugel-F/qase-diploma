package model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private String name;
    private String code;
    private String description;
    private String accessType;
    private String membersAccessType;
    private String addMembersAccessType;
}
