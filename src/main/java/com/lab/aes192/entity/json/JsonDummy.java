package com.lab.aes192.entity.json;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.jsontomapconverter.JsonToMapConverter;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithFloat;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@AllArgsConstructor
@Table(name = "jsondummy")
@SuperBuilder
public class JsonDummy extends BaseType<Map<String, Object>> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "json")
    @Convert(converter = JsonToMapConverter.class)
    private Map<String, Object> dummy = new HashMap<>();

    public JsonDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithMap());
    }


    @Override
    public Map<String, Object> getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }

}
