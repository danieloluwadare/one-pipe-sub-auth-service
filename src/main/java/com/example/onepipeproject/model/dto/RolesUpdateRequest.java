package com.example.onepipeproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesUpdateRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    private long userId;

    @NonNull
    private List<Long> roles;
}
