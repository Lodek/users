package com.wipro.bartenders.users.utils;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.BusinessIdentityTester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.openpojo.validation.test.impl.ToStringTester;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

public class PojoTestUtils {
    private static final Validator ACCESSOR_VALIDATOR =
            ValidatorBuilder.create()
                    .with(new GetterTester())
                    .with(new SetterTester())
                    .build();
    private static final Validator IDENTITY_VALIDATOR =
            ValidatorBuilder.create()
                    .with(new BusinessIdentityTester())
                    .with(new ToStringTester())
                    .build();
    public static void validateAccessors(final Class<?> clazz) {
        ACCESSOR_VALIDATOR.validate(PojoClassFactory.getPojoClass
                (clazz));
    }
    /**
     * Validate toString, equals, hashcode
     * @param clazz
     */
    public static void validateBusinessIdentity(final Class<?> clazz) {
        IDENTITY_VALIDATOR.validate(PojoClassFactory.getPojoClass
                (clazz));
    }
    public static void callToStringHashCodeAndEquals(Object obj) {
        if (obj != null) {
            obj.toString();
            obj.hashCode();
            obj.equals(obj);
        }
    }
    public static void validateBuilder(Object builder) {
        try {
// methods
            for (Method method : builder.getClass().getMethods()) {
                if (method.getDeclaringClass() == builder.getClass()) {
                    if (method.getParameterCount() == 0) {
                        method.invoke(builder);
                    }
                    if (method.getParameterCount() == 1) {
                        if (method.getParameterTypes()[0].isAssignableFrom
                                (Collection.class) ) {
                            method.invoke(builder, new Object[]
                                    {Collections.EMPTY_LIST});
                        } else {
                            method.invoke(builder, new Object[] {null});
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}