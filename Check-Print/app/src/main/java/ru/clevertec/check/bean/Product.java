package ru.clevertec.check.bean;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    int id;
    String name;
    double priceProduct;
    int count;
    double priceTotal;

}
