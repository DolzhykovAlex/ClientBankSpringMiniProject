package app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Inheritance(strategy = InheritanceType.JOINED)

   public abstract class AbstractClass {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
}
