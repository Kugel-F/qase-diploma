package model.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Suite {

    private String title;
    private String description;
    private String preconditions;
    @SerializedName("parent_id")
    private int parentId;
}
