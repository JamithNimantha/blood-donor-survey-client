package com.jamith.rmi.dto;

import java.io.Serializable;

/**
 * @author Jamith Nimantha
 */

public class ReportDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer data;
    private String label;

    public ReportDTO() {
    }

    /**
     * @return data value
     */
    public Integer getData() {
        return data;
    }

    /**
     * @param data set data value
     */
    public void setData(Integer data) {
        this.data = data;
    }

    /**
     * @return Label Name
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label Label Name
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return Generated ToString for the Object
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportDTO{");
        sb.append("data=").append(data);
        sb.append(", label='").append(label).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
