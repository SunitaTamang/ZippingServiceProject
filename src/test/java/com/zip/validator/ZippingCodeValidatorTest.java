package com.zip.validator;

import com.zip.pojo.ZippingData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ZippingCodeValidatorTest {

    @Autowired
    private ZippingCodeValidator zippingCodeValidator;

    @Test
    public void positiveValidateZipCodeData() {
        List<ZippingData> zipcodeDataList = Arrays.asList(new ZippingData(94133, 94133),
                new ZippingData(94200, 94299),
                new ZippingData(94300, 94399));
        boolean isValid = zippingCodeValidator.validateZipCode(zipcodeDataList);
        assertNotNull(isValid);
        assertEquals(true, isValid);
    }

    @Test
    public void negativeValidateZipCodeDataWithInvalidData() {
        List<ZippingData> zipcodeDataList = Arrays.asList(new ZippingData(null, null),
                new ZippingData(94200, 94299),
                new ZippingData(94300, 94399));
        boolean isValid = zippingCodeValidator.validateZipCode(zipcodeDataList);
        assertNotNull(isValid);
        assertEquals(false, isValid);
    }

    @Test
    public void negativeValidateZipCodeDataWithInvalidLowerAndHigherFreq() {
        List<ZippingData> zipcodeDataList = Arrays.asList(new ZippingData(123, 22),
                new ZippingData(94200, 94299),
                new ZippingData(94300, 94399));
        boolean isValid = zippingCodeValidator.validateZipCode(zipcodeDataList);
        assertNotNull(isValid);
        assertEquals(false, isValid);
    }
}
