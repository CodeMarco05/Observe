package com.observe.os1.v1.prometheusQueries;

public enum CpuQuereis {
    CPU_USAGE_PERCENTAGE("avg by (instance,mode) (irate(node_cpu_seconds_total{mode!='idle'}[15s]))");

    private final String query;

    CpuQuereis(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
