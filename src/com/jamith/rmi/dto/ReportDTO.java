package com.jamith.rmi.dto;

import java.io.Serializable;

/**
 * @author Jamith Nimantha
 */

public class ReportDTO implements Serializable {

    private Integer data;
    private String label;

    public ReportDTO() {
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportDTO{");
        sb.append("data=").append(data);
        sb.append(", label='").append(label).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
