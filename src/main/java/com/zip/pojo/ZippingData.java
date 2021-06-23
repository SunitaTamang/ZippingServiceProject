package com.zip.pojo;

import java.util.Objects;

/**
 * Zipping Code Data.
 */
public class ZippingData {

    private Integer lowerRangeData;
    private Integer higherRangeData;


    public ZippingData() {
    }

    public ZippingData(Integer lowerRangeData, Integer higherRangeData) {
        this.lowerRangeData = lowerRangeData;
        this.higherRangeData = higherRangeData;
    }

    public Integer getLowerRangeData() {
        return lowerRangeData;
    }

    public void setLowerRangeData(Integer lowerRangeData) {
        this.lowerRangeData = lowerRangeData;
    }

    public Integer getHigherRangeData() {
        return higherRangeData;
    }

    public void setHigherRangeData(Integer higherRangeData) {
        this.higherRangeData = higherRangeData;
    }

    /**
     * Equals Implementation.
     * @param data
     * @return Boolean.
     */
    @Override
    public boolean equals(Object data) {
        if (this == data) return true;
        if (data == null || getClass() != data.getClass()) return false;
        ZippingData that = (ZippingData) data;
        return Objects.equals(getLowerRangeData(), that.getLowerRangeData()) &&
                Objects.equals(getHigherRangeData(), that.getHigherRangeData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLowerRangeData(), getHigherRangeData());
    }

    /**
     * Method to present string representation.
     * @return String.
     */
    @Override
    public String toString() {
        return "ZippingData{" +
                "lowerRangeData=" + lowerRangeData +
                ", higherRangeData=" + higherRangeData +
                '}';
    }
}
