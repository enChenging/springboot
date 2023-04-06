package com.release;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author yancheng
 * @since 2023/4/6
 */
@Data
@Document("tb_person")
public class Person {

    @Id
    private ObjectId id;

    @Field("myname")
    private String name;

    private int age;

    private String address;
}
