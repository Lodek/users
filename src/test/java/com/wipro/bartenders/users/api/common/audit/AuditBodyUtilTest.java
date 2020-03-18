package com.wipro.bartenders.users.api.common.audit;

import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AuditBodyUtilTest {

    @Test
    public void setOf_values_returnSetOfValues(){
        //given values
        Long a = 1L, b = 2L, c = 3L;

        //when called setOf
        Set<Long> result = AuditBodyUtil.setOf(a, b, c);

        //then set contains elements
        assertThat(result)
                .hasSize(3)
                .contains(a)
                .contains(b)
                .contains(c);
    }

    @Test
    public void defaultIfEmpty_validString_returnString(){
        //given string
        String value = "potato";
        String other = "ree" ;

        //when called defaultIfEmpty
        String result = AuditBodyUtil.defaultIfEmpty(value, other);

        //then string was returned
        assertThat(result).isEqualTo(value);
    }

    @Test
    public void defaultIfEmpty_null_returnOther() {
        //given string
        String other = "ree" ;

        //when called defaultIfEmpty
        String result = AuditBodyUtil.defaultIfEmpty(null, other);

        //then string was returned
        assertThat(result).isEqualTo(other);
    }


}
