package com.lab.aes192.entity.Decimal;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithBigDecimal;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Table(name = "decimaldummy")
@SuperBuilder
// BigDecimal <- decimal(19,2)
public class DecimalDummy extends BaseType<BigDecimal> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "DECIMAL(19,2)")
    private BigDecimal dummy;

    public DecimalDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithBigDecimal());
    }

    @Override
    public BigDecimal getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
