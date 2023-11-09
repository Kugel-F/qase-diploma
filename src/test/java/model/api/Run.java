package model.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Run {

    private String title;
    private String description;
    @SerializedName("include_all_cases")
    private boolean includeAllCases;
    private boolean status;
}
