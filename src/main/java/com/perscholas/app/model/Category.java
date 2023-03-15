package com.perscholas.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="category")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NonNull
    @NotNull(message="Category Name cannot be blank")
    private String name;
    @NonNull
    @NotNull(message="Category description cannot be blank")
    private String description;

/*    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER )
    List<Product> product;*/



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
