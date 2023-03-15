package com.perscholas.app.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserImage {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    String name;
    @NonNull
    String url;
    @ToString.Exclude
    @NonNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "email")
    UserRegistration email;

    public UserImage(@NonNull String name, @NonNull String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImage userImage = (UserImage) o;
        return id == userImage.id && name.equals(userImage.name) && url.equals(userImage.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }
}
