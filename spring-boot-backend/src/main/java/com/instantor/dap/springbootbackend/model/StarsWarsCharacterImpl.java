package com.instantor.dap.springbootbackend.model;

import java.util.Objects;

/**
 * DTO that models a {@link  StarsWarsCharacter}
 */
public final class StarsWarsCharacterImpl implements StarsWarsCharacter {

    private final String name;

    public StarsWarsCharacterImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StarsWarsCharacterImpl that = (StarsWarsCharacterImpl) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}