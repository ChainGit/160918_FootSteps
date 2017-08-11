package com.chain.mybatis.day06;

public class UserFilter {

    private String name;
    private int minAge;
    private int maxAge;

    @Override
    public String toString() {
        return "UserFilter{" +
                "name='" + name + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                '}';
    }

    public String getName() {
        if (name == null || name.length() < 1)
            return "%null%";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public UserFilter(String name, int minAge, int maxAge) {
        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public UserFilter() {
    }
}
